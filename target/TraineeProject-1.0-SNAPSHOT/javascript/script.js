$(document).ready(function () {

    $('#sign').click(function () {
        var login = $('#login').val();
        var password = $('#password').val();
        $.ajax({
            type: 'POST',
            data: {
                login: login,
                password: password
            },
            url: '/validation-user',
            success: function (data) {
                if (data === "true") {
                    document.getElementById("login").style.borderColor = "#13fa00";
                    document.getElementById("password").style.borderColor = "#13fa00";
                    $("#war").text("You logged in!");
                    location.reload();
                } else if (data === "false") {
                    document.getElementById("login").style.borderColor = "#FF0000";
                    document.getElementById("password").style.borderColor = "#FF0000";
                    $("#war").text("User doesn't exist!");
                } else if (data === "empty") {
                    document.getElementById("login").style.borderColor = "#FFA500";
                    document.getElementById("password").style.borderColor = "#FFA500";
                    $("#war").text("All fields must be filled!");
                }
            },
            error: function () {
                alert("Ups... Something gone not right!");
            }

        });
    });

    $('#reg').click(function () {
        var login = $('#login').val();
        var password = $('#password').val();
        $.ajax({
            type: 'POST',
            data: {
                login: login,
                password: password
            },
            url: '/registration-user',
            success: function (date) {
                if (date === "true") {
                    document.getElementById("validationForm").reset();
                    $('#login').css({
                        "border-color": "transparent"
                    });
                    $('#password').css({
                        "border-color": "transparent"
                    });
                    $("#war").text("");
                    alert("Success!");
                } else if (date === "empty") {
                    document.getElementById("login").style.borderColor = "#FFA500";
                    document.getElementById("password").style.borderColor = "#FFA500";
                    $("#war").text("All fields must be filled!");
                } else {
                    document.getElementById("validationForm").reset();
                    $('#login').css({
                        "border-color": "transparent"
                    });
                    $('#password').css({
                        "border-color": "transparent"
                    });
                    $("#war").text("");
                    alert("Error! User already exist!");
                }
            }
        });
    });

    $('#close').click(function () {
        document.getElementById("validationForm").reset();
        $('.modalDialog').css({
           "display" : "none"
        });
        $('#login').css({
            "border-color": "transparent"
        });
        $('#password').css({
            "border-color": "transparent"
        });
        $("#war").text("");
    });

    $('#play').click(function () {
        $.ajax({
            type: 'POST',
            url: '/simulation-started',
            success: function () {
                alert("Simulation finished!");
            },
            error: function () {
                alert("Simulation didn't start!");
            }
        });
    });

    $('#logout').click(function () {
        $.ajax({
            type: 'POST',
            url: '/logout',
            success: function () {
                location = '../jsp/simulation.jsp';
            }
        });
    });

    $('#auth').click(function () {
       $('.modalDialog').css({
           "display" : "block",
           "pointer-events" : "auto"
       });
    });
});
