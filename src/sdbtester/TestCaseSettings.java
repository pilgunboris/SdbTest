/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author boris
 */
public class TestCaseSettings {

    //constants
    public final String TEST_CASES_MAIN_PATH = "DbTestCases/";
//    public final String MYSQL_TEST_CASES_PATH = TEST_CASES_MAIN_PATH + this.DB_TYPE_MYSQL + "/";
//    public final String MONGO_TEST_CASES_PATH = TEST_CASES_MAIN_PATH + this.DB_TYPE_MONGO + "/";
    public final String SETTINGS_APP = "app_settings.properties";
    public final String SETTINGS_TEST_CASE = "test_case.properties";
    public final String DB_TYPE_MYSQL = "mysql";     //default
    public final String DB_TYPE_MONGO = "mongo";
    private static Logger logger = Logger.getLogger(TestCaseSettings.class);
    private static TestCaseSettings instance = new TestCaseSettings();
    //fields
    private String TestCaseName = "";
    private String TestDbType = "";

    private TestCaseSettings() {
    }

    public static TestCaseSettings getInstance() {
        return instance;
    }

    public boolean loadTestFromTemplate(String templateName, String caseName, String dbType, boolean MakeCopy) {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("config.properties"));
            if (MakeCopy) {
                if(templateName.equals(caseName)){
                    
                }
                else{
                    
                }
            } else {
                
            }
        } catch (IOException e) {
            logger.error("getInstance  ->  \n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "Not enough mana!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void resetInstance() {
        instance = new TestCaseSettings();
    }

    public boolean setCurrentTestName(String caseName, String dbType) {
        if (this.TestCaseName.equals(caseName)) {
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
            } else {
                if (!caseName.equals(getTestCaseName())) {
                    JOptionPane.showMessageDialog(null, "Такое имя уже существует. Попробуйте другое", "Внимание!", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        } catch (IOException e) {
            logger.error("getInstance  ->  \n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "Not enough mana!", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            logger.error("getInstance  ->  \n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "Not enough mana!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void setTestCaseName(String TestCaseName) {
        this.TestCaseName = TestCaseName;
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
}
