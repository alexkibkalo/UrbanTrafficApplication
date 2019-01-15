package servlets;

import classes.InitializationLogging;
import dao.implementationModels.ImplementUserModel;
import models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static classes.InitializationLogging.logger;

@WebServlet("/ValidationServlet")
public class ValidationServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        PrintWriter printWriter = response.getWriter();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        List<User> userList = new ImplementUserModel().getAllObjects();

        String result = "";
        if(!login.isEmpty() && !password.isEmpty()) {
            for (User user : userList){
                if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                    result = "true";
                    break;
                }else{
                    result = "false";
                }
            }
        }else{
            result = "empty";
        }

        if (result.equals("true")){
            logger.info("User " + login + " logged in!");
        }else if(result.equals("false")){
            logger.error("User " + login + " didn't log in!");
        }

        new InitializationLogging().execute();

        printWriter.print(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
