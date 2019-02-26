package system.simulation.performing.states;

import system.models.Minibus;
import system.services.LoggingService;

public class Rest implements State {
    @Override
    public void execute(Minibus minibus) {
        LoggingService.logger.info("Rest! The minibus " + minibus.getModel() + " is standing!");
    }
}
