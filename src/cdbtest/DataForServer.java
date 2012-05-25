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
    public static final byte ERROR_STATE = 37;
    //from monitor
    public static final byte MONITOR_END_OF_INSERT = 10;
    public static final byte MONITOR_END_OF_SELECT = 11;
    public static final byte MONITOR_END_OF_UPDATE = 12;
    public static final byte MONITOR_END_OF_DELETE = 13;
    //class fields
    private byte TestStatus = -1;
    private ArrayList<Long> testResult = new ArrayList<Long>(0);
    private ArrayList<Long> testResultCPU = new ArrayList<Long>(0);
    private ArrayList<Long> testResultRAM = new ArrayList<Long>(0);
    private boolean Monitor = false;

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

    public boolean isMonitor() {
        return Monitor;
    }

    public void setMonitor(boolean Monitor) {
        this.Monitor = Monitor;
    }

    public ArrayList<Long> getTestResultCPU() {
        return testResultCPU;
    }

    public void setTestResultCPU(ArrayList<Long> testResultCPU) {
        this.testResultCPU = testResultCPU;
    }

    public ArrayList<Long> getTestResultRAM() {
        return testResultRAM;
    }

    public void setTestResultRAM(ArrayList<Long> testResultRAM) {
        this.testResultRAM = testResultRAM;
    }
}
