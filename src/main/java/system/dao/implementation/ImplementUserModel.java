package system.dao.implementation;

import system.dao.closing.Closing;
import system.dao.connection.ConnectionJDBC;
import system.dao.constants.Constants;
import system.dao.factory.DaoFactory;
import system.models.User;
import system.services.LoggingService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplementUserModel implements DaoFactory<User> {

    private Connection connection = new ConnectionJDBC().getConnection();
    private String login;
    private String password;

    public ImplementUserModel(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean create(User user) throws SQLException {

        PreparedStatement preparedStatement1 = null, preparedStatement2 = null;
        ResultSet resultSet = null;

        try {
            connection.setAutoCommit(false);

            preparedStatement1 = connection.prepareStatement(Constants.SQL_GET_USER);
            preparedStatement1.setString(1, login);
            preparedStatement1.setString(2, password);

            resultSet = preparedStatement1.executeQuery();

            if (resultSet.next()) {
                throw new Exception();
            }

            preparedStatement2 = connection.prepareStatement(Constants.SQL_CREATE_NEW_USER);
            preparedStatement2.setString(1, login);
            preparedStatement2.setString(2, password);
            preparedStatement2.executeUpdate();

            connection.commit();

            LoggingService.logger.info("User '" + login + "' logged up!");
            return true;

        }catch(Exception ex){
            connection.rollback();

            LoggingService.logger.warn("User '" + login + "' tried to registration! Something went wrong...");
            return false;

        } finally {
            Closing.close(connection);
            Closing.close(preparedStatement1);
            Closing.close(preparedStatement2);
            Closing.close(resultSet);
        }
    }

    @Override
    public User read() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        User user = null;

        try {
            preparedStatement = connection.prepareStatement(Constants.SQL_GET_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user = new User(resultSet.getString("login"), resultSet.getString("password"));
            }

        }catch (SQLException ex) {
            ex.printStackTrace();

        }finally {
            Closing.close(connection);
            Closing.close(preparedStatement);
            Closing.close(resultSet);
        }

        return user;
    }

    @Override
    public List<User> getAllObjects() {
        List<User> userList = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(Constants.SQL_SELECT_ALL_USERS);
            resultSet = preparedStatement.executeQuery();

            User user;
            userList = new ArrayList<>();

            while (resultSet.next()) {

                user = new User(resultSet.getString("login"), resultSet.getString("password"));
                userList.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            Closing.close(connection);
            Closing.close(preparedStatement);
            Closing.close(resultSet);
        }

        return userList;
    }
}
