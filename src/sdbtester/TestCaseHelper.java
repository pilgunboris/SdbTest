/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester;

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
    public final String TEST_CASES_MAIN_PATH = "DbTestCases/";
    public final String SETTINGS_APP_PROP_FILE = "app_settings.properties";
    public final String SETTINGS_TEST_CASE_PROP_FILE = "test_case.properties";
    public final String DB_TYPE_MYSQL = "mysql";     //default
    public final String DB_TYPE_MONGO = "mongo";
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
    private static final String ARRAY_DELIM = "!&!";
    //fields
    private String TestCaseName = "";
    private String TestCaseDescription = "";
    private String TestDbType = "";
//    private String TestDbName = "";
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
//initialized fields
    private static Logger logger = Logger.getLogger(TestCaseHelper.class);
    private static TestCaseHelper instance = new TestCaseHelper();

    private TestCaseHelper() {
    }

    public static TestCaseHelper getInstance() {
        return instance;
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
//    public String getTestDbName() {
//        return TestDbName;
//    }
//
//    public void setTestDbName(String TestDbName) {
//        this.TestDbName = TestDbName;
//    }
}
