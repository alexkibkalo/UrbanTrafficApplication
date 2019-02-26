package system.converter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import system.models.Minibus;
import system.models.Route;
import system.models.Stop;
import system.simulation.factories.StopFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    private final static String FILE = "C:\\Users\\Alex_WORKOUT\\IdeaProjects\\TraineeProject\\routes.xml";

    private List<Stop> getStops(Element element){
        List<Stop> stops = new ArrayList<>();
        NodeList list = element.getElementsByTagName("Stop");
        StopFactory factory = new StopFactory();

        for (int i = 0; i < list.getLength(); i++) {
            String[] res = list.item(i).getTextContent().split("\n");
            stops.add(factory.getStop(new Stop(Integer.parseInt(res[1].trim()), res[2].trim())));
        }
        return stops;
    }

    private List<Minibus> getMinibuses(Element element){
        List<Minibus> minibuses = new ArrayList<>();
        NodeList list = element.getElementsByTagName("Minibus");

        for (int i = 0; i < list.getLength(); i++) {
            String[] res = list.item(i).getTextContent().split("\n");
            Minibus minibus = new Minibus(Integer.parseInt(res[1].trim()), Integer.parseInt(res[3].trim()), res[2].trim());
            minibus.setWay(getStops(element));
            minibuses.add(minibus);
        }
        return minibuses;
    }

    public List<Route> readingXML(){

        List<Route> routes = new ArrayList<>();

        try {
            File inputFile = new File(FILE);

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputFile);
            document.getDocumentElement().normalize();

            NodeList localRoutes = document.getElementsByTagName("Route");
            for (int temp = 0; temp < localRoutes.getLength(); temp++) {
                Node node = localRoutes.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    int fare = Integer.parseInt(element.getElementsByTagName("fare").item(0).getTextContent());
                    int frequency = Integer.parseInt(element.getElementsByTagName("frequency").item(0).getTextContent());

                    boolean isRound = Boolean.parseBoolean(element.getElementsByTagName("isRound").item(0).getTextContent());

                    String name = element.getElementsByTagName("name").item(0).getTextContent();

                    List<Minibus> minibuses = getMinibuses(element);

                    Route route = new Route(id, fare, frequency, name, isRound);
                    route.setMinibuses(minibuses);
                    routes.add(route);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return routes;
    }
}
