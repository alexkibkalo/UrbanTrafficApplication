package system.simulation.performing.behaviors;

import system.models.Minibus;
import system.models.Route;
import system.models.Stop;
import system.simulation.performing.starting.Runner;
import system.simulation.performing.states.ControllerStates;
import system.simulation.performing.states.Rest;
import system.statistic.StopLog;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

public class NotRoundRoute extends ControllerStates implements Runner {
    @Override
    public void move(Route route, Minibus minibus) {
        List<Stop> list = minibus.getStops();

        for (int i = 0; i < 2; i++) {
            for (Stop rs : list) {
                if(!(minibus.getState() instanceof Rest)){
                    changeState(minibus, States.MOVING);
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

                }else{
                    minibus.setCurrentStop(rs.getId());
                    Collections.reverse(list);
                }
            }
            changeState(minibus, States.REST);
        }
    }
}
