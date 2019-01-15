package dao.implementationModels;

import dao.ConnectionJDBC;
import dao.Constants;
import dao.DaoFactory;
import models.RoutingStop;

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

        try {
            PreparedStatement preparedStatement = ConnectionJDBC.getConnection().prepareStatement(Constants.SQL_SELECT_ALL_ROUTINGSTOPS);
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        return routingStopList;
    }

    public List<RoutingStop> getRoutingStopsById(int id){
        List<RoutingStop> routingStops = new ArrayList<>();
        RoutingStop routingStop;
        try {
            PreparedStatement preparedStatement1 = ConnectionJDBC.getConnection()
                    .prepareStatement(Constants.SQL_SELECT_ROUTING_STOPS_BY_ID_ROUTE);
            preparedStatement1.setInt(1, id);
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                routingStop = new RoutingStop(resultSet.getInt("id_routing_stop"),
                        resultSet.getString("route_stop_name"),
                        resultSet.getString("address"));
                routingStops.add(routingStop);
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return routingStops;
    }
}
