package main.java.storage.initor.parser.sax;

import main.java.cargo.domain.Cargo;
import main.java.cargo.domain.CargoType;
import main.java.cargo.domain.LimitedShelfLife;
import main.java.cargo.domain.UnlimitedShelfLife;
import main.java.carrier.domain.Carrier;
import main.java.common.solutions.util.DateHandler;
import main.java.transportation.domain.Transportation;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

public class SaxParser {
    private static final String FILE = "src/main/java/resources/TransportTable.xml";
    private static Map<String, Cargo> cargoMap = new LinkedHashMap<>();
    private static Map<String, Carrier> carrierMap = new LinkedHashMap<>();
    private Map<String, Transportation> tiesMap = new LinkedHashMap<>();
    private SAXParser parser;

    public Map<String, Cargo> getCargoMap() throws ParserConfigurationException, SAXException, IOException {
        parser = getSAXParser();
        parser.parse(FILE, new XMLCargoHandler());
        return cargoMap;
    }

    public Map<String, Carrier> getCarrierMap() throws ParserConfigurationException, SAXException, IOException {
        parser = getSAXParser();
        parser.parse(FILE, new XMLCarrierHandler());
        return carrierMap;
    }

    public Map<String, Transportation> getTransportationMap() throws ParserConfigurationException, SAXException, IOException {
        parser = getSAXParser();
        parser.parse(FILE, new XMLTransportationHandler());
        return tiesMap;
    }

    private static SAXParser getSAXParser() throws ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        return factory.newSAXParser();
    }

    private static class XMLCargoHandler extends DefaultHandler {
        private String lastElementName;
        private String type;
        private String id;
        private String name;
        private String weight;
        private String cargoType;
        private String dateProduced;
        private String dateExpires;
        private String isComposite;
        private String fragility;


        @Override
        public void startElement(String uri, String localName,
                                 String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
            if (qName.equals("cargo")) {
                type = attributes.getValue("type");
                System.out.println("TYPE: " + type);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String content = new String(ch, start, length);
            content = content.replace("\n", "").trim();

            if (!content.isEmpty()) {
                if (lastElementName.equals("ID")) {
                    id = content;
                }
                if (lastElementName.equals("name")) {
                    name = content;
                }
                if (lastElementName.equals("weight")) {
                    weight = content;
                }
                if (lastElementName.equals("cargoType")) {
                    cargoType = content;
                }
                if (lastElementName.equals("dateProduced")) {
                    dateProduced = content;
                }
                if (lastElementName.equals("dateExpires")) {
                    dateExpires = content;
                }
                if (lastElementName.equals("isComposite")) {
                    isComposite = content;
                }
                if (lastElementName.equals("fragility")) {
                    fragility = content;
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (isAllFieldsOfOneEntityChecked()) {
                Cargo cargo;

                if (type.equals("lim")) {
                    cargo = new LimitedShelfLife();
                    try {
                        ((LimitedShelfLife) cargo).setProduced(DateHandler.parseDate(dateProduced));
                        ((LimitedShelfLife) cargo).setExpires(DateHandler.parseDate(dateExpires));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    cargo = new UnlimitedShelfLife();
                    ((UnlimitedShelfLife) cargo).setComposite(Boolean.parseBoolean(isComposite));
                    ((UnlimitedShelfLife) cargo).setFragility(Boolean.parseBoolean(fragility));
                }
                cargo.setName(name);
                cargo.setWeight(Integer.parseInt(weight));
                cargo.setCargoType(CargoType.valueOf(cargoType));
                cargoMap.put(id, cargo);
                cleanStringVarriables();
            }
        }

        private void cleanStringVarriables() {
            type = "";
            id = "";
            name = "";
            weight = "";
            cargoType = "";
            dateProduced = "";
            dateExpires = "";
            isComposite = "";
            fragility = "";
        }

        private boolean isAllFieldsOfOneEntityChecked() {
            return (name != null && !name.isEmpty() &&
                    id != null && !id.isEmpty() &&
                    weight != null && !weight.isEmpty() &&
                    cargoType != null && !cargoType.isEmpty()) &&
                    (isComposite != null && !isComposite.isEmpty() &&
                            fragility != null && !fragility.isEmpty()) ||
                    (dateProduced != null && !dateProduced.isEmpty() &&
                            dateExpires != null && !dateExpires.isEmpty());
        }

    }

    






}
