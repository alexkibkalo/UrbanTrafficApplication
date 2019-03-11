package system.simulation.performing.behaviors;

import system.models.Minibus;
import system.models.Route;
import system.models.Stop;
import system.simulation.performing.starting.Runner;
import system.simulation.performing.states.ControllerStates;
import system.statistic.entities.StopLog;

import java.time.LocalTime;
import java.util.List;

public class RoundRoute extends ControllerStates implements Runner {
    @Override
    public void move(Route route, Minibus minibus) {
        List<Stop> list = minibus.getStops();

        for (int i = 0; i < 2; i++) {
            for (Stop rs : list) {

                changeState(minibus, States.MOVING);

                try {
                    Thread.sleep(route.getFrequency());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (list.get(list.size() - 1).getId() != rs.getId()) {
                    minibus.setCurrentStop(rs.getId());
                    changeState(minibus, States.STAYING);

                    statistic.addLog(new StopLog(
                            LocalTime.now().toString(),
                            minibus.getCurrentStop().getId(),
                            route.getId(),
                            minibus.getId(),
                            minibus.getLoaded(),
                            minibus.getUnloaded(),
                            minibus.getPercent(),
                            minibus.getLoadLevel()));

                } else {
                    minibus.setCurrentStop(rs.getId());
                    changeState(minibus, States.STAYING);
                    changeState(minibus, States.REST);

                    statistic.addLog(new StopLog(
                            LocalTime.now().toString(),
                            minibus.getCurrentStop().getId(),
                            route.getId(),
                            minibus.getId(),
                            minibus.getLoaded(),
                            minibus.getUnloaded(),
                            minibus.getPercent(),
                            minibus.getLoadLevel()));
                }
            }
        }
    }
}
