package system.services;

import system.models.Minibus;
import system.models.Passenger;
import system.models.Stop;

import java.util.ArrayList;
import java.util.List;

public class SimulationService {

    public void loading(Minibus minibus) {
        Stop stop = minibus.getCurrentStop();

        LoggingService.logger.info("Loading... On the " + stop.getName() +
                "! To the minibus " + minibus.getModel());

        int count = (int) (1 + Math.random() * 5);

        List<Passenger> passengers = getPassengerList(count, stop);

        minibus.addPassengers(passengers);
        minibus.setLoaded(count);

        if(stop != null){
            stop.getPassengerToMinibus(passengers);
        }
    }

    public void unloading(Minibus minibus) {
        Stop stop = minibus.getCurrentStop();

        LoggingService.logger.info("Unloading... On the " + stop.getName() +
                "! From the minibus " + minibus.getModel());

        int count = (int) (1 + Math.random() * 3);

        List<Passenger> passengers = getPassengerList(count, stop);

        minibus.getPassengers(passengers);
        minibus.setUnloaded(count);

        if(stop != null){
            stop.addPassengerFromMinibus(passengers);
        }
    }

    public void generate(Stop stop){
        stop.comingPassenger();
        stop.leavingPassenger(getPassengerList((int) (0 + Math.random() * 5), stop));
    }

    private List<Passenger> getPassengerList(int count, Stop stop){
        List<Passenger> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Passenger(stop.getName()));
        }
        return list;
    }
}
