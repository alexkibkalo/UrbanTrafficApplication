package system.models;

public class RoutingStop {

    private int idRouteStop;

    private String routeStopName;
    private String address;

    public RoutingStop(){}

    public RoutingStop(int idRoutingStop, String routingStopName, String address) {
        this.idRouteStop = idRoutingStop;
        this.routeStopName = routingStopName;
        this.address = address;
    }

    public int getIdRouteStop() {
        return idRouteStop;
    }

    public void setIdRouteStop(int idRouteStop) {
        this.idRouteStop = idRouteStop;
    }

    public String getRouteStopName() {
        return routeStopName;
    }

    public void setRouteStopName(String routeStopName) {
        this.routeStopName = routeStopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
