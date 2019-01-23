package system.models;

public class Minibus {

    private int idMinibus;
    private int capacity;

    private int idRoute;

    private String model;

    public Minibus(){}

    public Minibus(int idMinibus, int idRoute, int capacity, String model) {
        this.idRoute = idRoute;
        this.idMinibus = idMinibus;
        this.capacity = capacity;
        this.model = model;
    }

    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public int getIdMinibus() {
        return idMinibus;
    }

    public void setIdMinibus(int idMinibus) {
        this.idMinibus = idMinibus;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
