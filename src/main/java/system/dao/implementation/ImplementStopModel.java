package system.dao.implementation;

import system.dao.closing.Closing;
import system.dao.connection.ConnectionJDBC;
import system.dao.constants.Constants;
import system.dao.factory.DaoFactory;
import system.models.Stop;
import system.statistic.entities.StopLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplementStopModel implements DaoFactory<Stop> {

    @Override
    public boolean create(Stop stop) throws Exception {
        Connection connection = new ConnectionJDBC().getConnection();
        PreparedStatement preparedStatement1 = null, preparedStatement2 = null;
        ResultSet resultSet = null;

        try {
            connection.setAutoCommit(false);

            preparedStatement1 = connection.prepareStatement(Constants.SQL_GET_STOP);
            preparedStatement1.setInt(1, stop.getId());
            resultSet = preparedStatement1.executeQuery();

            if (resultSet.next()) {
                throw new Exception();
            }

            preparedStatement2 = connection.prepareStatement(Constants.SQL_CREATE_NEW_STOP);
            preparedStatement2.setInt(1, stop.getId());
            preparedStatement2.setString(2, stop.getName());
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
    public Stop read() {
        return null;
    }

    @Override
    public List<Stop> getAllObjects() {
        List<Stop> stopsList = null;
        Connection connection = new ConnectionJDBC().getConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(Constants.SQL_SELECT_ALL_STOPS);
            resultSet = preparedStatement.executeQuery();

            Stop route;
            stopsList = new ArrayList<>();

            while (resultSet.next()) {
                route = new Stop(resultSet.getInt("id_routing_stop"), resultSet.getString("route_stop_name"));
                stopsList.add(route);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            Closing.close(connection);
            Closing.close(preparedStatement);
            Closing.close(resultSet);
        }

        return stopsList;
    }

    public List<Stop> getRoutingStopsByIdMinibus(int id){
        List<Stop> routingStops = new ArrayList<>();
        Connection connection = new ConnectionJDBC().getConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Stop routingStop;

        try {
            preparedStatement = connection.prepareStatement(Constants.SQL_SELECT_STOPS_BY_ID_MINIBUS);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                routingStop = new Stop(resultSet.getInt("id_routing_stop"), resultSet.getString("route_stop_name"));
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

    public void addStopLog(StopLog log) {
        PreparedStatement preparedStatement = null;
        Connection connection = new ConnectionJDBC().getConnection();

        try {
            preparedStatement = connection.prepareStatement(Constants.SQL_CREATE_LOG);
            preparedStatement.setString(1, log.getTime());
            preparedStatement.setInt(2, log.getIdRoute());
            preparedStatement.setInt(3, log.getIdStop());
            preparedStatement.setInt(4, log.getIdMinibus());
            preparedStatement.setInt(5, log.getLoadedPassenger());
            preparedStatement.setInt(6, log.getUnloadedPassenger());
            preparedStatement.setInt(7, log.getLoadLevel());
            preparedStatement.setString(8, log.getLoadingPercent() + "%");
            preparedStatement.executeUpdate();

        }catch(Exception ex){
            ex.printStackTrace();

        } finally {
            Closing.close(connection);
            Closing.close(preparedStatement);
        }
    }

    public void clean(){
        PreparedStatement preparedStatement = null;
        Connection connection = new ConnectionJDBC().getConnection();

        try {
            preparedStatement = connection.prepareStatement(Constants.SQL_CLEAN_TABLE);
            preparedStatement.executeUpdate();

        } catch(Exception ex){
            ex.printStackTrace();

        } finally {
            Closing.close(preparedStatement);
            Closing.close(connection);
        }
    }
}
