package system.statistic;

import system.models.Minibus;
import system.models.Route;
import system.models.Stop;

import java.io.Serializable;
import java.util.List;

public class VeiwStatistic implements Serializable {
    private int countRoutes;
    private int countStops;
    private int countMinibuses;
    private String time;
    private String status;

    private List<Route> routes;
    private List<Stop> stops;
    private List<Minibus> minibuses;

    public VeiwStatistic(int countRoutes, int countStops, int countMinibuses, String time, String status) {
        this.countRoutes = countRoutes;
        this.countStops = countStops;
        this.countMinibuses = countMinibuses;
        this.time = time;
        this.status = status;
    }

    public int getCountRoutes() {
        return countRoutes;
    }

    public int getCountStops() {
        return countStops;
    }

    public int getCountMinibuses() {
        return countMinibuses;
    }
    public String getTime() {
        return time;
    }
    public String getStatus() {
        return status;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public List<Minibus> getMinibuses() {
        return minibuses;
    }
}
