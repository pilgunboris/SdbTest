/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester.factory_beans;

import com.mongodb.DB;
import com.mongodb.Mongo;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import sdbtester.DataBase;
import sdbtester.STestCaseHelper;
import sdbtester.Server;

/**
 *
 * @author boris
 */
public class SDataBaseMongo implements DataBase {

    private static Logger logger = Logger.getLogger(Server.class);
    STestCaseHelper appSettings = STestCaseHelper.getInstance();

    @Override
    public boolean CreateDataBaseConnection(String address, String port, String login, String pass) {
        appSettings.setTestDbAddress(address);
        appSettings.setTestDbPort(port);
        appSettings.setTestDbLogin(login);
        appSettings.setTestDbPass(pass);
        if (!checkForConnectionParams()) {
            return false;
        }
        if (appSettings.getTestCurrentDbName().equals("")) {
            appSettings.setTestCurrentDbName("testMongo");
        }
        try {
            Mongo m = new Mongo(address, Integer.parseInt(port));
            appSettings.setDbConnection(m);
        } catch (Exception e) {
        }
        return true;
    }

    @Override
    public boolean CreateConnectionForCertainDB(String dbName) {
        try {
            Mongo m = null;
            if (appSettings.getDbConnection() != null) {
                m = (Mongo) appSettings.getDbConnection();
            } else {
                m = new Mongo(appSettings.getTestDbAddress(), Integer.parseInt(appSettings.getTestDbPort()));
                appSettings.setDbConnection(m);
            }
            appSettings.setTestCurrentDbName(dbName);
            DB db = m.getDB(dbName);
            if (db.authenticate(appSettings.getTestDbLogin(), appSettings.getTestDbPass().toCharArray())) {
                return true;
            }
        } catch (Exception e) {
            logger.error("Cannot connect to " + dbName + "\n" + e);
            return false;
        }
        return false;
    }

    @Override
    public boolean checkForConnectionParams() {
        if (appSettings.getTestDbPort().isEmpty() || appSettings.getTestDbAddress().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public long executeDataBaseQuery(String query, String queryType) {
        try {
            if (!checkConnection()) {
                Mongo m = new Mongo(appSettings.getTestDbAddress(), Integer.parseInt(appSettings.getTestDbPort()));
                appSettings.setDbConnection(m);
            } else {
                Mongo m = (Mongo) appSettings.getDbConnection();
            }
//            if (queryType.toLowerCase().equals("select")) {
//                start = System.currentTimeMillis();
//                System.out.println(query);
//                ResultSet rs = stmt.executeQuery(query);
//                end = System.currentTimeMillis();
//            } else {
//                start = System.currentTimeMillis();
//                System.out.println(query);
//                stmt.executeUpdate(query);
//                end = System.currentTimeMillis();
//
//            }
            return -1;
        } catch (Exception e) {
            logger.error("Mongo server is not connectable " + e);
            return -1;
        }
    }

    @Override
    public boolean checkConnection() {
        try {
            DB db = ((Mongo) appSettings.getDbConnection()).getDB(appSettings.getTestCurrentDbName());
            return db != null;
        } catch (Exception e) {
            logger.error("Mongo server is not connectable " + e);
            return false;
        }
    }

    @Override
    public boolean closeConnection() {
        try {
            ((Mongo) appSettings.getDbConnection()).close();
            return true;
        } catch (Exception e) {
            logger.error("Cannot close connection " + e);
            return false;
        }
    }

    @Override
    public ArrayList getDbList() {
        List<String> databases = null;
        try {
            Mongo m = (Mongo) appSettings.getDbConnection();
            databases = m.getDatabaseNames();
            databases.add(0, "----- не выбрано -----");
            return (ArrayList) databases;
        } catch (Exception e) {
            logger.error("Cannot get db list " + e);
            return new ArrayList();
        }
    }
}
