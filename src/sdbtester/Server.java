/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester;

/**
 *
 * @author boris
 */
import cdbtest.DataForServer;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

public class Server implements Runnable {
    // The port we will listen on

    private int port = 3223;
    // A pre-allocated buffer for encrypting data
    private final ByteBuffer buffer = ByteBuffer.allocate(100000);
    private static ServerSocketChannel ssChannel = null;
    private static Logger logger = Logger.getLogger(Server.class);
    private static STestCaseHelper appSettings;
    private ArrayList<SocketChannel> workingKeys = new ArrayList<SocketChannel>(0);
    private ArrayList<SelectionKey> dataSentKeys = new ArrayList<SelectionKey>(0);
    private ArrayList<SelectionKey> readyKeys = new ArrayList<SelectionKey>(0);
    private boolean dataSentToAll = false;
    private SelectionKey serverKey = null;
    private SelectionKey monitorKey = null;

    public Server(int port) {
        appSettings = STestCaseHelper.getInstance();
        this.port = port;
//        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            ssChannel = ServerSocketChannel.open();
            ssChannel.configureBlocking(false);
            ssChannel.socket().bind(new InetSocketAddress(appSettings.getAppServerPort()));
            System.out.println("listen on " + appSettings.getAppServerPort());
            Selector selector = Selector.open();
            serverKey = ssChannel.register(selector, SelectionKey.OP_ACCEPT);
            for (;;) {
                selector.select();
                Set keys = selector.selectedKeys();
                for (Iterator i = keys.iterator(); i.hasNext();) {
                    SelectionKey key = (SelectionKey) i.next();
                    i.remove();
                    if (!appSettings.isCurrentTestStarted() && (key == serverKey)) {
                        SocketChannel clientChannel = ssChannel.accept();
                        clientChannel.configureBlocking(false);
                        SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        workingKeys.add(clientChannel);
                    } else if (key != serverKey) {
                        processingClient(key, keys);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("server run  \n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "server run", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void sendDataToClient(SocketChannel clientChannel) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(appSettings.getDataSetForClients());
        ByteBuffer buf = ByteBuffer.wrap(baos.toByteArray());
        clientChannel.write(buf);
    }

    private void processClientIfNextStep(byte status, ArrayList channels) throws IOException {
        appSettings.resetDataForClientsFields(appSettings.nextCategoryForClient(status));
        for (int j = 0; j < channels.size(); ++j) {
            SocketChannel curChannel = (SocketChannel) channels.get(j);
            sendDataToClient(curChannel);
        }
        readyKeys.clear();
    }

    private void tryStoreResults(DataForServer dataForServer) {
        if (appSettings.isTheLastStep(dataForServer.getTestStatus())) {
            appSettings.storeTestCaseSettings(appSettings.getTestCaseName(), appSettings.getTestDbType());
            logger.info("Stored at " + dataForServer.getTestStatus());
        }
    }

    private void processingClient(SelectionKey key, Set keys) throws IOException, ClassNotFoundException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        if (!dataSentToAll && appSettings.isCurrentTestStarted() && !dataSentKeys.contains(key)) {
            appSettings.setDataForClientsFields();
            sendDataToClient(clientChannel);
            dataSentKeys.add(key);
            appSettings.appendLogTestContent("Data sent to " + clientChannel.socket().getInetAddress());
        }
        if (!dataSentToAll) {
            if (dataSentKeys.size() == workingKeys.size()) {
                dataSentToAll = true;
                dataSentKeys.clear();
            }
        }
        DataForServer dataForServer = null;
        if (key.isReadable()) {
            buffer.clear();
            int byteSread = clientChannel.read(buffer);
            if (byteSread != -1) {
                buffer.flip();
                byte[] bytes = new byte[buffer.limit()];
                buffer.get(bytes);
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                dataForServer = (DataForServer) ois.readObject();
                if (dataForServer != null) {

                    //switching on test steps
                    switch (dataForServer.getTestStatus()) {
                        case DataForServer.READY_FOR_TEST: {
                            if (!readyKeys.contains(key)) {
                                readyKeys.add(key);
                                appSettings.appendLogTestContent("Client ready for testing: " + clientChannel.socket().getInetAddress());
                            }
                            if (dataForServer.isMonitor()) {
                                monitorKey = key;
                                appSettings.setHasMonitor(true);
                                appSettings.appendLogTestContent("Monitor ready for testing: " + clientChannel.socket().getInetAddress());
                            }
                            if (readyKeys.size() == workingKeys.size()) {
                                processClientIfNextStep(DataForServer.READY_FOR_TEST, workingKeys);
                                readyKeys.clear();
                            }
                            break;
                        }
                        case DataForServer.END_OF_INSERT: {
                            if (!readyKeys.contains(key)) {
                                appSettings.calculateAndSetProgress(STestCaseHelper.PROGRESS_INSERT, true);
                                readyKeys.add(key);
                                appSettings.setResultInsert(appSettings.calculateAverageFromLists(appSettings.getResultInsert(), dataForServer.getTestResult()));
                                appSettings.appendLogTestContent("Got INSERT result from " + clientChannel.socket().getInetAddress());
                            }
                            if ((readyKeys.size() + 1) == workingKeys.size()) {
                                processClientIfNextStep(DataForServer.END_OF_INSERT, workingKeys);
                                tryStoreResults(dataForServer);
                                appSettings.calculateAndSetProgress(STestCaseHelper.PROGRESS_INSERT, false);
                                appSettings.appendLogTestContent("INSERT finished");
                            }
                            break;
                        }
                        case DataForServer.END_OF_SELECT: {
                            if (!readyKeys.contains(key)) {
                                appSettings.calculateAndSetProgress(STestCaseHelper.PROGRESS_SELECT, true);
                                readyKeys.add(key);
                                appSettings.setResultSelect(appSettings.calculateAverageFromLists(appSettings.getResultSelect(), dataForServer.getTestResult()));
                                appSettings.appendLogTestContent("Got INSERT result from " + clientChannel.socket().getInetAddress());
                            }

                            if ((readyKeys.size() + 1) == workingKeys.size()) {
                                processClientIfNextStep(DataForServer.END_OF_SELECT, workingKeys);
                                tryStoreResults(dataForServer);
                                appSettings.calculateAndSetProgress(STestCaseHelper.PROGRESS_SELECT, false);
                                appSettings.appendLogTestContent("SELECT finished");
                            }
                            break;
                        }
                        case DataForServer.END_OF_UPDATE: {
                            if (!readyKeys.contains(key)) {
                                appSettings.calculateAndSetProgress(STestCaseHelper.PROGRESS_UPDATE, true);
                                readyKeys.add(key);
                                appSettings.setResultUpdate(appSettings.calculateAverageFromLists(appSettings.getResultUpdate(), dataForServer.getTestResult()));
                                appSettings.appendLogTestContent("Got INSERT result from " + clientChannel.socket().getInetAddress());
                            }
                            if ((readyKeys.size() + 1) == workingKeys.size()) {
                                processClientIfNextStep(DataForServer.END_OF_UPDATE, workingKeys);
                                tryStoreResults(dataForServer);
                                appSettings.calculateAndSetProgress(STestCaseHelper.PROGRESS_UPDATE, false);
                                appSettings.appendLogTestContent("UPDATE finished");
                            }
                            break;
                        }
                        case DataForServer.END_OF_DELETE: {
                            if (!readyKeys.contains(key)) {
                                appSettings.calculateAndSetProgress(STestCaseHelper.PROGRESS_DELETE, true);
                                readyKeys.add(key);
                                appSettings.setResultDelete(appSettings.calculateAverageFromLists(appSettings.getResultDelete(), dataForServer.getTestResult()));
                                appSettings.appendLogTestContent("Got DELETE result from " + clientChannel.socket().getInetAddress());
                            }
                            if ((readyKeys.size() + 1) == workingKeys.size()) {
                                processClientIfNextStep(DataForServer.END_OF_DELETE, workingKeys);
                                tryStoreResults(dataForServer);
                                appSettings.calculateAndSetProgress(STestCaseHelper.PROGRESS_DELETE, false);
                                appSettings.appendLogTestContent("DELETE finished");
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    logger.error(e);
                                }
                                appSettings.setCurrentTestStarted(false);
                            }
                            break;
                        }
                        case DataForServer.MONITOR_END_OF_INSERT: {
                            appSettings.setResultInsertCPU(dataForServer.getTestResultCPU());
                            appSettings.setResultInsertRAM(dataForServer.getTestResultRAM());
                            tryStoreResults(dataForServer);
                            appSettings.appendLogTestContent("Got INSERT CPU & RAM results from " + clientChannel.socket().getInetAddress());
                            break;
                        }
                        case DataForServer.MONITOR_END_OF_SELECT: {
                            appSettings.setResultSelectCPU(dataForServer.getTestResultCPU());
                            appSettings.setResultSelectRAM(dataForServer.getTestResultRAM());
                            tryStoreResults(dataForServer);
                            appSettings.appendLogTestContent("Got SELECT CPU & RAM results from " + clientChannel.socket().getInetAddress());
                            break;
                        }
                        case DataForServer.MONITOR_END_OF_UPDATE: {
                            appSettings.setResultUpdateCPU(dataForServer.getTestResultCPU());
                            appSettings.setResultUpdateRAM(dataForServer.getTestResultRAM());
                            tryStoreResults(dataForServer);
                            appSettings.appendLogTestContent("Got UPDATE CPU & RAM results from " + clientChannel.socket().getInetAddress());
                            break;
                        }
                        case DataForServer.MONITOR_END_OF_DELETE: {
                            appSettings.setResultDeleteCPU(dataForServer.getTestResultCPU());
                            appSettings.setResultDeleteRAM(dataForServer.getTestResultRAM());
                            tryStoreResults(dataForServer);
                            appSettings.appendLogTestContent("Got DELETE CPU & RAM results from " + clientChannel.socket().getInetAddress());
                            break;
                        }

                    }
                }
            }
        }
    }
}
