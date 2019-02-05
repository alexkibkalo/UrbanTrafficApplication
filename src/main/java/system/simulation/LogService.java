package system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogService {

    static {
        setLogFile();
    }

    public static final Logger logger = LoggerFactory.getLogger(SimulationController.class);

    private static void setLogFile(){
        File file = new File("log_file.log");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getPath()));
            bufferedWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
