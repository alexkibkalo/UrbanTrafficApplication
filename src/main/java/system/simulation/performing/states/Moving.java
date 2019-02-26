package system.simulation.performing.states;

import system.models.Minibus;
import system.models.Stop;
import system.services.LoggingService;

public class Moving implements State {
    @Override
    public void execute(Minibus minibus) {
        Stop stop = minibus.getCurrentStop();
        if(stop != null){
            LoggingService.logger.info("The minibus " + minibus.getModel() + " is moving!");
            service.generate(minibus.getCurrentStop());
        }else {
            LoggingService.logger.info("The minibus " + minibus.getModel() + " went to first stop!");
        }
    }
}
