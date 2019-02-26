package system.models;

import system.dao.implementation.ImplementStopModel;
import system.simulation.performing.states.State;
import system.simulation.performing.states.Staying;

import java.util.ArrayList;
import java.util.List;

public class Minibus {

    private int id;
    private int capacity;
    private int currentStop;
    private int loaded;
    private int unloaded;

    private String model;

    private State state;

    private List<Stop> way;
    private List<Passenger> passengers = new ArrayList<>();

    public Minibus() {
    }

    public Minibus(int id, int capacity, String model) {
        this.id = id;
        this.capacity = capacity;
        this.model = model;
        this.state = new Staying();
        if (way == null) {
            way = new ImplementStopModel().getRoutingStopsByIdMinibus(id);
        }
    }

    public int getLoadLevel() {
        return passengers.size();
    }

    public void addPassengers(List<Passenger> list) {
        if (passengers.size() + list.size() <= capacity) {
            passengers.addAll(list);
        } else {
            for (Passenger passenger : list) {
                if (passengers.size() != capacity) {
                    passengers.add(passenger);
                } else
                    break;
            }
        }
    }

    public void getPassengers(List<Passenger> list) {
        if(passengers.size() - list.size() >= 0){
            for (Passenger passenger : list) {
                passengers.remove(getIndex(passenger));
            }
        } else {
            for (int i = 0; i < passengers.size(); i++) {
                passengers.remove(i);
            }
        }
    }

    private int getIndex(Passenger passenger) {
        for (int i = 0; i < passengers.size(); i++) {
            Passenger localObject = passengers.get(i);
            if (passenger.getRouteName().equals(localObject.getRouteName())) {
                return i;
            }
        }
        return 0;
    }

    public Stop getCurrentStop() {
        for (Stop s : way) {
            if (s.getId() == currentStop) {
                return s;
            }
        }
        return null;
    }

    public List<Stop> getStops() {
        return way;
    }

    public void setWay(List<Stop> way) {
        this.way = way;
    }

    public void setCurrentStop(int currentStop) {
        this.currentStop = currentStop;
    }

    public int getId() {
        return id;
    }

    public void setLoaded(int loaded) {
        this.loaded = loaded;
    }

    public void setUnloaded(int unloaded) {
        this.unloaded = unloaded;
    }

    public int getLoaded() {
        return loaded;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getUnloaded() {
        return unloaded;
    }

    public String getModel() {
        return model;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Double getPercent() {
        double result = (double) passengers.size() / capacity;
        return result * 100;
    }

    public void execute() {
        state.execute(this);
    }

}
