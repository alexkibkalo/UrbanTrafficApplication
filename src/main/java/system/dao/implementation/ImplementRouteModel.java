package system.dao.implementation;

import system.dao.closing.Closing;
import system.dao.connection.ConnectionJDBC;
import system.dao.constants.Constants;
import system.dao.factory.DaoFactory;
import system.models.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplementRouteModel implements DaoFactory<Route> {

    @Override
    public boolean create(Route route) throws Exception {
        PreparedStatement preparedStatement1 = null, preparedStatement2 = null;
        ResultSet resultSet = null;
        Connection connection = new ConnectionJDBC().getConnection();

        try {
            connection.setAutoCommit(false);

            preparedStatement1 = connection.prepareStatement(Constants.SQL_GET_ROUTE);
            preparedStatement1.setInt(1, route.getId());
            resultSet = preparedStatement1.executeQuery();

            if (resultSet.next()) {
                throw new Exception();
            }

            preparedStatement2 = connection.prepareStatement(Constants.SQL_CREATE_NEW_ROUTE);
            preparedStatement2.setInt(1, route.getId());
            preparedStatement2.setString(2, route.getName());
            preparedStatement2.setBoolean(3, route.isRound());
            preparedStatement2.setInt(4, route.getFare());
            preparedStatement2.setInt(5, route.getFrequency());
            preparedStatement2.executeUpdate();

            connection.commit();
            return true;

        } catch (Exception ex) {
            connection.rollback();
            return false;

        } finally {
            Closing.close(connection);
            Closing.close(preparedStatement1);
            Closing.close(preparedStatement2);
            Closing.close(resultSet);
        }
    }

    @Override
    public Route read() throws SQLException {
        return null;
    }

    @Override
    public List<Route> getAllObjects() throws SQLException {
        List<Route> routes = null;

        Connection connection = new ConnectionJDBC().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(Constants.SQL_SELECT_ALL_STOPS);
            resultSet = preparedStatement.executeQuery();

            Route route;
            routes = new ArrayList<>();

            while (resultSet.next()) {
                route = new Route(resultSet.getInt("id_routing_stop"),
                        resultSet.getInt("fare"),
                        resultSet.getInt("frequency"),
                        resultSet.getString("route_name"),
                        resultSet.getBoolean("is_round_route"));
                routes.add(route);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            Closing.close(connection);
            Closing.close(preparedStatement);
            Closing.close(resultSet);
        }

        return routes;
    }

    public void addRelation(int idRoute, int idStop) throws SQLException {
        Connection connection = new ConnectionJDBC().getConnection();
        PreparedStatement preparedStatement1 = null, preparedStatement2 = null;
        ResultSet resultSet = null;

        try {
            connection.setAutoCommit(false);

            preparedStatement1 = connection.prepareStatement(Constants.SQL_GET_RELATION);
            preparedStatement1.setInt(1, idRoute);
            preparedStatement1.setInt(2, idStop);
            resultSet = preparedStatement1.executeQuery();

            if (resultSet.next()) {
                System.out.println("EX");
                throw new Exception();
            }

            preparedStatement2 = connection.prepareStatement(Constants.SQL_CREATE_NEW_RELATION);
            preparedStatement2.setInt(1, idRoute);
            preparedStatement2.setInt(2, idStop);
            preparedStatement2.executeUpdate();


            System.out.println("success");
            connection.commit();
        } catch (Exception ex) {
            System.out.println("failed");
            connection.rollback();
        } finally {
            Closing.close(connection);
            Closing.close(preparedStatement1);
            Closing.close(preparedStatement2);
            Closing.close(resultSet);
        }
    }
}
