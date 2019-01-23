package system.dao.implementationModels;

import system.dao.Closing;
import system.dao.ConnectionJDBC;
import system.dao.Constants;
import system.dao.DaoFactory;
import system.models.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplementRouteModel implements DaoFactory<Route, Integer> {
    @Override
    public boolean create() throws SQLException {
        return false;
    }

    @Override
    public Route read(Integer integer) {
        return null;
    }

    @Override
    public void update(Route model) throws SQLException {

    }

    @Override
    public void delete(Route model) {

    }

    @Override
    public List<Route> getAllObjects() {
        List<Route> routeList = null;

        Connection connection = new ConnectionJDBC().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(Constants.SQL_SELECT_ALL_ROUTES);
            resultSet = preparedStatement.executeQuery();

            Route route;
            routeList = new ArrayList<>();

            while (resultSet.next()) {

                route = new Route(
                        resultSet.getInt("id_route"),
                        resultSet.getInt("fare"),
                        resultSet.getInt("frequency"),
                        resultSet.getString("route_name"),
                        resultSet.getBoolean("is_round_route")
                );
                routeList.add(route);
            }
        }catch (SQLException ex) {
            Closing.close(connection);
            Closing.close(preparedStatement);
            Closing.close(resultSet);
        }

        return routeList;
    }
}

