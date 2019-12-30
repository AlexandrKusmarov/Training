package main.java.common.solutions.parser.xml;

import main.java.cargo.domain.Cargo;
import main.java.cargo.domain.CargoType;
import main.java.cargo.domain.LimitedShelfLife;
import main.java.cargo.domain.UnlimitedShelfLife;
import main.java.carrier.domain.Carrier;
import main.java.carrier.domain.CarrierType;
import main.java.storage.IdGenerator;
import main.java.transportation.domain.Transportation;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
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
    private static final int CARGO_INDX_FROM_XML = 1000;
    private static final int CARRIER_INDEX_FROM_XML = 2000;
    private static final String PATH_NAME = "src/main/java/resources/TransportTable.xml";
    private static Document document;

    private XmlParser() {
    }

    public static void initDocument() throws ParserConfigurationException, IOException, SAXException {
        document = createDocument();
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

    public static List<Carrier> getCarrierList() throws ParseException {
        List<Carrier> carrierList = new ArrayList<>();
        NodeList carriers = document.getElementsByTagName("carriers").item(0).getChildNodes();

        for (int i = 0; i < carriers.getLength(); i++) {
            if (carriers.item(i).getNodeName().equals("carrier")) {
                List<String> temp = getEntity(carriers.item(i).getChildNodes());

                Carrier carrierObject = new Carrier();
                carrierObject.setId(IdGenerator.generateId());
                carrierObject.setName(temp.get(1));
                carrierObject.setAddress(temp.get(2));
                carrierObject.setCarrierType(CarrierType.valueOf(temp.get(3)));
                carrierList.add(carrierObject);
            }
        }
        return carrierList;
    }

    public static Map<String, Transportation> getTransportationMap() throws ParseException {
        Map<String, Transportation> transportationMap = new HashMap<>();
        NodeList transportations = document.getElementsByTagName("transportations").item(0).getChildNodes();

        for (int i = 0; i < transportations.getLength(); i++) {
            if (transportations.item(i).getNodeName().equals("transportation")) {
                List<String> temp = getEntity(transportations.item(i).getChildNodes());

                Transportation transportationObject = new Transportation();
                Date date = new SimpleDateFormat("dd.MM.yyyy").parse(temp.get(2));
                transportationObject.setId(IdGenerator.generateId());
                transportationObject.setDescription(temp.get(0));
                transportationObject.setBillTo(temp.get(1));
                transportationObject.setDate(date);
                transportationMap.put(temp.get(3), transportationObject);
            }
        }
        return transportationMap;
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

    public static void tieCargosCarriersToTransportations(Map<String, Transportation> map,
                                                          List<Cargo> cargoList,
                                                          List<Carrier> carrierList) {
        for (Map.Entry<String, Transportation> pair : map.entrySet()) {
            String[] arr = pair.getKey().split("->");
            pair.getValue()
                    .setCargo(cargoList.get(
                            Integer.parseInt(arr[0].trim()) - CARGO_INDX_FROM_XML - 1));
            pair.getValue()
                    .setCarrier(carrierList.get(
                            Integer.parseInt(arr[1].trim()) - CARRIER_INDEX_FROM_XML - 1));

        }
    }
}
