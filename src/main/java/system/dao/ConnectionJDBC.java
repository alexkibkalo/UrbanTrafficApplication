package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

    private static Connection connection;

    public static Connection getConnection(){
        if(connection == null){
            try {
                connection = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    static {
        try {
            Class.forName(Constants.DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ConnectionJDBC(){ }
}
