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

    //Constants for SQL (Stops)
    public final static String SQL_GET_STOP = "SELECT * FROM stops WHERE id_routing_stop = ?";
    public final static String SQL_CREATE_NEW_STOP = "INSERT INTO stops VALUE (?, ?)";
    public final static String SQL_SELECT_ALL_STOPS = "SELECT * FROM stops";
    public final static String SQL_SELECT_STOPS_BY_ID_MINIBUS =
            "SELECT stops.id_routing_stop, route_stop_name FROM stops\n" +
            "INNER JOIN route_has_routingstop ON stops.id_routing_stop = route_has_routingstop.id_routing_stop\n" +
            "INNER JOIN minibuses ON route_has_routingstop.id_route = minibuses.id_route WHERE minibuses.id_minibus = ?";

    //Constants for SQL (Logs)
    public final static String SQL_CLEAN_TABLE = "TRUNCATE TABLE logs;";
    public final static String SQL_CREATE_LOG =
            "INSERT INTO urbantrafficsystem.logs VALUE (0, ?, ?, ?, ?, ?, ?, ?, ?);\n";

    //Constants for SQL (Minibuses)
    public static String SQL_GET_MINIBUS = "SELECT * FROM minibuses WHERE (id_minibus = ?)";
    public final static String SQL_CREATE_NEW_MINIBUS =
            "INSERT INTO minibuses VALUE (?, ?, ?, ?)";

    //Constants for SQL (Routes)
    public final static String SQL_SELECT_ALL_ROUTES = "SELECT * FROM routes";
    public static String SQL_GET_ROUTE = "SELECT * FROM routes WHERE (id_route = ?)";
    public final static String SQL_CREATE_NEW_ROUTE = "INSERT INTO routes VALUE (?, ?, ?, ?, ?)";

    //Constants for SQL (Route_has_Stop)
    public static String SQL_GET_RELATION = "SELECT * FROM route_has_routingstop WHERE (id_route = ? and id_routing_stop = ?)";
    public final static String SQL_CREATE_NEW_RELATION = "INSERT INTO route_has_routingstop VALUE (?, ?)";
}
