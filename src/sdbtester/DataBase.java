/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester;

import java.util.ArrayList;

/**
 *
 * @author boris
 */
public interface DataBase {

    public boolean CreateDataBaseConnection(String address, String port, String login, String pass);

    public boolean CreateConnectionForCertainDB(String dbName);

    public ArrayList getDbList();

    public boolean checkForConnectionParams();

    public long executeDataBaseQuery(String query, String queryType);

    public boolean checkConnection();

    public boolean closeConnection();
}
