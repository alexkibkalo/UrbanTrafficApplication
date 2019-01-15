package dao.implementationModels;

import dao.ConnectionJDBC;
import dao.Constants;
import dao.DaoFactory;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static classes.InitializationLogging.logger;

public class ImplementUserModel implements DaoFactory<User, Integer> {

    private String login;
    private String password;

    public ImplementUserModel() {
    }

    public ImplementUserModel(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean create() throws SQLException {
        Connection connection = ConnectionJDBC.getConnection();

        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement1 = connection.prepareStatement(Constants.SQL_GET_USER);
            preparedStatement1.setString(1, login);
            preparedStatement1.setString(2, password);
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString("login"), resultSet.getString("password"));
                if(user.getLogin().equals(login)){
                    throw new Exception();
                }
            }

            PreparedStatement preparedStatement2 = ConnectionJDBC.getConnection().prepareStatement(Constants.SQL_CREATE_NEW_USER);
            preparedStatement2.setString(1, login);
            preparedStatement2.setString(2, password);
            preparedStatement2.executeUpdate();

            connection.commit();
            logger.info("Transaction 'Registration user' successfully executed!");
            return true;
        }catch(Exception ex){
            connection.rollback();
            logger.error("Transaction 'Registration user' interrupted!");
            return false;
        } finally {
            connection.close();
        }
    }

    @Override
    public User read(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public void update(User model) throws SQLException {

    }

    @Override
    public void delete(User model) throws SQLException {

    }

    @Override
    public List<User> getAllObjects() {

        List<User> userList = null;


        try {
            PreparedStatement preparedStatement = ConnectionJDBC.getConnection().prepareStatement(Constants.SQL_SELECT_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();

            User user;
            userList = new ArrayList<>();

            while (resultSet.next()) {

                user = new User(resultSet.getString("login"), resultSet.getString("password"));
                userList.add(user);
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        return userList;
    }
}
