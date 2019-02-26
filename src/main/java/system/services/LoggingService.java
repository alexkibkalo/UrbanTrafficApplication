package system.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import system.simulation.performing.starting.RunnerRoute;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoggingService {
    public static final Logger logger = LoggerFactory.getLogger(RunnerRoute.class);

    public void setLogFile(){
        File file = new File("C:\\Users\\Alex_WORKOUT\\IdeaProjects\\TraineeProject\\log_file.log");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getPath()));
            bufferedWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
