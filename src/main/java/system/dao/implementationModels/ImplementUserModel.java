package system.dao.implementationModels;

import system.InitializationLogging;
import system.dao.Closing;
import system.dao.ConnectionJDBC;
import system.dao.Constants;
import system.dao.DaoFactory;
import system.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

        Connection connection = new ConnectionJDBC().getConnection();
        PreparedStatement preparedStatement1 = null, preparedStatement2 = null;
        ResultSet resultSet = null;

        try {
            connection.setAutoCommit(false);
            preparedStatement1 = connection.prepareStatement(Constants.SQL_GET_USER);
            preparedStatement1.setString(1, login);
            preparedStatement1.setString(2, password);
            resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString("login"), resultSet.getString("password"));
                if(user.getLogin().equals(login)){
                    throw new Exception();
                }
            }

            preparedStatement2 = connection.prepareStatement(Constants.SQL_CREATE_NEW_USER);
            preparedStatement2.setString(1, login);
            preparedStatement2.setString(2, password);
            preparedStatement2.executeUpdate();

            connection.commit();
            InitializationLogging.logger.info("Transaction 'Registration user' successfully executed!");
            return true;
        }catch(Exception ex){
            connection.rollback();
            InitializationLogging.logger.error("Transaction 'Registration user' interrupted!");
            return false;
        } finally {
            Closing.close(connection);
            Closing.close(preparedStatement1);
            Closing.close(preparedStatement2);
            Closing.close(resultSet);
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

        Connection connection = new ConnectionJDBC().getConnection();
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
