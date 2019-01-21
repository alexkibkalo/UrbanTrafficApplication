package servlets;

import system.InitializationLogging;
import system.dao.implementationModels.ImplementUserModel;
import system.models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/validation")
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
            //logger.info("User " + login + " logged in!");
        }else if(result.equals("false")){
            //logger.error("User " + login + " didn't log in!");
        }

        new InitializationLogging().execute();

        printWriter.print(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
