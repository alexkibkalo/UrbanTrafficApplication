package system.services;

import system.dao.implementationModels.ImplementRouteModel;
import system.models.Route;
import system.models.RoutingStop;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationService {

    public static void setCookie(HttpServletResponse response, String login){
        Cookie cookie = new Cookie("login", login);
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest request, String login) {
        for (Cookie c : request.getCookies()) {
            if(c.getName().equals(login)){
                return c.getValue();
            }
        }
        return "sign in";
    }

    private static void deleteCookie() {
        Cookie cookie = new Cookie("login", "");
        cookie.setMaxAge(0);
    }

    public Map<String, List<RoutingStop>> loadData(){
        Map<String, List<RoutingStop>> map = new HashMap<>();
        for (Route route : new ImplementRouteModel().getAllObjects()) {
            for (Map.Entry<String, List<RoutingStop>> i : route.getRoutes().entrySet()) {
                map.put(route.getRouteName(), i.getValue());
            }
        }
        return map;
    }

}
