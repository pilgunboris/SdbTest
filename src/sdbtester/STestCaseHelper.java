/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester;

import cdbtest.DataForClients;
import cdbtest.DataForServer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import sdbtester.analysis.SAnalysisHelper;

/**
 *
 * @author boris
 */
public class STestCaseHelper {

    //constants for progress bar
    public static final byte PROGRESS_INSERT = 20;
    public static final byte PROGRESS_SELECT = 22;
    public static final byte PROGRESS_UPDATE = 24;
    public static final byte PROGRESS_DELETE = 26;
    //config options
    public static final String TEST_CASES_MAIN_PATH = "DbTestCases/";
    public static final String SETTINGS_APP_PROP_FILE = "global_settings.properties";
    public static final String SETTINGS_TEST_CASE_PROP_FILE = "test_case.properties";
    public static final String DB_TYPE_MYSQL = "mysql";     //default
    public static final String DB_TYPE_MONGO = "mongo";
    //properties constants
    public static final String PROPERTY_CASE_NAME = "test_case.name";
    public static final String PROPERTY_CASE_DESCRIPTION = "test_case.description";
    private static final String PROPERTY_DB_TYPE = "test_case.db.type";
    private static final String PROPERTY_DB_PORT = "test_case.db.port";
    private static final String PROPERTY_DB_ADDRESS = "test_case.db.address";
    private static final String PROPERTY_DB_LOGIN = "test_case.db.login";
    private static final String PROPERTY_DB_PASS = "test_case.db.pass";
    private static final String PROPERTY_QUERY_INSERT_LIST = "test_case.query.insert.list";
    private static final String PROPERTY_QUERY_SELECT_LIST = "test_case.query.select.list";
    private static final String PROPERTY_QUERY_UPDATE_LIST = "test_case.query.update.list";
    private static final String PROPERTY_QUERY_DELETE_LIST = "test_case.query.delete.list";
    private static final String PROPERTY_HAS_INSERT = "test_case.query.has.insert";
    private static final String PROPERTY_HAS_SELECT = "test_case.query.has.select";
    private static final String PROPERTY_HAS_UPDATE = "test_case.query.has.update";
    private static final String PROPERTY_HAS_DELETE = "test_case.query.has.delete";
    //prop results request time
    public static final String PROPERTY_RESULT_INSERT = "test_case.result.insert";
    public static final String PROPERTY_RESULT_SELECT = "test_case.result.select";
    public static final String PROPERTY_RESULT_UPDATE = "test_case.result.update";
    public static final String PROPERTY_RESULT_DELETE = "test_case.result.delete";
    //prop results CPU load
    public static final String PROPERTY_RESULT_INSERT_CPU = "test_case.result.insert.cpu";
    public static final String PROPERTY_RESULT_SELECT_CPU = "test_case.result.select.cpu";
    public static final String PROPERTY_RESULT_UPDATE_CPU = "test_case.result.update.cpu";
    public static final String PROPERTY_RESULT_DELETE_CPU = "test_case.result.delete.cpu";
    //prop results RAM load
    public static final String PROPERTY_RESULT_INSERT_RAM = "test_case.result.insert.ram";
    public static final String PROPERTY_RESULT_SELECT_RAM = "test_case.result.select.ram";
    public static final String PROPERTY_RESULT_UPDATE_RAM = "test_case.result.update.ram";
    public static final String PROPERTY_RESULT_DELETE_RAM = "test_case.result.delete.ram";
    public static final String ARRAY_DELIM = "!&!";
    //global properties
    private static final String PROPERTY_APP_SERVER_PORT = "app.server.port";
    //test case fields
    private String TestCaseName = "";
    private String TestCaseDescription = "";
    private String TestDbType = "";
    private Object DbConnection = null;
    private String TestCurrentDbName = "";
    private String TestDbLogin = "";
    private String TestDbPass = "";
    private String TestDbAddress = "";
    private String TestDbPort = "";
    private ArrayList<String> QueriesInsert = new ArrayList<String>(0);
    private ArrayList<String> QueriesSelect = new ArrayList<String>(0);
    private ArrayList<String> QueriesUpdate = new ArrayList<String>(0);
    private ArrayList<String> QueriesDelete = new ArrayList<String>(0);
    private DataForClients DataSetForClients = new DataForClients();
    private int ClientThreadsNum = 10;
    private int ClientTestRequestNum = 50;
    private boolean HasInsert = true;
    private boolean HasSelect = true;
    private boolean HasUpdate = true;
    private boolean HasDelete = true;
    private boolean HasMonitor = false;
    //results 
    private ArrayList<Double> ResultInsert = new ArrayList<Double>(0);
    private ArrayList<Double> ResultSelect = new ArrayList<Double>(0);
    private ArrayList<Double> ResultUpdate = new ArrayList<Double>(0);
    private ArrayList<Double> ResultDelete = new ArrayList<Double>(0);
    private ArrayList<Long> ResultInsertCPU = new ArrayList<Long>(0);
    private ArrayList<Long> ResultInsertRAM = new ArrayList<Long>(0);
    private ArrayList<Long> ResultSelectCPU = new ArrayList<Long>(0);
    private ArrayList<Long> ResultSelectRAM = new ArrayList<Long>(0);
    private ArrayList<Long> ResultUpdateCPU = new ArrayList<Long>(0);
    private ArrayList<Long> ResultUpdateRAM = new ArrayList<Long>(0);
    private ArrayList<Long> ResultDeleteCPU = new ArrayList<Long>(0);
    private ArrayList<Long> ResultDeleteRAM = new ArrayList<Long>(0);
    //global fields
    private int AppServerPort = 3223;
    private boolean CurrentTestStarted = false;
    private int ProgressBarPosition = -1;
    private String LogTestContent = "";
    private Thread ProgressThread = null;
    //analysis fields
    private SAnalysisHelper AnalysisTest1 = null;
    private SAnalysisHelper AnalysisTest2 = null;
//initialized fields
    private static Logger logger = Logger.getLogger(STestCaseHelper.class);
    private static STestCaseHelper instance = new STestCaseHelper();

    private STestCaseHelper() {
    }

    public static STestCaseHelper getInstance() {
        return instance;
    }

    public boolean resetResultsArrays() {
        boolean flagHasOldResults = !this.ResultInsert.isEmpty()
                || !this.ResultInsertCPU.isEmpty()
                || !this.ResultInsertRAM.isEmpty()
                || !this.ResultSelect.isEmpty()
                || !this.ResultSelectCPU.isEmpty()
                || !this.ResultSelectRAM.isEmpty()
                || !this.ResultUpdate.isEmpty()
                || !this.ResultUpdateCPU.isEmpty()
                || !this.ResultUpdateRAM.isEmpty()
                || !this.ResultDelete.isEmpty()
                || !this.ResultDeleteCPU.isEmpty()
                || !this.ResultDeleteRAM.isEmpty();
        if (flagHasOldResults && JOptionPane.showConfirmDialog(null, "Сбросить старые результаты?", "Подтверждение", JOptionPane.OK_CANCEL_OPTION) == 0) {
            this.setResultInsert(new ArrayList<Double>(0));
            this.setResultInsertCPU(new ArrayList<Long>(0));
            this.setResultInsertRAM(new ArrayList<Long>(0));
            this.setResultSelect(new ArrayList<Double>(0));
            this.setResultSelectCPU(new ArrayList<Long>(0));
            this.setResultSelectRAM(new ArrayList<Long>(0));
            this.setResultUpdate(new ArrayList<Double>(0));
            this.setResultUpdateCPU(new ArrayList<Long>(0));
            this.setResultUpdateRAM(new ArrayList<Long>(0));
            this.setResultDelete(new ArrayList<Double>(0));
            this.setResultDeleteCPU(new ArrayList<Long>(0));
            this.setResultDeleteRAM(new ArrayList<Long>(0));
            return true;
        } else if (flagHasOldResults) {
            return false;
        } else {
            return true;
        }
    }

    //average from 2 ArrayLists<Long>
    public ArrayList calculateAverageFromLists(ArrayList resultList, ArrayList newList) {
        try {
            ArrayList<Long> tmpRes = new ArrayList<Long>(0);
            if (resultList.isEmpty()) {
                return new ArrayList(newList);
            } else {
                for (int i = 0; i < newList.size(); ++i) {
                    long a = Long.parseLong(resultList.get(i).toString());
                    long b = Long.parseLong(newList.get(i).toString());
                    tmpRes.add((a + b) / 2);
                }
                return tmpRes;
            }

        } catch (Exception e) {
            logger.warn("calculateAverageFromLists  \n" + e);
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "calculateAverageFromLists", JOptionPane.ERROR_MESSAGE);
            return new ArrayList();

        }
    }

    /*
     * switch on DataForServer statuses
     */
    public boolean isTheLastStep(byte curStep) {
        switch (curStep) {
            case DataForServer.END_OF_INSERT: {
                return !HasSelect && !HasUpdate && !HasDelete;
            }
            case DataForServer.MONITOR_END_OF_INSERT: {
                return !HasSelect && !HasUpdate && !HasDelete;
            }
            case DataForServer.END_OF_SELECT: {
                return !HasUpdate && !HasDelete;
            }
            case DataForServer.MONITOR_END_OF_SELECT: {
                return !HasUpdate && !HasDelete;
            }
            case DataForServer.END_OF_UPDATE: {
                return !HasDelete;
            }
            case DataForServer.MONITOR_END_OF_UPDATE: {
                return !HasDelete;
            }
            case DataForServer.END_OF_DELETE: {
                return true;
            }
            case DataForServer.MONITOR_END_OF_DELETE: {
                return true;
            }
            default: {
                return false;
            }
        }
    }

    /*
     * curStatus == DataForServerObj.getTestStatus(); returns DataForClients
     * statuses
     */
    public byte nextCategoryForClient(byte curStatus) {
        switch (curStatus) {
            case DataForServer.READY_FOR_TEST: {
                if (HasInsert) {
                    return DataForClients.START_INSERT;
                } else if (HasSelect) {
                    return DataForClients.START_SELECT;
                } else if (HasUpdate) {
                    return DataForClients.START_UPDATE;
                } else if (HasDelete) {
                    return DataForClients.START_DELETE;
                } else {
                    return DataForClients.END_TESTING;
                }
            }
            case DataForServer.END_OF_INSERT: {
                if (HasSelect) {
                    return DataForClients.START_SELECT;
                } else if (HasUpdate) {
                    return DataForClients.START_UPDATE;
                } else if (HasDelete) {
                    return DataForClients.START_DELETE;
                } else {
                    return DataForClients.END_TESTING;
                }
            }
            case DataForServer.END_OF_SELECT: {
                if (HasUpdate) {
                    return DataForClients.START_UPDATE;
                } else if (HasDelete) {
                    return DataForClients.START_DELETE;
                } else {
                    return DataForClients.END_TESTING;
                }
            }
            case DataForServer.END_OF_UPDATE: {
                if (HasDelete) {
                    return DataForClients.START_DELETE;
                } else {
                    return DataForClients.END_TESTING;
                }
            }
            case DataForServer.END_OF_DELETE: {
                return DataForClients.END_TESTING;
            }
        }
        return -1;
    }

    public boolean setDataForClientsFields() {
        try {
            DataSetForClients.setClientTestRequestNum(this.ClientTestRequestNum);
            DataSetForClients.setClientThreadsNum(this.ClientThreadsNum);
            DataSetForClients.setDbAdress(this.TestDbAddress);
            DataSetForClients.setDbLogin(this.TestDbLogin);
            DataSetForClients.setDbPass(this.TestDbPass);
            DataSetForClients.setDbPort(this.TestDbPort);
            DataSetForClients.setDbType(this.TestDbType);
            DataSetForClients.setQueriesInsert(this.QueriesInsert);
            DataSetForClients.setQueriesSelect(this.QueriesSelect);
            DataSetForClients.setQueriesUpdate(this.QueriesUpdate);
            DataSetForClients.setQueriesDelete(this.QueriesDelete);
            DataSetForClients.setTestStatus(DataForClients.MAIN_TEST_DATA);
            DataSetForClients.setDbName(this.TestCurrentDbName);
        } catch (Exception e) {
            logger.warn("setDataForClientsFields  \n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "setDataForClientsFields", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean resetDataForClientsFields(byte status) {
        this.DataSetForClients = new DataForClients();
        this.DataSetForClients.setTestStatus(status);
        return true;
    }

    public boolean loadGlobalSettings() {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(SETTINGS_APP_PROP_FILE));
            this.setAppServerPort(Integer.parseInt(prop.getProperty(PROPERTY_APP_SERVER_PORT)));

        } catch (IOException e) {
            logger.warn("Cannot load global properties. Will be created new " + SETTINGS_APP_PROP_FILE + " file  \n" + e.getStackTrace());
//            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "Cannot find properties.", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (NumberFormatException e) {
            logger.warn("Wrong property format  \n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "Wrong property format.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean storeGlobalSettings() {
        try {
            Properties prop = new Properties();

            prop.setProperty(PROPERTY_APP_SERVER_PORT, String.valueOf(this.getAppServerPort()));

            prop.store(new FileOutputStream(SETTINGS_APP_PROP_FILE), null);
        } catch (IOException e) {
            logger.warn("Cannot store global properties  \n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "Cannot store properties.", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean loadTestFromTemplate(String caseName, String dbType) {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(TEST_CASES_MAIN_PATH + dbType + "/" + caseName + "/" + SETTINGS_TEST_CASE_PROP_FILE));

            //test case
            this.setTestCaseName(prop.getProperty(PROPERTY_CASE_NAME));
            this.setTestCaseDescription(prop.getProperty(PROPERTY_CASE_DESCRIPTION));
            //db settings
            this.setTestDbType(prop.getProperty(PROPERTY_DB_TYPE));
            this.setTestDbAddress(prop.getProperty(PROPERTY_DB_ADDRESS));
            this.setTestDbPort(prop.getProperty(PROPERTY_DB_PORT));
            this.setTestDbLogin(prop.getProperty(PROPERTY_DB_LOGIN));
            this.setTestDbPass(prop.getProperty(PROPERTY_DB_PASS));
            //queries
            this.setQueriesInsert(stringToList(prop.getProperty(PROPERTY_QUERY_INSERT_LIST)));
            this.setQueriesSelect(stringToList(prop.getProperty(PROPERTY_QUERY_SELECT_LIST)));
            this.setQueriesUpdate(stringToList(prop.getProperty(PROPERTY_QUERY_UPDATE_LIST)));
            this.setQueriesDelete(stringToList(prop.getProperty(PROPERTY_QUERY_DELETE_LIST)));
            //has queries?
            this.setHasInsert(strToBool(prop.getProperty(PROPERTY_HAS_INSERT)));
            this.setHasSelect(strToBool(prop.getProperty(PROPERTY_HAS_SELECT)));
            this.setHasUpdate(strToBool(prop.getProperty(PROPERTY_HAS_UPDATE)));
            this.setHasDelete(strToBool(prop.getProperty(PROPERTY_HAS_DELETE)));
            //test results
            this.setResultInsert(stringToList(prop.getProperty(PROPERTY_RESULT_INSERT)));
            this.setResultSelect(stringToList(prop.getProperty(PROPERTY_RESULT_SELECT)));
            this.setResultUpdate(stringToList(prop.getProperty(PROPERTY_RESULT_UPDATE)));
            this.setResultDelete(stringToList(prop.getProperty(PROPERTY_RESULT_DELETE)));
            //test results CPU
            this.setResultInsertCPU(stringToList(prop.getProperty(PROPERTY_RESULT_INSERT_CPU)));
            this.setResultSelectCPU(stringToList(prop.getProperty(PROPERTY_RESULT_SELECT_CPU)));
            this.setResultUpdateCPU(stringToList(prop.getProperty(PROPERTY_RESULT_UPDATE_CPU)));
            this.setResultDeleteCPU(stringToList(prop.getProperty(PROPERTY_RESULT_DELETE_CPU)));
            //test results RAM
            this.setResultInsertRAM(stringToList(prop.getProperty(PROPERTY_RESULT_INSERT_RAM)));
            this.setResultSelectRAM(stringToList(prop.getProperty(PROPERTY_RESULT_SELECT_RAM)));
            this.setResultUpdateRAM(stringToList(prop.getProperty(PROPERTY_RESULT_UPDATE_RAM)));
            this.setResultDeleteRAM(stringToList(prop.getProperty(PROPERTY_RESULT_DELETE_RAM)));

        } catch (IOException e) {
            logger.warn("Cannot load properties  \n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "Cannot find properties.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean storeTestCaseSettings(String caseName, String dbType) {
        if (caseName == null) {
            caseName = this.TestCaseName;
        }
        if (dbType == null) {
            dbType = this.TestDbType;
        }
        try {
            Properties prop = new Properties();

            //properties list
            //test case
            prop.setProperty(PROPERTY_CASE_NAME, this.getTestCaseName());
            prop.setProperty(PROPERTY_CASE_DESCRIPTION, this.getTestCaseDescription());
            //db settings
            prop.setProperty(PROPERTY_DB_TYPE, this.getTestDbType());
            prop.setProperty(PROPERTY_DB_ADDRESS, this.getTestDbAddress());
            prop.setProperty(PROPERTY_DB_PORT, this.getTestDbPort());
            prop.setProperty(PROPERTY_DB_LOGIN, this.getTestDbLogin());
            prop.setProperty(PROPERTY_DB_PASS, this.getTestDbPass());
            //queries:
            prop.setProperty(PROPERTY_QUERY_INSERT_LIST, listToString(QueriesInsert));
            prop.setProperty(PROPERTY_QUERY_SELECT_LIST, listToString(QueriesSelect));
            prop.setProperty(PROPERTY_QUERY_UPDATE_LIST, listToString(QueriesUpdate));
            prop.setProperty(PROPERTY_QUERY_DELETE_LIST, listToString(QueriesDelete));
            //has queries?
            prop.setProperty(PROPERTY_HAS_INSERT, boolToStr(this.HasInsert));
            prop.setProperty(PROPERTY_HAS_SELECT, boolToStr(this.HasSelect));
            prop.setProperty(PROPERTY_HAS_UPDATE, boolToStr(this.HasUpdate));
            prop.setProperty(PROPERTY_HAS_DELETE, boolToStr(this.HasDelete));
            //testing results
            prop.setProperty(PROPERTY_RESULT_INSERT, listToString(getResultInsert()));
            prop.setProperty(PROPERTY_RESULT_SELECT, listToString(getResultSelect()));
            prop.setProperty(PROPERTY_RESULT_UPDATE, listToString(getResultUpdate()));
            prop.setProperty(PROPERTY_RESULT_DELETE, listToString(getResultDelete()));
            //test result cpu
            prop.setProperty(PROPERTY_RESULT_INSERT_CPU, listToString(ResultInsertCPU));
            prop.setProperty(PROPERTY_RESULT_SELECT_CPU, listToString(ResultSelectCPU));
            prop.setProperty(PROPERTY_RESULT_UPDATE_CPU, listToString(ResultUpdateCPU));
            prop.setProperty(PROPERTY_RESULT_DELETE_CPU, listToString(ResultDeleteCPU));
            //test result RAM
            prop.setProperty(PROPERTY_RESULT_INSERT_RAM, listToString(ResultInsertRAM));
            prop.setProperty(PROPERTY_RESULT_SELECT_RAM, listToString(ResultSelectRAM));
            prop.setProperty(PROPERTY_RESULT_UPDATE_RAM, listToString(ResultUpdateRAM));
            prop.setProperty(PROPERTY_RESULT_DELETE_RAM, listToString(ResultDeleteRAM));
            ///storing
            prop.store(new FileOutputStream(TEST_CASES_MAIN_PATH + dbType + "/" + caseName + "/" + SETTINGS_TEST_CASE_PROP_FILE), null);
        } catch (IOException e) {
            logger.warn("Cannot store properties  \n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "Cannot store properties.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean setCurrentTestName(String caseName, String dbType, String caseDescription) {
        if (TestCaseName != null && this.TestCaseName.equals(caseName)) {
            return true;
        }
        try {
            File mainDir = new File(TEST_CASES_MAIN_PATH);
            if (!mainDir.isDirectory()) {
                if (mainDir.mkdirs()) {
                    logger.info("created  DbTestCases/");
                } else {
                    throw new IOException("Cannot create DbTestCases dir");
                }
            }
            //set or rename testCaseDir
            File testCaseDir = new File(TEST_CASES_MAIN_PATH + dbType + "/" + caseName);
            if (!testCaseDir.isDirectory()) {
                if (!testCaseDir.mkdirs()) {
                    throw new IOException("Cannot create DbTestCases dir");
                }
                setTestCaseName(caseName);
                setTestCaseDescription(caseDescription);
                setTestDbType(dbType);
            } else {
                if (!caseName.equals(getTestCaseName())) {
                    JOptionPane.showMessageDialog(null, "Такое имя уже существует. Попробуйте другое", "Внимание!", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        } catch (IOException e) {
            logger.error(e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "Not enough mana!", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "Not enough mana!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        storeTestCaseSettings(caseName, dbType);
        return true;
    }

    private void setTestCaseName(String TestCaseName) {
        this.TestCaseName = TestCaseName;
    }

    private String listToString(ArrayList list) {
        String res = "";
        if (list.isEmpty()) {
            return res;
        }
        for (int i = 0; i < list.size(); ++i) {
            res += list.get(i).toString() + ARRAY_DELIM;
        }
        return res;
    }

    private ArrayList stringToList(String str) {
        if (str == null || str.equals("")) {
            return new ArrayList<String>();
        }
        ArrayList<Object> res = null;
        try {
            res = new ArrayList<Object>(Arrays.asList(str.split(ARRAY_DELIM)));
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "Not enough mana!", JOptionPane.ERROR_MESSAGE);
        }
        return res;
    }

    private String boolToStr(boolean has) {
        return (has ? "1" : "0");
    }

    private boolean strToBool(String has) {
        if (has == null) {
            return true;
        }
        return (has.equals("0") ? false : true);
    }

    public void calculateAndSetProgress(byte curPhase, boolean justStarted) {
        int part = 0;
        int num = 0;
        if (HasInsert && ++num > 0
                && HasSelect && ++num > 0
                && HasUpdate && ++num > 0
                && HasDelete && ++num > 0) {
            part = 100 / num;
        }
        num = 0;
        switch (curPhase) {
            case PROGRESS_INSERT: {
                if (justStarted) {
                    this.ProgressBarPosition = part / 2;
                } else {
                    this.ProgressBarPosition = part;
                }
                break;
            }
            case PROGRESS_SELECT: {
                if (HasInsert && ++num > 0) {
                    if (justStarted) {
                        this.ProgressBarPosition = part * num + part / 2;
                    } else {
                        this.ProgressBarPosition = part * num + part;
                    }
                }
                break;
            }
            case PROGRESS_UPDATE: {
                if (HasInsert && ++num > 0 && HasSelect && ++num > 0) {
                    if (justStarted) {
                        this.ProgressBarPosition = part * num + part / 2;
                    } else {
                        this.ProgressBarPosition = part * num + part;
                    }
                }
                break;
            }
            case PROGRESS_DELETE: {
                if (HasInsert && ++num > 0 && HasSelect && ++num > 0 && HasUpdate && ++num > 0) {
                    if (justStarted) {
                        this.ProgressBarPosition = part * num + part / 2;
                    } else {
                        this.ProgressBarPosition = 100;
                    }
                }
                break;
            }
        }
    }

    public String getTestCaseName() {
        return TestCaseName;
    }

    public String getTestDbType() {
        return TestDbType;
    }

    public void setTestDbType(String TestDbType) {
        this.TestDbType = TestDbType;
    }

    public String getTestCaseDescription() {
        return TestCaseDescription;
    }

    public void setTestCaseDescription(String TestCaseDescription) {
        this.TestCaseDescription = TestCaseDescription;
    }

    public Object getDbConnection() {
        return DbConnection;
    }

    public void setDbConnection(Object DbConnection) {
        this.DbConnection = DbConnection;
    }

    public String getTestDbAddress() {
        return TestDbAddress;
    }

    public void setTestDbAddress(String TestDbAddress) {
        this.TestDbAddress = TestDbAddress;
    }

    public String getTestDbLogin() {
        return TestDbLogin;
    }

    public void setTestDbLogin(String TestDbLogin) {
        this.TestDbLogin = TestDbLogin;
    }

    public String getTestDbPass() {
        return TestDbPass;
    }

    public void setTestDbPass(String TestDbPass) {
        this.TestDbPass = TestDbPass;
    }

    public String getTestDbPort() {
        return TestDbPort;
    }

    public void setTestDbPort(String TestDbPort) {
        this.TestDbPort = TestDbPort;
    }

    public String getTestCurrentDbName() {
        return TestCurrentDbName;
    }

    public void setTestCurrentDbName(String TestCurrentDbName) {
        this.TestCurrentDbName = TestCurrentDbName;
    }

    public ArrayList<String> getQueriesDelete() {
        return QueriesDelete;
    }

    public void setQueriesDelete(ArrayList<String> QueriesDelete) {
        this.QueriesDelete = QueriesDelete;
    }

    public ArrayList<String> getQueriesInsert() {
        return QueriesInsert;
    }

    public void setQueriesInsert(ArrayList<String> QueriesInsert) {
        this.QueriesInsert = QueriesInsert;
    }

    public ArrayList<String> getQueriesSelect() {
        return QueriesSelect;
    }

    public void setQueriesSelect(ArrayList<String> QueriesSelect) {
        this.QueriesSelect = QueriesSelect;
    }

    public ArrayList<String> getQueriesUpdate() {
        return QueriesUpdate;
    }

    public void setQueriesUpdate(ArrayList<String> QueriesUpdate) {
        this.QueriesUpdate = QueriesUpdate;
    }

    public int getAppServerPort() {
        return AppServerPort;
    }

    public void setAppServerPort(int AppServerPort) {
        this.AppServerPort = AppServerPort;
    }

    public DataForClients getDataSetForClients() {
        return DataSetForClients;
    }

    public void setDataSetForClients(DataForClients DataSetForClients) {
        setDataForClientsFields();
        this.DataSetForClients = DataSetForClients;
    }

    public int getClientTestRequestNum() {
        return ClientTestRequestNum;
    }

    public void setClientTestRequestNum(int ClientTestRequestNum) {
        this.ClientTestRequestNum = ClientTestRequestNum;
    }

    public int getClientThreadsNum() {
        return ClientThreadsNum;
    }

    public void setClientThreadsNum(int ClientThreadsNum) {
        this.ClientThreadsNum = ClientThreadsNum;
    }

    public boolean isHasDelete() {
        return HasDelete;
    }

    public void setHasDelete(boolean HasDelete) {
        this.HasDelete = HasDelete;
    }

    public boolean isHasInsert() {
        return HasInsert;
    }

    public void setHasInsert(boolean HasInsert) {
        this.HasInsert = HasInsert;
    }

    public boolean isHasSelect() {
        return HasSelect;
    }

    public void setHasSelect(boolean HasSelect) {
        this.HasSelect = HasSelect;
    }

    public boolean isHasUpdate() {
        return HasUpdate;
    }

    public void setHasUpdate(boolean HasUpdate) {
        this.HasUpdate = HasUpdate;
    }

    public boolean isCurrentTestStarted() {
        return CurrentTestStarted;
    }

    public void setCurrentTestStarted(boolean CurrentTestStarted) {
        this.CurrentTestStarted = CurrentTestStarted;
    }

    public ArrayList<Double> getResultDelete() {
        return ResultDelete;
    }

    public void setResultDelete(ArrayList<Double> ResultDelete) {
        this.ResultDelete = ResultDelete;
    }

    public ArrayList<Double> getResultInsert() {
        return ResultInsert;
    }

    public void setResultInsert(ArrayList<Double> ResultInsert) {
        this.ResultInsert = ResultInsert;
    }

    public ArrayList<Double> getResultSelect() {
        return ResultSelect;
    }

    public void setResultSelect(ArrayList<Double> ResultSelect) {
        this.ResultSelect = ResultSelect;
    }

    public ArrayList<Double> getResultUpdate() {
        return ResultUpdate;
    }

    public void setResultUpdate(ArrayList<Double> ResultUpdate) {
        this.ResultUpdate = ResultUpdate;
    }

    public boolean isHasMonitor() {
        return HasMonitor;
    }

    public void setHasMonitor(boolean HasMonitor) {
        this.HasMonitor = HasMonitor;
    }

    public ArrayList<Long> getResultDeleteCPU() {
        return ResultDeleteCPU;
    }

    public void setResultDeleteCPU(ArrayList<Long> ResultDeleteCPU) {
        this.ResultDeleteCPU = ResultDeleteCPU;
    }

    public ArrayList<Long> getResultDeleteRAM() {
        return ResultDeleteRAM;
    }

    public void setResultDeleteRAM(ArrayList<Long> ResultDeleteRAM) {
        this.ResultDeleteRAM = ResultDeleteRAM;
    }

    public ArrayList<Long> getResultInsertCPU() {
        return ResultInsertCPU;
    }

    public void setResultInsertCPU(ArrayList<Long> ResultInsertCPU) {
        this.ResultInsertCPU = ResultInsertCPU;
    }

    public ArrayList<Long> getResultInsertRAM() {
        return ResultInsertRAM;
    }

    public void setResultInsertRAM(ArrayList<Long> ResultInsertRAM) {
        this.ResultInsertRAM = ResultInsertRAM;
    }

    public ArrayList<Long> getResultSelectCPU() {
        return ResultSelectCPU;
    }

    public void setResultSelectCPU(ArrayList<Long> ResultSelectCPU) {
        this.ResultSelectCPU = ResultSelectCPU;
    }

    public ArrayList<Long> getResultSelectRAM() {
        return ResultSelectRAM;
    }

    public void setResultSelectRAM(ArrayList<Long> ResultSelectRAM) {
        this.ResultSelectRAM = ResultSelectRAM;
    }

    public ArrayList<Long> getResultUpdateCPU() {
        return ResultUpdateCPU;
    }

    public void setResultUpdateCPU(ArrayList<Long> ResultUpdateCPU) {
        this.ResultUpdateCPU = ResultUpdateCPU;
    }

    public ArrayList<Long> getResultUpdateRAM() {
        return ResultUpdateRAM;
    }

    public void setResultUpdateRAM(ArrayList<Long> ResultUpdateRAM) {
        this.ResultUpdateRAM = ResultUpdateRAM;
    }

    public SAnalysisHelper getAnalysisTest1() {
        return AnalysisTest1;
    }

    public void setAnalysisTest1(SAnalysisHelper AnalysisTest1) {
        this.AnalysisTest1 = AnalysisTest1;
    }

    public SAnalysisHelper getAnalysisTest2() {
        return AnalysisTest2;
    }

    public void setAnalysisTest2(SAnalysisHelper AnalysisTest2) {
        this.AnalysisTest2 = AnalysisTest2;
    }

    public int getProgressBarPosition() {
        return ProgressBarPosition;
    }

    public void setProgressBarPosition(int ProgressBarPosition) {
        this.ProgressBarPosition = ProgressBarPosition;
    }

    public String getLogTestContent() {
        return LogTestContent;
    }

    public void setLogTestContent(String LogTestContent) {
        this.LogTestContent = LogTestContent;
    }

    public void appendLogTestContent(String LogTestContent) {
        this.LogTestContent = this.LogTestContent + "\n" + LogTestContent;
    }

    public Thread getProgressThread() {
        return ProgressThread;
    }

    public void setProgressThread(Thread ProgressThread) {
        this.ProgressThread = ProgressThread;
    }
}
