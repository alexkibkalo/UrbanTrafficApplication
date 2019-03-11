<%@ page import="system.services.ValidationService" %>
<%@ page import="system.statistic.entities.Statistic" %>
<%@ page import="system.statistic.entities.StopLog" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Urban Traffic Simulation</title>
    <meta charset="utf-8" content="text/html">

    <link rel="stylesheet" href="../css/style.css" type="text/css">
    <link rel="icon" type="image/png" href="../icons/1.png"/>

    <script type="text/javascript" src="../javascript/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="../javascript/jquery.validate.min.js"></script>
    <script type="text/javascript" src="../javascript/script.js"></script>
</head>
<%
    List<StopLog> logs = Statistic.getObject().getLogList();
    request.setAttribute("logs", logs);

    String cookieValue = new ValidationService().getCookie(request, "login");
    request.setAttribute("cookieValue", cookieValue);
%>
<body>
<div class="header">
    <c:if test="${cookieValue.equals('sign in')}">
        <a href="#authorization" id="auth" class="validate">${cookieValue}</a>
    </c:if>
    <c:if test="${!cookieValue.equals('sign in')}">
        <a href="#" id="logout">&nbsp;|&nbsp;<u>logout</u></a>
        <a href="#authorization" id="auth" class="validate">${cookieValue}</a>
    </c:if>
    <div class="image">
        <img src="../icons/1.png" height="180" width="210">
    </div>
    <h1 class="name">Urban Traffic Simulation</h1>
</div>
<div id="authorization" class="modalDialog">
    <div id="valid">
        <a href="#" title="close" id="close" class="close">x</a>
        <c:if test="${cookieValue.equals('sign in')}">
            <h2>Authorization</h2>
        </c:if>
        <c:if test="${!cookieValue.equals('sign in')}">
            <h2>Change user</h2>
        </c:if>
        <form id="validationForm" method="post">
            <label for="login"><b>Login:</b></label><br>
            <input type="text" name="login" id="login"/> <br/><br>
            <label for="password"><b>Password:</b></label>
            <input type="password" name="password" id="password"/> <br/>
            <div id="war"></div>
            <br/>
            <button type="button" id="sign">Sign in</button>
        </form>
    </div>
</div>
<div>
    <c:if test="${cookieValue.equals('sign in')}">
        <div id="message">
            <h1>If you want to use this resource, you have to log into the system!</h1>
        </div>
    </c:if>
</div>
<c:if test="${!cookieValue.equals('sign in')}">
    <div id="buttons">
        <button type="button" id="play">Start simulation</button>
    </div>
    <div id="result">
        <%--<ol>
            <c:forEach items="${logs}" var="log">
                <li>
                    <c:out value="${log.toString()}"/>
                </li>
            </c:forEach>
        </ol>--%>
    </div>
</c:if>
</div>
</body>
</html>