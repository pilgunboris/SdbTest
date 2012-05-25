/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester;

import sdbtester.factory_beans.SDataBaseMongo;
import sdbtester.factory_beans.SDataBaseMysql;

/**
 *
 * @author boris
 */
public class SDataBaseFactory {
    
    public static DataBase getDataBase(String name) {
        if (name.equals(STestCaseHelper.DB_TYPE_MYSQL)) {
            return new SDataBaseMysql();
        } else if (name.equals(STestCaseHelper.DB_TYPE_MONGO)) {
            return new SDataBaseMongo();
        }
        return null;
    }
}
