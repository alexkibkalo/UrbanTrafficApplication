package system.dao;

public class Constants {

    //Constants for connection
    public static final String LOGIN = "root";
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/urbantrafficsystem";
    public static final String DRIVER = "com.mysql.jdbc.Driver";

    //Constants for SQL (Users)
    public final static String SQL_SELECT_ALL_USERS = "SELECT * FROM users";
    public static String SQL_GET_USER = "SELECT * FROM users WHERE (login = ? and password = ?)";
    public final static String SQL_CREATE_NEW_USER = "INSERT INTO users VALUE (?, ?)";

    //Constants for SQL (Routes)
    public final static String SQL_SELECT_ALL_ROUTES = "SELECT * FROM routes";

    //Constants for SQL (RoutingStops)
    public final static String SQL_SELECT_ALL_ROUTINGSTOPS = "SELECT * FROM routingstops";

    //Constants for SQL (Minibuses)
    public final static String SQL_SELECT_ALL_MINIBUSES = "SELECT * FROM minibuses";

    //Constants for SQL (Route_has_RoutingStops)
    public final static String SQL_SELECT_ROUTING_STOPS_BY_ID_ROUTE = "SELECT routingstops.id_routing_stop, route_stop_name, address FROM routingstops \n" +
            "INNER JOIN route_has_routingstop ON routingstops.id_routing_stop = route_has_routingstop.id_routing_stop\n" +
            "WHERE route_has_routingstop.id_route = ?";

}
