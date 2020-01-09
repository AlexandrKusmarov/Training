package main.java.storage.initor.parser.sax;

import main.java.cargo.domain.Cargo;
import main.java.cargo.domain.CargoType;
import main.java.cargo.domain.LimitedShelfLife;
import main.java.cargo.domain.UnlimitedShelfLife;
import main.java.carrier.domain.Carrier;
import main.java.carrier.domain.CarrierType;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SaxParser {
    private static final String FILE = "src/main/java/resources/TransportTable.xml";
    private static List<Cargo> cargoList = new LinkedList<>();
    private static List<Carrier> carrierList = new LinkedList<>();
    private static Map<String, Transportation> tiesMap = new LinkedHashMap<>();
    private static String lastElementName;
    private static String type;
    private static String id;
    private static SAXParser parser;

    public List<Cargo> getCargoList() throws ParserConfigurationException, SAXException, IOException {
        parser = getSAXParser();
        parser.parse(FILE, new XMLCargoHandler());
        return cargoList;
    }

    public List<Carrier> getCarrierList() throws ParserConfigurationException, SAXException, IOException {
        parser = getSAXParser();
        parser.parse(FILE, new XMLCarrierHandler());
        return carrierList;
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
        private String name;
        private String weight;
        private String cargoType;
        private String dateProduced;
        private String dateExpires;
        private String isComposite;
        private String fragility;

        @Override
        public void startElement(String uri, String localName,
                                 String qName, Attributes attributes) {
            lastElementName = qName;
            if (qName.equals("cargo")) {
                type = attributes.getValue("type");
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
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
        public void endElement(String uri, String localName, String qName) {
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
                cargoList.add(cargo);
                cleanStringVarriables();
            }
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
    }

    private static class XMLCarrierHandler extends DefaultHandler {
        private String name;
        private String address;
        private String carrierType;

        @Override
        public void startElement(String uri, String localName,
                                 String qName, Attributes attributes) {
            lastElementName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            String content = new String(ch, start, length);
            content = content.replace("\n", "").trim();

            if (!content.isEmpty()) {
                if (lastElementName.equals("ID")) {
                    id = content;
                }
                if (lastElementName.equals("name")) {
                    name = content;
                }
                if (lastElementName.equals("address")) {
                    address = content;
                }
                if (lastElementName.equals("carrierType")) {
                    carrierType = content;
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (isAllFieldsOfOneEntityChecked()) {
                Carrier carrier = new Carrier();

                carrier.setName(name);
                carrier.setAddress(address);
                carrier.setCarrierType(CarrierType.valueOf(carrierType));
                carrierList.add(carrier);
                cleanStringVarriables();
            }
        }

        private void cleanStringVarriables() {
            id = "";
            name = "";
            address = "";
            carrierType = "";
        }

        private boolean isAllFieldsOfOneEntityChecked() {
            return (name != null && !name.isEmpty() &&
                    id != null && !id.isEmpty() &&
                    address != null && !address.isEmpty() &&
                    carrierType != null && !carrierType.isEmpty());
        }
    }

    private static class XMLTransportationHandler extends DefaultHandler {
        private String description;
        private String billTo;
        private String date;
        private String bound;

        @Override
        public void startElement(String uri, String localName,
                                 String qName, Attributes attributes) {
            lastElementName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            String content = new String(ch, start, length);
            content = content.replace("\n", "").trim();

            if (!content.isEmpty()) {
                if (lastElementName.equals("ID")) {
                    id = content;
                }
                if (lastElementName.equals("description")) {
                    description = content;
                }
                if (lastElementName.equals("billTo")) {
                    billTo = content;
                }
                if (lastElementName.equals("date")) {
                    date = content;
                }
                if (lastElementName.equals("bound")) {
                    bound = content;
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (isAllFieldsOfOneEntityChecked()) {
                Transportation transportation = new Transportation();
                try {
                    transportation.setDate(DateHandler.parseDate(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                transportation.setDescription(description);
                transportation.setBillTo(billTo);
                tiesMap.put(bound, transportation);
                cleanStringVarriables();
            }
        }

        private void cleanStringVarriables() {
            id = "";
            description = "";
            billTo = "";
            date = "";
            bound = "";
        }

        private boolean isAllFieldsOfOneEntityChecked() {
            return (description != null && !description.isEmpty() &&
                    id != null && !id.isEmpty() &&
                    billTo != null && !billTo.isEmpty() &&
                    bound != null && !bound.isEmpty());
        }
    }
}
