package system.services;

import system.converter.XmlParser;
import system.dao.implementation.ImplementMinibusModel;
import system.dao.implementation.ImplementRouteModel;
import system.dao.implementation.ImplementStopModel;
import system.models.Minibus;
import system.models.Route;
import system.models.Stop;

import java.util.List;

public class LoadingData {
    public static void loadData() {
        XmlParser parser = new XmlParser();
        ImplementStopModel stopModel = new ImplementStopModel();
        ImplementRouteModel routeModel = new ImplementRouteModel();
        ImplementMinibusModel minibusModel = new ImplementMinibusModel();

        List<Route> routes = parser.readingXML();

        try {
            for (Route route : routes) {
                routeModel.create(route);

                for (Minibus minibus : route.getMinibusesList()) {
                    minibus.setIdRoute(route.getId());
                    minibusModel.create(minibus);

                    for (Stop stop : minibus.getStops()) {
                        stopModel.create(stop);
                        System.out.println(route.getId() + " " + stop.getId());
                        routeModel.addRelation(route.getId(), stop.getId());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
