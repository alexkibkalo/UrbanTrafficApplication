package dao.implementationModels;

import dao.ConnectionJDBC;
import dao.Constants;
import dao.DaoFactory;
import models.Route;

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

        try {
            PreparedStatement preparedStatement = ConnectionJDBC.getConnection().prepareStatement(Constants.SQL_SELECT_ALL_ROUTES);
            ResultSet resultSet = preparedStatement.executeQuery();

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
            ex.printStackTrace();
        }

        return routeList;
    }
}

