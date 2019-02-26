package system.dao.constants;

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

    //Constants for SQL (RoutingStops)
    public final static String SQL_SELECT_ALL_ROUTING_STOPS = "SELECT * FROM routingstops";
    public final static String SQL_SELECT_ROUTING_STOPS_BY_ID_MINIBUS =
            "SELECT routingstops.id_routing_stop, route_stop_name FROM routingstops\n" +
            "INNER JOIN route_has_routingstop ON routingstops.id_routing_stop = route_has_routingstop.id_routing_stop\n" +
            "INNER JOIN minibuses ON route_has_routingstop.id_route = minibuses.id_route WHERE minibuses.id_minibus = ?";

    //Constants for SQL (Logs)
    public final static String SQL_CLEAN_TABLE = "TRUNCATE TABLE logs;";
    public final static String SQL_CREATE_LOG =
            "INSERT INTO urbantrafficsystem.logs VALUE (0, ?, ?, ?, ?, ?, ?, ?, ?);\n";
}
