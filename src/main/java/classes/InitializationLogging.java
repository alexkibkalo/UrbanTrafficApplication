package classes;

import dao.implementationModels.ImplementRouteModel;
import models.Route;
import models.RoutingStop;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitializationLogging {

    public static final Logger logger = Logger.getLogger(InitializationLogging.class);

    public void setLogFile(){
        File file = new File("C:\\Users\\Alex_WORKOUT\\IdeaProjects\\TraineeProject\\logging.log");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getPath()));
            bufferedWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, List<RoutingStop>> execute(){
        Map<String, List<RoutingStop>> map = new HashMap<>();
        for (Route route : new ImplementRouteModel().getAllObjects()) {
            for (Map.Entry<String, List<RoutingStop>> i : route.getRoutes().entrySet()) {
                map.put(route.getRouteName(), i.getValue());
            }
        }
        return map;
    }
}
