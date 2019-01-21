package servlets;

import system.dao.implementationModels.ImplementUserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter printWriter = response.getWriter();

        String login = request.getParameter("login");
        String password = request.getParameter("password");


        String result = null;
        try {
            if(!login.isEmpty() && !password.isEmpty()){
                if(new ImplementUserModel(login, password).create()){
                    result = "true";
                }else
                    result = "false";
            }else {
                result = "empty";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        printWriter.print(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
