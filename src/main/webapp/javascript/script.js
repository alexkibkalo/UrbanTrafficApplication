$(document).ready(function(){
    $('#sign').click(function(){
        var login = $('#login').val();
        var password = $('#password').val();
        $.ajax({
            type: 'POST',
            data: {
                login: login,
                password: password
            },
            url: '/validation-user',
            success: function(date) {
                funSuccessSignIn(date);
                if(date === "true"){
                    window.location.href = "../jsp/simulationOpen.jsp";
                }
            },
            error: function () {
                alert("Ups... Something gone not right!");
            }

        });
    });

    $('#reg').click(function(){
        var login = $('#login').val();
        var password = $('#password').val();
        $.ajax({
            type: 'POST',
            data: {
                login: login,
                password: password
            },
            url: 'RegistrationServlet',
            success: function(date) {
                if(date === "true"){
                    document.getElementById("validationForm").reset();
                    $('#login').css({
                        "border-color": "transparent"
                    });
                    $('#password').css({
                        "border-color": "transparent"
                    });
                    $("#war").text("");
                    alert("Success!");
                } else if (date === "empty"){
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

    /*$('#more').click(function() {
        var className = content.className;
        if( className.indexOf(' expanded') === -1 ){
            className += ' expanded';
        }
        else {
            className = className.replace(' expanded', '');
        }
        content.className = className;
        return false;
    });
*/
    $('#close').click(function (){
        document.getElementById("validationForm").reset();
        $('#login').css({
            "border-color": "transparent"
        });
        $('#password').css({
            "border-color": "transparent"
        });
        $("#war").text("");
    });

});


function funSuccessSignIn(data){
    if(data === "true"){
        document.getElementById("login").style.borderColor = "#13fa00";
        document.getElementById("password").style.borderColor = "#13fa00";
        $("#war").text("You logged in!");
    } else if(data === "false") {
        document.getElementById("login").style.borderColor = "#FF0000";
        document.getElementById("password").style.borderColor = "#FF0000";
        $("#war").text("User doesn't exist!");
    } else if (data === "empty") {
        document.getElementById("login").style.borderColor = "#FFA500";
        document.getElementById("password").style.borderColor = "#FFA500";
        $("#war").text("All fields must be filled!");
    }
}

/*
function slowScroll(id){
    var offset = 0;
    $('html, body').animate( {
        scrollTop: $(id).offset().top - offset
    }, 500);
    return false;
}*/
