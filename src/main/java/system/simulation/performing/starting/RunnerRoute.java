package system.simulation.performing.starting;

import system.converter.XmlParser;
import system.dao.implementation.ImplementStopModel;
import system.models.Minibus;
import system.models.Route;
import system.services.LoggingService;
import system.simulation.performing.behaviors.NotRoundRoute;
import system.simulation.performing.behaviors.RoundRoute;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnerRoute {

    private static volatile RunnerRoute controller;

    RunnerRoute(){}

    public static RunnerRoute getRunnerRoute(){
        RunnerRoute localObject = controller;
        if(localObject == null){
            synchronized (RunnerRoute.class){
                localObject = controller;
                if (localObject == null){
                    controller = localObject = new RunnerRoute();
                }
            }
        }
        return localObject;
    }

    private String start(Route route, Minibus minibus){
        if(route != null) {
            Runner runner;
            if (route.isRound()) {
                runner = new RoundRoute();
                LoggingService.logger.info("The Route with ID: " + route.getId() + " started!");
            } else {
                runner = new NotRoundRoute();
                LoggingService.logger.info("The Route with ID: " + route.getId() + " started!");
            }
            runner.move(route, minibus);
        }
        return "started";
    }

    public void run(int numberThreads) {
        XmlParser parser = new XmlParser();
        List<Route> routes = new ArrayList<>(parser.readingXML());

        LoggingService loggingService = new LoggingService();
        loggingService.setLogFile();

        ExecutorService executorService = Executors.newFixedThreadPool(numberThreads);
        List<Callable<String>> tasks = new ArrayList<>();

        long start = System.currentTimeMillis();
        ImplementStopModel model = new ImplementStopModel();
        model.clean();

        for (Route route : routes) {
            for (Minibus minibus : route.getMinibusesList()) {
                tasks.add(() -> start(route, minibus));
            }
        }

        for (Callable<String> task : tasks) {
            executorService.submit(task);
        }

        executorService.shutdown();

        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.print("Execution time is " + formatter.format((System.currentTimeMillis()  - start) / 1000d) + " seconds\n");
    }
}
