package system.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Stop implements Serializable {
    private int id;

    private String name;

    private List<Passenger> passengers = new ArrayList<>();

    public Stop(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addPassengerFromMinibus(List<Passenger> list) {
        passengers.addAll(list);
    }

    public void getPassengerToMinibus(List<Passenger> list) {
        if (passengers.size() - list.size() >= 0) {
            for (Passenger passenger : list) {
                passengers.remove(getIndex(passenger));
            }
        } else
            passengers.clear();
    }

    public void comingPassenger() {
        int count = (int) (0 + Math.random() * 6);
        for (int i = 0; i < count; i++) {
            passengers.add(new Passenger(name));
        }
    }

    public void leavingPassenger(List<Passenger> list) {
        if (passengers.size() - list.size() >= 0) {
            for (Passenger passenger : list) {
                passengers.remove(getIndex(passenger));
            }
        } else
            passengers.clear();
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

    public int getSize(){
        return passengers.size();
    }

    @Override
    public String toString() {
        return "Stop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
