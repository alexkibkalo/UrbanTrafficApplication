package system.simulation.performing.starting;

import system.models.Minibus;
import system.models.Route;
import system.statistic.Statistic;

public interface Runner {
    Statistic statistic = Statistic.getObject();

    void move(Route route, Minibus minibus);
}
