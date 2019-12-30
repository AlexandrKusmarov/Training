package main.java.storage.initor;

import main.java.application.serviceholder.ServiceHolder;
import main.java.cargo.domain.Cargo;
import main.java.cargo.service.CargoService;
import main.java.carrier.service.CarrierService;
import main.java.common.solutions.parser.xml.XmlParser;
import main.java.transportation.service.TransportationService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

public class XmlStorageInitor implements StorageInitor {
    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;

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
            for (Cargo cargo : XmlParser.getCargoList()) {
                cargoService.add(cargo);
            }
        } catch (ParserConfigurationException | SAXException | IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void initCarriers() {
    }

    private void initTransportations() {
    }


}
