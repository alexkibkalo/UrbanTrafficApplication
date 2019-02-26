package system.simulation.factories;

import system.models.Stop;

import java.util.HashMap;
import java.util.Map;

public class StopFactory {
    private static final Map<Integer, Stop> stops = new HashMap<>();

    public Stop getStop(Stop stop) {
        Stop object = null;
        try{
            object = stops.get(stop.getId());
        }catch (IndexOutOfBoundsException ignore){}

        if(object == null){
            object = new Stop(stop.getId(), stop.getName());
            stops.put(stop.getId(), object);
        }
        return object;
    }
}
