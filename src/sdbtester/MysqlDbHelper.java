/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author boris
 */
public class MysqlDbHelper {

    //constants
    public final String DB_DRIVER_MYSQL = "com.mysql.jdbc.Driver";
    ////
    private static Logger logger = Logger.getLogger(MysqlDbHelper.class);
    private TestCaseHelper appSettings = null;

    public MysqlDbHelper() {
        appSettings = TestCaseHelper.getInstance();
    }

    public boolean CreateConnectionMysql(String address, String port, String login, String pass) {
        appSettings.setTestDbAddress(address);
        appSettings.setTestDbPort(port);
        appSettings.setTestDbLogin(login);
        appSettings.setTestDbPass(pass);
        if (!checkForConnectionParams()) {
            return false;
        }
        if (appSettings.getTestCurrentDbName().equals("")) {
            appSettings.setTestCurrentDbName("mysql");
        }
        try {
            if (appSettings.getDbConnection() != null) {
                appSettings.getDbConnection().close();
            }
            Class.forName(DB_DRIVER_MYSQL);
            String DbUrl = "jdbc:mysql://" + appSettings.getTestDbAddress().toString() + ":" + appSettings.getTestDbPort() + "/" + appSettings.getTestCurrentDbName();
            logger.info(DbUrl);
            appSettings.setDbConnection(DriverManager.getConnection(DbUrl, appSettings.getTestDbLogin(), appSettings.getTestDbPass()));
            logger.info("Connected to " + appSettings.getTestCurrentDbName());
        } catch (Exception e) {
            logger.warn("Cannot connect to mysql DB  \n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage(), "Cannot connect to mysql DB!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean CreateConnectionMysqlForCertainDB(String dbName) {
        appSettings.setTestCurrentDbName(dbName);
        return this.CreateConnectionMysql(
                appSettings.getTestDbAddress(),
                appSettings.getTestDbPort(),
                appSettings.getTestDbLogin(),
                appSettings.getTestDbPass());
    }

    public ArrayList getDbList() {
        ArrayList<String> databases = new ArrayList<String>();
        databases.add("----- не выбрано -----");
        try {
            Statement stmt = appSettings.getDbConnection().createStatement();
            ResultSet schemas = stmt.executeQuery("SHOW DATABASES;");
            while (schemas.next()) {
                if (!Arrays.asList(new String[]{"mysql", "information_schema", "performance_schema"}).contains(schemas.getString(1).toString().trim())) {
                    databases.add(schemas.getString(1));
                }
            }
        } catch (Exception e) {
            logger.warn("Cannot store properties  \n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "Cannot store properties.", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<String>();
        }
        appSettings.storeTestCaseSettings(null, null);
        return databases;
    }

    private boolean checkForConnectionParams() {
        if (appSettings.getTestDbPort().isEmpty()
                || appSettings.getTestDbAddress().isEmpty()
                || appSettings.getTestDbLogin().isEmpty()) {
            return false;
        }
        return true;
    }

    public long checkQuery(String query, String queryType) {
        if (!checkConnection()) {
            CreateConnectionMysqlForCertainDB(appSettings.getTestCurrentDbName());
        }
        long start = 0;
        long end = 0;
        try {
            Statement stmt = appSettings.getDbConnection().createStatement();
            if (queryType.toLowerCase().equals("select")) {
                start = System.currentTimeMillis();
                ResultSet rs = stmt.executeQuery(query);
                end = System.currentTimeMillis();
            } else {
                start = System.currentTimeMillis();
                stmt.executeUpdate(query);
                end = System.currentTimeMillis();

            }
        } catch (SQLException e) {
            logger.warn("Wrong query!  \n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage(), "Wrong query!", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        logger.info("exequted query:    " + query);
        return (end - start);
    }

    public boolean checkConnection() {
        try {
            Statement stmt = appSettings.getDbConnection().createStatement();
            ResultSet schemas = stmt.executeQuery("SHOW TABLES;");
        } catch (SQLException e) {
            logger.warn("Connection is lost!  \n" + e.getStackTrace());
            return false;
        }
        return true;
    }
}
