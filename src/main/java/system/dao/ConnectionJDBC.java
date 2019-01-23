package system.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

    static {
        try {
            Class.forName(Constants.DRIVER);
        } catch (ClassNotFoundException ignore) {
        }
    }

    public Connection getConnection() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(Constants.URL, Constants.LOGIN, Constants.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}