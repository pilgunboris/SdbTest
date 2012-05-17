/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdbtest;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author boris
 */
public class DataForServer implements Serializable {

    public static final byte READY_FOR_TEST = 0;
    public static final byte END_OF_INSERT = 1;
    public static final byte END_OF_SELECT = 2;
    public static final byte END_OF_UPDATE = 3;
    public static final byte END_OF_DELETE = 4;
    //class fields
    private byte TestStatus = -1;
    private ArrayList<Long> testResult = new ArrayList<Long>(0);

    public byte getTestStatus() {
        return TestStatus;
    }

    public void setTestStatus(byte TestStatus) {
        this.TestStatus = TestStatus;
    }

    public ArrayList<Long> getTestResult() {
        return testResult;
    }

    public void setTestResult(ArrayList<Long> testResult) {
        this.testResult = testResult;
    }
}
