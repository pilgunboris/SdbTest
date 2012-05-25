/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester.analysis;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import sdbtester.STestCaseHelper;

/**
 *
 * @author boris
 */
public class SAnalysisHelper {

    private String TestCaseName = "";
    private String TestCaseDescription = "";
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
    private STestCaseHelper appSettings = STestCaseHelper.getInstance();
    private static Logger logger = Logger.getLogger(SAnalysisHelper.class);

    public boolean loadTestFromTemplate(String caseName, String dbType) {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(STestCaseHelper.TEST_CASES_MAIN_PATH + dbType + "/" + caseName + "/" + STestCaseHelper.SETTINGS_TEST_CASE_PROP_FILE));

            //test case
            this.setTestCaseName(prop.getProperty(STestCaseHelper.PROPERTY_CASE_NAME));
            this.setTestCaseDescription(prop.getProperty(STestCaseHelper.PROPERTY_CASE_DESCRIPTION));
            //test results
            this.setResultInsert(stringToList(prop.getProperty(STestCaseHelper.PROPERTY_RESULT_INSERT)));
            this.setResultSelect(stringToList(prop.getProperty(STestCaseHelper.PROPERTY_RESULT_SELECT)));
            this.setResultUpdate(stringToList(prop.getProperty(STestCaseHelper.PROPERTY_RESULT_UPDATE)));
            this.setResultDelete(stringToList(prop.getProperty(STestCaseHelper.PROPERTY_RESULT_DELETE)));
            //test results CPU
            this.setResultInsertCPU(stringToList(prop.getProperty(STestCaseHelper.PROPERTY_RESULT_INSERT_CPU)));
            this.setResultSelectCPU(stringToList(prop.getProperty(STestCaseHelper.PROPERTY_RESULT_SELECT_CPU)));
            this.setResultUpdateCPU(stringToList(prop.getProperty(STestCaseHelper.PROPERTY_RESULT_UPDATE_CPU)));
            this.setResultDeleteCPU(stringToList(prop.getProperty(STestCaseHelper.PROPERTY_RESULT_DELETE_CPU)));
            //test results RAM
            this.setResultInsertRAM(stringToList(prop.getProperty(STestCaseHelper.PROPERTY_RESULT_INSERT_RAM)));
            this.setResultSelectRAM(stringToList(prop.getProperty(STestCaseHelper.PROPERTY_RESULT_SELECT_RAM)));
            this.setResultUpdateRAM(stringToList(prop.getProperty(STestCaseHelper.PROPERTY_RESULT_UPDATE_RAM)));
            this.setResultDeleteRAM(stringToList(prop.getProperty(STestCaseHelper.PROPERTY_RESULT_DELETE_RAM)));

        } catch (IOException e) {
            logger.warn("Cannot load properties  \n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "Cannot find properties.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private String listToString(ArrayList list) {
        String res = "";
        if (list.isEmpty()) {
            return res;
        }
        for (int i = 0; i < list.size(); ++i) {
            res += list.get(i).toString() + STestCaseHelper.ARRAY_DELIM;
        }
        return res;
    }

    private ArrayList stringToList(String str) {
        if (str == null || str.equals("")) {
            return new ArrayList<String>();
        }
        ArrayList<Object> res = null;
        try {
            res = new ArrayList<Object>(Arrays.asList(str.split(STestCaseHelper.ARRAY_DELIM)));
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "Not enough mana!", JOptionPane.ERROR_MESSAGE);
        }
        return res;
    }

    public ArrayList<Double> getResultDelete() {
        return ResultDelete;
    }

    public void setResultDelete(ArrayList<Double> ResultDelete) {
        this.ResultDelete = ResultDelete;
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

    public ArrayList<Double> getResultInsert() {
        return ResultInsert;
    }

    public void setResultInsert(ArrayList<Double> ResultInsert) {
        this.ResultInsert = ResultInsert;
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

    public ArrayList<Double> getResultSelect() {
        return ResultSelect;
    }

    public void setResultSelect(ArrayList<Double> ResultSelect) {
        this.ResultSelect = ResultSelect;
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

    public ArrayList<Double> getResultUpdate() {
        return ResultUpdate;
    }

    public void setResultUpdate(ArrayList<Double> ResultUpdate) {
        this.ResultUpdate = ResultUpdate;
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

    public String getTestCaseDescription() {
        return TestCaseDescription;
    }

    public void setTestCaseDescription(String TestCaseDescription) {
        this.TestCaseDescription = TestCaseDescription;
    }

    public String getTestCaseName() {
        return TestCaseName;
    }

    public void setTestCaseName(String TestCaseName) {
        this.TestCaseName = TestCaseName;
    }
}
