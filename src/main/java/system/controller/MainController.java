package system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import system.InitializationLogging;
import system.dao.implementationModels.ImplementUserModel;
import system.models.User;
import system.services.ValidationService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class MainController {

    @RequestMapping(value = "/validation-user", method = RequestMethod.POST)
    @ResponseBody
    public void validation(@RequestParam(value = "login") String login,
                           @RequestParam(value = "password") String password,
                           HttpServletResponse response) throws IOException {

        response.setContentType("text/plain");
        PrintWriter printWriter = response.getWriter();

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
            ValidationService.setCookie(response, login);
            InitializationLogging.logger.info("User " + login + " logged in!");
            new ValidationService().loadData();
        }else if(result.equals("false")){
            InitializationLogging.logger.error("User " + login + " didn't log in!");
        }

        printWriter.print(result);
    }


}
