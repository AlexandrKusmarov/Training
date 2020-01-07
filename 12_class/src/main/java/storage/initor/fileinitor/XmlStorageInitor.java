package main.java.storage.initor.fileinitor;

import main.java.application.serviceholder.ServiceHolder;
import main.java.cargo.domain.Cargo;
import main.java.cargo.service.CargoService;
import main.java.carrier.domain.Carrier;
import main.java.carrier.service.CarrierService;
import main.java.storage.initor.parser.xml.XmlParser;
import main.java.storage.initor.StorageInitor;
import main.java.transportation.domain.Transportation;
import main.java.transportation.service.TransportationService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class XmlStorageInitor implements StorageInitor {
    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;
    private static List<Cargo> cargoList;
    private static List<Carrier> carrierList;
    private XmlParser xmlParser;

    public XmlStorageInitor() {
        carrierService = ServiceHolder.getInstance().getCarrierService();
        cargoService = ServiceHolder.getInstance().getCargoService();
        transportationService = ServiceHolder.getInstance().getTransportationService();
    }

    @Override
    public void initStorage() throws IOException {
        initCargos();
        initCarriers();
        initTransportations();
    }

    private void initCargos() {
        try {
            XmlParser.initDocument();
            cargoList = xmlParser.getCargoList();
            for (Cargo cargo : cargoList) {
                cargoService.add(cargo);
            }
        } catch (ParserConfigurationException | SAXException | IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void initCarriers() {
        try {
            carrierList = xmlParser.getCarrierList();
            for (Carrier carrier : carrierList) {
                carrierService.add(carrier);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void initTransportations() {
        try {
            Map<String, Transportation> map;
            map = xmlParser.getTransportationMap();
            xmlParser.tieCargosCarriersToTransportations(map, cargoList, carrierList);
            map.forEach((k, v) -> transportationService.add(v));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
