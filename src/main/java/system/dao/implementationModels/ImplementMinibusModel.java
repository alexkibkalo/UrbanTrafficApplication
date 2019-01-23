package system.dao.implementationModels;

import system.dao.Closing;
import system.dao.ConnectionJDBC;
import system.dao.Constants;
import system.dao.DaoFactory;
import system.models.Minibus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplementMinibusModel implements DaoFactory<Minibus, Integer> {
    @Override
    public boolean create() throws SQLException {
        return false;
    }

    @Override
    public Minibus read(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public void update(Minibus model) throws SQLException {

    }

    @Override
    public void delete(Minibus model) throws SQLException {

    }

    @Override
    public List<Minibus> getAllObjects() {
        List<Minibus> minibusList = null;

        Connection connection = new ConnectionJDBC().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(Constants.SQL_SELECT_ALL_MINIBUSES);
            resultSet = preparedStatement.executeQuery();

            Minibus minibus;
            minibusList = new ArrayList<>();

            while (resultSet.next()) {

                minibus = new Minibus(
                        resultSet.getInt("id_minibus"),
                        resultSet.getInt("id_route"),
                        resultSet.getInt("capacity"),
                        resultSet.getString("model")
                        );
                minibusList.add(minibus);
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Closing.close(connection);
            Closing.close(preparedStatement);
            Closing.close(resultSet);
        }

        return minibusList;
    }
}
