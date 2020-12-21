package controller;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 *
 * @author andre
 */
public class DBConn {
    private final String DB_URL = "jdbc:mysql://localhost:3306/db_pbo_tubes";
    private final String DB_Username = "root";
    private final String DB_Pass = "";
    
    public void db_connection(MysqlDataSource data) {
        data.setUrl(DB_URL);
        data.setUser(DB_Username);
        data.setPassword(DB_Pass);
    }
}
