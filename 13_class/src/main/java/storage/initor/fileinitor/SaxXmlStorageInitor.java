package main.java.storage.initor.fileinitor;

import main.java.application.serviceholder.ServiceHolder;
import main.java.cargo.domain.Cargo;
import main.java.cargo.service.CargoService;
import main.java.carrier.domain.Carrier;
import main.java.carrier.service.CarrierService;
import main.java.storage.initor.StorageInitor;
import main.java.storage.initor.parser.sax.SaxParser;
import main.java.transportation.domain.Transportation;
import main.java.transportation.service.TransportationService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

import static main.java.common.solutions.util.xml.CommonParserUtil.tieCargosCarriersToTransportations;
import static main.java.storage.Storage.cargoList;
import static main.java.storage.Storage.carrierList;

public class SaxXmlStorageInitor implements StorageInitor {
    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;
    private SaxParser saxParser = new SaxParser();

    public SaxXmlStorageInitor() {
        carrierService = ServiceHolder.getInstance().getCarrierService();
        cargoService = ServiceHolder.getInstance().getCargoService();
        transportationService = ServiceHolder.getInstance().getTransportationService();
    }

    @Override
    public void initStorage() {
        initCargos();
        initCarriers();
        initTransportations();
    }

    private void initCargos() {
        try {
            for (Cargo cargo : saxParser.getCargoList()) {
                cargoService.add(cargo);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private void initCarriers() {
        try {
            for (Carrier carrier : saxParser.getCarrierList()) {
                carrierService.add(carrier);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private void initTransportations() {
        Map<String, Transportation> transportationMap;
        try {
            transportationMap = saxParser.getTransportationMap();
            tieCargosCarriersToTransportations(transportationMap, cargoList, carrierList);
            transportationMap.forEach((k, v) -> transportationService.add(v));
        } catch (ParserConfigurationException | SAXException | IOException e1) {
            e1.printStackTrace();
        }
    }
}
