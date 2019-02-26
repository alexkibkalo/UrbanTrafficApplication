package system.simulation.performing.states;

import system.models.Minibus;
import system.services.SimulationService;

public interface State {
    SimulationService service = new SimulationService();

    void execute(Minibus minibus);
}
