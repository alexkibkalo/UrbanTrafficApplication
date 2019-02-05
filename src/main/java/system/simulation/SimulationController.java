package system.simulation;

import system.dao.implementationModels.ImplementRouteModel;
import system.models.Minibus;
import system.models.Route;

import java.util.ArrayList;
import java.util.List;

public class StartSimuation {

    private static List<ThreadHandler> threads = new ArrayList<>();

    private static void creatingThreads() {
        ThreadHandler thread;
        for (Route route : new ImplementRouteModel().getAllObjects()){
            for(Minibus minibus : route.getMinibuses()){
                thread = new MinibusMotionsHandler(minibus, route);
                thread.setName(minibus.getModel() + " - " + route.getRouteName());
                threads.add(thread);
            }
        }

    }

    private static void startThreads(){
        System.out.println("Application started!");
        for (MinibusMotionsHandler thread : threads) {
            System.out.println(thread.getName());
            thread.start();
        }
    }

    public static void interrupt(){
        for (MinibusMotionsHandler thread : threads) {
            if(!thread.isInterrupted()){
                thread.interrupt();
            }
        }
        System.out.println("Application finished! All data saved!");
    }
}
