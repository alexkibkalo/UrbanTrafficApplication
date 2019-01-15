package models;

import dao.implementationModels.ImplementRoutingStopModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Route extends Thread {

    private int idRoute;
    private int fare;
    private int frequency;

    private String routeName;

    private boolean isRoundRoute;

    private Map<String, List<RoutingStop>> routes = new HashMap<>();

    public Route() {
    }

    public Route(int idRoute, int fare, int frequency, String routeName, boolean isRoundRoute) {

        this.idRoute = idRoute;
        this.fare = fare;
        this.frequency = frequency;
        this.routeName = routeName;
        this.isRoundRoute = isRoundRoute;
        routes.put(routeName, new ImplementRoutingStopModel().getRoutingStopsById(idRoute));
    }

    public Map<String, List<RoutingStop>> getRoutes() {
        return routes;
    }

    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public boolean isRoundRoute() {
        return isRoundRoute;
    }

    public void setRoundRoute(boolean roundRoute) {
        isRoundRoute = roundRoute;
    }

    @Override
    public void run() {
        System.out.println("Route: " + routeName + " was started!");
    }
}
