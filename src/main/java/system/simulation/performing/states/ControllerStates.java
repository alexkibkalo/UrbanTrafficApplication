package system.simulation.performing.states;

import system.models.Minibus;

public class ControllerStates {

    public enum States {
        MOVING,
        STAYING,
        REST
    }

    protected void changeState(Minibus minibus, States state) {
        switch (state.name()) {
            case "MOVING":
                minibus.setState(new Moving());
                minibus.execute();
                break;
            case "STAYING":
                minibus.setState(new Staying());
                minibus.execute();
                break;
            case "REST":
                minibus.setState(new Rest());
                minibus.execute();
                break;
        }
    }
}
