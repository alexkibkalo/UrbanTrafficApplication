package system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import system.dao.implementation.ImplementUserModel;
import system.models.User;
import system.services.LoggingService;
import system.services.ValidationService;
import system.simulation.performing.starting.RunnerRoute;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
public class MainController {

    @RequestMapping(value = "/validation-user", method = RequestMethod.POST)
    @ResponseBody
    public void validation(@RequestParam(value = "login") String login,
                           @RequestParam(value = "password") String password,
                           HttpServletResponse response) throws IOException, SQLException {

        response.setContentType("text/plain");
        PrintWriter printWriter = response.getWriter();

        if (!login.isEmpty() && !password.isEmpty()) {
            User user = new ImplementUserModel(login, password).read();

            if (user != null) {
                ValidationService.setCookie(response, login);
                printWriter.print("true");
                LoggingService.logger.info("User " + login + " logged in!");
            } else {
                printWriter.print("false");
                LoggingService.logger.warn("Something went wrong for user '" + login + "'!");
            }
        } else {
            printWriter.print("empty");
        }
    }

    @RequestMapping(value = "/simulation-started", method = RequestMethod.POST)
    @ResponseBody
    public void simulationStart() {
        long time = Long.parseLong(LocalTime.now().format(DateTimeFormatter.ofPattern("HH")));

        if (time < 12) {
            RunnerRoute.getRunnerRoute().run(5);
        } else if (time >= 12 && time < 18) {
            RunnerRoute.getRunnerRoute().run(2);
        } else if (time >= 18) {
            RunnerRoute.getRunnerRoute().run(4);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public void logout(HttpServletResponse response) {
        ValidationService.deleteCookie(response);
    }
}
