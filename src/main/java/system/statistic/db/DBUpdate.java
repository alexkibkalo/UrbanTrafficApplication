package system.statistic.db;

import system.dao.implementation.ImplementStopModel;
import system.statistic.entities.StopLog;

public class DBUpdate {

    public void update(StopLog stopLog){
        ImplementStopModel model = new ImplementStopModel();
        model.addStopLog(stopLog);
    }
}
