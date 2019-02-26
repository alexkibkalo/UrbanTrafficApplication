package system.statistic;

import system.dao.implementation.ImplementStopModel;

public class DBUpdate {

    public void update(StopLog stopLog){
        ImplementStopModel model = new ImplementStopModel();
        model.addStopLog(stopLog);
    }
}
