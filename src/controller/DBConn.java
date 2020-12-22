package controller;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 *
 * @author andre
 */
public class DBConn {
    public void db_connection(MysqlDataSource data) {
        String DB_URL = "jdbc:mysql://localhost:3306/db_pbo_tubes";
        String DB_Username = "root";

        data.setUrl(DB_URL);
        data.setUser(DB_Username);
        data.setPassword("");
    }
}
