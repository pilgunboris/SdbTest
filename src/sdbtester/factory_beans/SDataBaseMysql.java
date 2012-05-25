/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester.factory_beans;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import sdbtester.DataBase;
import sdbtester.STestCaseHelper;

/**
 *
 * @author boris
 */
public class SDataBaseMysql implements DataBase {

    //constants
    public final String DB_DRIVER_MYSQL = "com.mysql.jdbc.Driver";
    ////
    private static Logger logger = Logger.getLogger(SDataBaseMysql.class);
    private STestCaseHelper appSettings = null;

    public SDataBaseMysql() {
        appSettings = STestCaseHelper.getInstance();
    }

    @Override
    public boolean CreateDataBaseConnection(String address, String port, String login, String pass) {
        appSettings.setTestDbAddress(address);
        appSettings.setTestDbPort(port);
        appSettings.setTestDbLogin(login);
        appSettings.setTestDbPass(pass);
        Connection con = null;
        if (!checkForConnectionParams()) {
            return false;
        }
        if (appSettings.getTestCurrentDbName().equals("")) {
            appSettings.setTestCurrentDbName("mysql");
        }
        try {
            try {
                if (appSettings.getDbConnection() != null) {
                    ((Connection) appSettings.getDbConnection()).close();
                }
            } catch (Exception e) {
                logger.error("Cannot close connection");
            }
            Class.forName(DB_DRIVER_MYSQL);
            String DbUrl = "jdbc:mysql://" + appSettings.getTestDbAddress().toString() + ":" + appSettings.getTestDbPort() + "/" + appSettings.getTestCurrentDbName();
            logger.info(DbUrl);
            con = DriverManager.getConnection(DbUrl, appSettings.getTestDbLogin(), appSettings.getTestDbPass());
            logger.info("Connected to " + appSettings.getTestCurrentDbName());
        } catch (Exception e) {
            logger.warn("Cannot connect to mysql DB  \n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage(), "Cannot connect to mysql DB!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        appSettings.setDbConnection(con);
        return true;
    }

    @Override
    public boolean CreateConnectionForCertainDB(String dbName) {
        appSettings.setTestCurrentDbName(dbName);
        return this.CreateDataBaseConnection(
                appSettings.getTestDbAddress(),
                appSettings.getTestDbPort(),
                appSettings.getTestDbLogin(),
                appSettings.getTestDbPass());
    }

    @Override
    public ArrayList getDbList() {
        ArrayList<String> databases = new ArrayList<String>();
        databases.add("----- не выбрано -----");
        try {
            Statement stmt = ((Connection) appSettings.getDbConnection()).createStatement();
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

    @Override
    public boolean checkForConnectionParams() {
        if (appSettings.getTestDbPort().isEmpty()
                || appSettings.getTestDbAddress().isEmpty()
                || appSettings.getTestDbLogin().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public long executeDataBaseQuery(String query, String queryType) {
        if (!checkConnection()) {
            CreateConnectionForCertainDB(appSettings.getTestCurrentDbName());
        }
        long start;
        long end;
        try {
            Statement stmt = ((Connection) appSettings.getDbConnection()).createStatement();
            if (queryType.toLowerCase().equals("select")) {
                start = System.currentTimeMillis();
                System.out.println(query);
                ResultSet rs = stmt.executeQuery(query);
                end = System.currentTimeMillis();
            } else {
                start = System.currentTimeMillis();
                System.out.println(query);
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

    @Override
    public boolean checkConnection() {
        try {
            Statement stmt = ((Connection) appSettings.getDbConnection()).createStatement();
            ResultSet schemas = stmt.executeQuery("SHOW TABLES;");
        } catch (SQLException e) {
            logger.warn("Connection is lost!  \n" + e.getStackTrace());
            return false;
        }
        return true;
    }

    @Override
    public boolean closeConnection() {
        try {
            Connection con = (Connection) appSettings.getDbConnection();
            con.close();
        } catch (Exception e) {
            logger.info("Cannot close connection " + e.getStackTrace());
            return false;
        }
        return true;
    }
}
