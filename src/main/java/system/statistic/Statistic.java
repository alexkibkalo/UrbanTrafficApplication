package system.statistic;

import java.util.ArrayList;
import java.util.List;

public class Statistic {

    private static volatile Statistic controller;
    private static List<StopLog> logs = new ArrayList<>();

    private Statistic(){}

    public static Statistic getObject(){
        Statistic localController = controller;
        if(localController == null){
            synchronized (Statistic.class){
                localController = controller;
                if (localController == null){
                    controller = localController = new Statistic();
                }
            }
        }
        return localController;
    }

    public void addLog(StopLog log){
        logs.add(log);
    }

    public List<StopLog> getLogList(){
        return logs;
    }
}
