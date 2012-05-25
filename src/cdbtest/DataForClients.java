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
public class DataForClients implements Serializable {

    public static final byte MAIN_TEST_DATA = 0;
    public static final byte START_INSERT = 1;
    public static final byte START_SELECT = 2;
    public static final byte START_UPDATE = 3;
    public static final byte START_DELETE = 4;
    public static final byte END_TESTING = 5;
    //only for monitor
//    public static final byte 13
    
    //class fields
    private int ClientThreadsNum = 10;
    private int ClientTestRequestNum = 100;
    private String DbType = "";
    private String DbAdress = "";
    private String DbPort = "";
    private String DbLogin = "";
    private String DbPass = "";
    private String DbName = "";
    private ArrayList<String> QueriesInsert = new ArrayList<String>(0);
    private ArrayList<String> QueriesSelect = new ArrayList<String>(0);
    private ArrayList<String> QueriesUpdate = new ArrayList<String>(0);
    private ArrayList<String> QueriesDelete = new ArrayList<String>(0);
    private byte TestStatus = -1;

    public DataForClients() {
    }

    public int getClientThreadsNum() {
        return ClientThreadsNum;
    }

    public void setClientThreadsNum(int ClientThreadsNum) {
        this.ClientThreadsNum = ClientThreadsNum;
    }

    public String getDbAdress() {
        return DbAdress;
    }

    public void setDbAdress(String DbAdress) {
        this.DbAdress = DbAdress;
    }

    public String getDbLogin() {
        return DbLogin;
    }

    public void setDbLogin(String DbLogin) {
        this.DbLogin = DbLogin;
    }

    public String getDbPass() {
        return DbPass;
    }

    public void setDbPass(String DbPass) {
        this.DbPass = DbPass;
    }

    public String getDbPort() {
        return DbPort;
    }

    public void setDbPort(String DbPort) {
        this.DbPort = DbPort;
    }

    public String getDbType() {
        return DbType;
    }

    public void setDbType(String DbType) {
        this.DbType = DbType;
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

    public int getClientTestRequestNum() {
        return ClientTestRequestNum;
    }

    public void setClientTestRequestNum(int ClientTestRequestNum) {
        this.ClientTestRequestNum = ClientTestRequestNum;
    }

    public byte getTestStatus() {
        return TestStatus;
    }

    public void setTestStatus(byte TestStatus) {
        this.TestStatus = TestStatus;
    }

    public String getDbName() {
        return DbName;
    }

    public void setDbName(String DbName) {
        this.DbName = DbName;
    }
}
