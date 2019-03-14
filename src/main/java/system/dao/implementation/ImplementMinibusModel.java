package system.dao.implementation;

import system.dao.closing.Closing;
import system.dao.connection.ConnectionJDBC;
import system.dao.constants.Constants;
import system.dao.factory.DaoFactory;
import system.models.Minibus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplementMinibusModel implements DaoFactory<Minibus> {

    @Override
    public boolean create(Minibus minibus) throws Exception {

        Connection connection = new ConnectionJDBC().getConnection();
        PreparedStatement preparedStatement1 = null, preparedStatement2 = null;
        ResultSet resultSet = null;

        try {
            connection.setAutoCommit(false);

            preparedStatement1 = connection.prepareStatement(Constants.SQL_GET_MINIBUS);
            preparedStatement1.setInt(1, minibus.getId());
            resultSet = preparedStatement1.executeQuery();

            if (resultSet.next()) {
                throw new Exception();
            }

            preparedStatement2 = connection.prepareStatement(Constants.SQL_CREATE_NEW_MINIBUS);
            preparedStatement2.setInt(1, minibus.getId());
            preparedStatement2.setString(2, minibus.getModel());
            preparedStatement2.setInt(3, minibus.getCapacity());
            preparedStatement2.setInt(4, minibus.getIdRoute());
            preparedStatement2.executeUpdate();

            connection.commit();
            return true;

        }catch(Exception ex){
            connection.rollback();
            return false;

        } finally {
            Closing.close(connection);
            Closing.close(preparedStatement1);
            Closing.close(preparedStatement2);
            Closing.close(resultSet);
        }    }

    @Override
    public Minibus read() throws SQLException {
        return null;
    }

    @Override
    public List<Minibus> getAllObjects() {
        List<Minibus> minibuses = null;

        Connection connection = new ConnectionJDBC().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(Constants.SQL_SELECT_ALL_STOPS);
            resultSet = preparedStatement.executeQuery();

            Minibus minibus;
            minibuses = new ArrayList<>();

            while (resultSet.next()) {
                minibus = new Minibus(resultSet.getInt("id_minibus"),
                        resultSet.getInt("capacity"),
                        resultSet.getString("model"));

                minibuses.add(minibus);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            Closing.close(connection);
            Closing.close(preparedStatement);
            Closing.close(resultSet);
        }

        return minibuses;
    }
}
