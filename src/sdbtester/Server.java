/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester;

/**
 *
 * @author boris
 */
import cdbtest.DataForClients;
import cdbtest.DataForServer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.apache.log4j.Logger;

public class Server implements Runnable {
    // The port we will listen on

    private int port;
    // A pre-allocated buffer for encrypting data
    private final ByteBuffer buffer = ByteBuffer.allocate(32000);
    private static ServerSocketChannel ssChannel = null;
    private static Logger logger = Logger.getLogger(Server.class);
    private static TestCaseHelper appSettings;
    private Charset charset = Charset.forName("ISO-8859-1");
    private CharsetEncoder encoder = charset.newEncoder();
    private CharsetDecoder decoder = charset.newDecoder();
    private ArrayList<SocketChannel> clientsKeysList = new ArrayList<SocketChannel>(0);

    public Server(int port) {
        appSettings = TestCaseHelper.getInstance();
        this.port = port;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            ssChannel = ServerSocketChannel.open();
            ssChannel.configureBlocking(false);
            ssChannel.socket().bind(new InetSocketAddress(appSettings.getAppServerPort()));
            System.out.println("listen on " + appSettings.getAppServerPort());
            Selector selector = Selector.open();
            SelectionKey serverKey = ssChannel.register(selector, SelectionKey.OP_ACCEPT);
            for (;;) {
                selector.select();
                Set keys = selector.selectedKeys();
                for (Iterator i = keys.iterator(); i.hasNext();) {
                    SelectionKey key = (SelectionKey) i.next();
                    i.remove();

                    if (key == serverKey) {
                        SocketChannel clientChannel = ssChannel.accept();
                        clientChannel.configureBlocking(false);
                        SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        clientsKeysList.add(clientChannel);

                    } else {
                        processingClient(key);
                    }
                }
            }

        } catch (IllegalArgumentException e) {
            System.err.println("exception here" + e);


        } catch (Exception ie) {
            System.err.println(ie);
        }
    }

    private void processingClient(SelectionKey key) throws IOException, ClassNotFoundException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        DataForServer dataForServer = null;
        if (key.isReadable()) {
            int bytesread = clientChannel.read(buffer);
            if (bytesread != -1) {
                buffer.flip();
                byte[] bytes = new byte[buffer.limit()];
                buffer.get(bytes);
                ByteArrayInputStream bios = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bios);
                dataForServer = (DataForServer) ois.readObject();
                if(dataForServer != null){
                                        
                }
            }
            

            key.cancel();
            clientChannel.close();

        }

    }
    
    
    private void processingDataForServer(DataForServer dataForServer){
        
    }
    
    
}
