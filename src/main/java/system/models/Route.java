package system.models;

import java.io.Serializable;
import java.util.List;

public class Route implements Serializable {

    private int id;
    private int fare;
    private int frequency;

    private String name;

    private boolean isRound;

    private List<Minibus> minibuses;

    public Route(int id, int fare, int frequency, String name, boolean isRound) {
        this.id = id;
        this.fare = fare;
        this.frequency = frequency;
        this.name = name;
        this.isRound = isRound;
    }

    public void setMinibuses(List<Minibus> minibuses) {
        this.minibuses = minibuses;
    }

    public boolean isRound() {
        return isRound;
    }

    public int getId() {
        return id;
    }

    public int getFare() {
        return fare;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getName() {
        return name;
    }

    public List<Minibus> getMinibusesList() {
        return minibuses;
    }
}
