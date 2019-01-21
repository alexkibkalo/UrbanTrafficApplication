package dao.implementationModels;

import dao.ConnectionJDBC;
import dao.Constants;
import dao.DaoFactory;
import models.Minibus;

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

        try {
            PreparedStatement preparedStatement = ConnectionJDBC.getConnection().prepareStatement(Constants.SQL_SELECT_ALL_MINIBUSES);
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }

        return minibusList;
    }
}
