package main.java.storage.initor.fileinitor;

import main.java.application.serviceholder.ServiceHolder;
import main.java.cargo.domain.Cargo;
import main.java.cargo.service.CargoService;
import main.java.carrier.service.CarrierService;
import main.java.storage.initor.StorageInitor;
import main.java.storage.initor.parser.sax.SaxParser;
import main.java.transportation.service.TransportationService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

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
        try {
           for(Map.Entry<String, Cargo> pair : saxParser.getCargoMap().entrySet()){
               System.out.println(pair.getKey() + " " + pair.getValue());
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        initCargos();
        initCarriers();
        initTransportations();
    }

    private void initCargos() {

    }

    private void initCarriers() {

    }

    private void initTransportations() {
    }

}
