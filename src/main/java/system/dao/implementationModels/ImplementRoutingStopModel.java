package system.dao.implementationModels;

import system.dao.Closing;
import system.dao.ConnectionJDBC;
import system.dao.Constants;
import system.dao.DaoFactory;
import system.models.RoutingStop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplementRoutingStopModel implements DaoFactory<RoutingStop, Integer> {
    @Override
    public boolean create() throws SQLException {
        return false;
    }

    @Override
    public RoutingStop read(Integer integer) {
        return null;
    }

    @Override
    public void update(RoutingStop model) throws SQLException {

    }

    @Override
    public void delete(RoutingStop model) throws SQLException {

    }

    @Override
    public List<RoutingStop> getAllObjects() {
        List<RoutingStop> routingStopList = null;

        Connection connection = new ConnectionJDBC().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(Constants.SQL_SELECT_ALL_ROUTINGSTOPS);
            resultSet = preparedStatement.executeQuery();

            RoutingStop route;
            routingStopList = new ArrayList<>();

            while (resultSet.next()) {

                route = new RoutingStop(
                        resultSet.getInt("id_route"),
                        resultSet.getString("route_stop_name"),
                        resultSet.getString("address")
                );
                routingStopList.add(route);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Closing.close(connection);
            Closing.close(preparedStatement);
            Closing.close(resultSet);
        }

        return routingStopList;
    }

    public List<RoutingStop> getRoutingStopsById(int id){
        List<RoutingStop> routingStops = new ArrayList<>();

        Connection connection = new ConnectionJDBC().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        RoutingStop routingStop;

        try {
            preparedStatement = connection.prepareStatement(Constants.SQL_SELECT_ROUTING_STOPS_BY_ID_ROUTE);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                routingStop = new RoutingStop(resultSet.getInt("id_routing_stop"),
                        resultSet.getString("route_stop_name"),
                        resultSet.getString("address"));
                routingStops.add(routingStop);
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            Closing.close(connection);
            Closing.close(preparedStatement);
            Closing.close(resultSet);
        }

        return routingStops;
    }
}
