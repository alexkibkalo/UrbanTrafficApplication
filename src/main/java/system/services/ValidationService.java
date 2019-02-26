package system.services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidationService {

    private static String data;

    public static void setCookie(HttpServletResponse response, String login){
        Cookie cookie = new Cookie("login", login);
        cookie.setMaxAge(86400);
        data = login;
        response.addCookie(cookie);
    }

    public String getCookie(HttpServletRequest request, String login) {
        String cookieValue = "sign in";
        if(login != null){
            for (Cookie c : request.getCookies()) {
                if(c.getName().equals(login)){
                    cookieValue = c.getValue();
                    break;
                }
            }
        }
        return cookieValue;
    }

    public static void deleteCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("login", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        if(data != null){
            String log = "User '" + data + "' logged out!";
            LoggingService.logger.info(log);
        }
    }
}
