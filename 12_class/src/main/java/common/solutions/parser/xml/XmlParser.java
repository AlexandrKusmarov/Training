package main.java.common.solutions.parser.xml;

import main.java.cargo.domain.Cargo;
import main.java.cargo.domain.CargoType;
import main.java.cargo.domain.LimitedShelfLife;
import main.java.cargo.domain.UnlimitedShelfLife;
import main.java.storage.IdGenerator;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class XmlParser {
    private static final String PATH_NAME = "src/main/java/resources/TransportTable.xml";
    private static Document document;

    private XmlParser() {
    }

    public static void initDocument() throws ParserConfigurationException, IOException, SAXException {
        document = createDocument();
        Node carriers = document.getElementsByTagName("carriers").item(0);
        Node transportations = document.getElementsByTagName("transportations").item(0);

    }

    private static Document createDocument() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new File(PATH_NAME));
    }

    public static List<Cargo> getCargoList() throws ParseException {
        List<Cargo> cargoList = new ArrayList<>();
        List<String> temp;
        Cargo cargoObject = null;
        NodeList cargos = document.getElementsByTagName("cargos").item(0).getChildNodes();
        for (int i = 0; i < cargos.getLength(); i++) {
            NamedNodeMap attributes;
            if (cargos.item(i).getNodeName().equals("cargo")) {
                attributes = cargos.item(i).getAttributes();
                temp = getEntity(cargos.item(i).getChildNodes());

                if (attributes.getNamedItem("type").getNodeValue().equals("lim")) {
                    cargoObject = new LimitedShelfLife();
                    Date date1 = new SimpleDateFormat("dd.MM.yyyy").parse(temp.get(4));
                    Date date2 = new SimpleDateFormat("dd.MM.yyyy").parse(temp.get(5));
                    ((LimitedShelfLife) cargoObject).setProduced(date1);
                    ((LimitedShelfLife) cargoObject).setExpires(date2);
                } else if (attributes.getNamedItem("type").getNodeValue().equals("unlim")) {
                    cargoObject = new UnlimitedShelfLife();
                    ((UnlimitedShelfLife) cargoObject).setComposite(Boolean.parseBoolean(temp.get(4)));
                    ((UnlimitedShelfLife) cargoObject).setFragility(Boolean.parseBoolean(temp.get(5)));
                }

                cargoObject.setId(IdGenerator.generateId());
                cargoObject.setName(temp.get(1));
                cargoObject.setWeight(Integer.parseInt(temp.get(2)));
                cargoObject.setCargoType(CargoType.valueOf(temp.get(3)));
                cargoList.add(cargoObject);
            }
        }
        return cargoList;
    }

    private static List<String> getEntity(NodeList nodeList) {
        List<String> tempList = new ArrayList<>();
        for (int j = 0; j < nodeList.getLength(); j++) {
            switch (nodeList.item(j).getNodeName()) {
                case "ID": {
                    tempList.add(nodeList.item(j).getTextContent());
                    break;
                }
                case "name": {
                    tempList.add(nodeList.item(j).getTextContent());
                    break;
                }
                case "weight": {
                    tempList.add(nodeList.item(j).getTextContent());
                    break;
                }
                case "cargoType": {
                    tempList.add(nodeList.item(j).getTextContent());
                    break;
                }
                case "dateProduced": {
                    tempList.add(nodeList.item(j).getTextContent());
                    break;
                }
                case "dateExpires": {
                    tempList.add(nodeList.item(j).getTextContent());
                    break;
                }
                case "isComposite": {
                    tempList.add(nodeList.item(j).getTextContent());
                    break;
                }
                case "fragility": {
                    tempList.add(nodeList.item(j).getTextContent());
                    break;
                }
                case "address": {
                    tempList.add(nodeList.item(j).getTextContent());
                    break;
                }
                case "carrierType": {
                    tempList.add(nodeList.item(j).getTextContent());
                    break;
                }
                case "description": {
                    tempList.add(nodeList.item(j).getTextContent());
                    break;
                }
                case "billTo": {
                    tempList.add(nodeList.item(j).getTextContent());
                    break;
                }
                case "date": {
                    tempList.add(nodeList.item(j).getTextContent());
                    break;
                }
                case "bound": {
                    tempList.add(nodeList.item(j).getTextContent());
                    break;
                }
            }
        }
        return tempList;
    }
}
