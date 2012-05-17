/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester;

import cdbtest.DataForClients;
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

/**
 *
 * @author boris
 */
public class TestCaseHelper {

    //constants
    public static final String TEST_CASES_MAIN_PATH = "DbTestCases/";
    public static final String SETTINGS_APP_PROP_FILE = "global_settings.properties";
    public static final String SETTINGS_TEST_CASE_PROP_FILE = "test_case.properties";
    public static final String DB_TYPE_MYSQL = "mysql";     //default
    public static final String DB_TYPE_MONGO = "mongo";
    //properties constants
    private static final String PROPERTY_CASE_NAME = "test_case.name";
    private static final String PROPERTY_CASE_DESCRIPTION = "test_case.description";
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
    private static final String ARRAY_DELIM = "!&!";
    //global properties
    private static final String PROPERTY_APP_SERVER_PORT = "app.server.port";
    //test case fields
    private String TestCaseName = "";
    private String TestCaseDescription = "";
    private String TestDbType = "";
    private Connection DbConnection = null;
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
    private int ClientTestRequestNum = 100;
    private boolean HasInsert = true;
    private boolean HasSelect = true;
    private boolean HasUpdate = true;
    private boolean HasDelete = true;
    //global fields
//    private 
    private int AppServerPort = 3223;
    private int CurrentTestStatus = -1;
//initialized fields
    private static Logger logger = Logger.getLogger(TestCaseHelper.class);
    private static TestCaseHelper instance = new TestCaseHelper();

    private TestCaseHelper() {
    }

    public static TestCaseHelper getInstance() {
        return instance;
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
//            DataSetForClients.setTestStatus(this.);
        } catch (Exception e) {
            logger.warn("setDataForClientsFields  \n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "setDataForClientsFields", JOptionPane.ERROR_MESSAGE);
            return false;
        }
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

//    public boolean
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

    private String listToString(ArrayList<String> list) {
        String res = "";
        if (list.isEmpty()) {
            return res;
        }
        for (int i = 0; i < list.size(); ++i) {
            res += list.get(i) + ARRAY_DELIM;
        }
        return res;
    }

    private ArrayList<String> stringToList(String str) {
        if (str.equals("") || str == null) {
            return new ArrayList<String>();
        }
        ArrayList<String> res = null;
        try {
            res = new ArrayList<String>(Arrays.asList(str.split(ARRAY_DELIM)));
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
        if(has == null){
            return true;
        }
        return (has.equals("0") ? false : true);
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

    public Connection getDbConnection() {
        return DbConnection;
    }

    public void setDbConnection(Connection DbConnection) {
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

//    public boolean isFlagTestStarted() {
//        return FlagTestStarted;
//    }
//
//    public void setFlagTestStarted(boolean FlagTestStarted) {
//        this.FlagTestStarted = FlagTestStarted;
//    }
    public int getCurrentTestStatus() {
        return CurrentTestStatus;
    }

    public void setCurrentTestStatus(int CurrentTestStatus) {
        this.CurrentTestStatus = CurrentTestStatus;
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
}
