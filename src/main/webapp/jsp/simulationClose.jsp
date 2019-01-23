<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Urban Traffic Simulation</title>
    <link rel="stylesheet" href="../css/style.css" type="text/css">
    <link rel="icon" type="image/png" href="../icons/1.png" />
    <script type="text/javascript" src="../javascript/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="../javascript/jquery.validate.min.js"></script>
    <script type="text/javascript" src="../javascript/script.js"></script>
</head>
<body>
<div class="header">
    <a href="#authorization" id="auth" class="validate">sign in</a>
    <div class="image">
        <img src="../icons/1.png" height="180" width="210">
    </div>
    <h1 class="name">Urban Traffic Simulation</h1>
</div>
<div id="authorization" class="modalDialog">
    <div id="valid">
        <a href="#" title="close" id="close" class="close">x</a>
        <h2>Authorization</h2>
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
<div id="message" class="info">
    <h1>If you want to use this resource, you have to log into the system!</h1>
</div>
</body>
</html>
