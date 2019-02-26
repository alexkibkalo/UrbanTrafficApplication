package system.dao.connection;

import system.dao.constants.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

    public ConnectionJDBC(){
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