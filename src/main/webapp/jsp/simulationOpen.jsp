<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="system.dao.implementationModels.ImplementRouteModel" %>
<%@ page import="system.dao.implementationModels.ImplementRoutingStopModel" %>
<%@ page import="system.models.Route" %>
<%@ page import="system.models.RoutingStop" %>
<%@ page import="system.services.ValidationService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Urban Traffic Simulation</title>
    <meta charset="utf-8" content="text/html">
    <link rel="stylesheet" href="../css/style.css" type="text/css">
    <link rel="icon" type="image/png" href="../icons/1.png" />
    <script type="text/javascript" src="../javascript/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="../javascript/jquery.validate.min.js"></script>
    <script type="text/javascript" src="../javascript/script.js"></script>
</head>

<%
    List<Route> routesList = new ImplementRouteModel().getAllObjects();
    request.setAttribute("routeList", routesList);

    Map<String, List<RoutingStop>> map = new ValidationService().loadData();
    request.setAttribute("map", map);

    List<RoutingStop> routingStopsList = new ImplementRoutingStopModel().getAllObjects();
    request.setAttribute("routingStopsList", routingStopsList);
%>

<body>
    <div class="header">
        <a href="#authorization" id="auth" class="validate">
            <%= ValidationService.getCookie(request, "login") %>
        </a>
        <div class="image">
            <img src="../icons/1.png" height="180" width="210">
        </div>
        <h1 class="name">Urban Traffic Simulation</h1>
    </div>
    <div id="authorization" class="modalDialog">
        <div id="valid">
            <a href="#" title="close" id="close" class="close">x</a>
            <h2>Change user</h2>
            <form id="validationForm" action="/RegistrationServlet" method="post">
                <label for="login"><b>Login:</b></label><br>
                <input type="text" name="login" id="login"/> <br/><br>
                <label for="password"><b>Password:</b></label>
                <input type="password" name="password" id="password"/> <br/>
                <p><div id="war"></div>
                <button type="button" id="sign">Sign in</button>
                <button type="button" id="reg">Sign up</button>
            </form>
        </div>
    </div>
    <div id="main">
        <table class="table_routes">
            <caption>Routes</caption>
            <tr>
                <th>ID Route</th>
                <th>Name</th>
                <th>Fare</th>
                <th>Round</th>
                <th>Frequency</th>
                <th>Notes</th>
                <th>Edit</th>
            </tr>
            <c:forEach items="${routeList}" var="route" >
                <tr>
                    <td><c:out value="${route.idRoute}"/></td>
                    <td><c:out value="${route.routeName}"/></td>
                    <td><c:out value="${route.fare} UAH"/></td>
                    <td><c:out value="${route.roundRoute}"/></td>
                    <td><c:out value="${route.frequency} min"/></td>
                    <td>.....</td>
                    <td>
                        <a href="#edition"><button type="button" id="edit">edit</button></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="buttons">
        <button type="button" id="play">Start simulation</button>
        <button type="button" id="stop">Stop simulation</button>
    </div>
    <div id="edition" class="modalDialog">
        <div id="edit_window">
            <a href="#" title="close" class="close">x</a>
            <table class="table_routes">
                <caption>Editing Routing Stop</caption>
                <tr>
                    <th>ID Routing Stop</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${routingStopsList}" var="j" >
                    <tr>
                        <td><c:out value="${j.idRouteStop}"/></td>
                        <td><c:out value="${j.routeStopName}"/></td>
                        <td><c:out value="${j.address}"/></td>
                        <td>
                            <button type="button" id="delete">delete</button>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td>
                        Auto Increment...
                    </td>
                    <td>
                        <input type="text" placeholder="Name..."/>
                    </td>
                    <td>
                        <input type="text" placeholder="Address..."/>
                    </td>
                    <td>
                        <button type="button" id="add">add</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>
