package system.simulation.performing.states;

import system.models.Minibus;
import system.services.LoggingService;

public class Staying implements State {
    @Override
    public void execute(Minibus minibus) {
        LoggingService.logger.info("The minibus " + minibus.getModel() +
                " is staying! On the " + minibus.getCurrentStop().getName() + "...");
        service.unloading(minibus);
        service.loading(minibus);
    }
}
