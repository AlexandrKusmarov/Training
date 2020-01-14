package main.java.storage.initor.fileinitor;

import main.java.application.serviceholder.ServiceHolder;
import main.java.cargo.domain.Cargo;
import main.java.cargo.service.CargoService;
import main.java.carrier.domain.Carrier;
import main.java.carrier.service.CarrierService;
import main.java.storage.initor.StorageInitor;
import main.java.storage.initor.parser.dom.DomParser;
import main.java.transportation.domain.Transportation;
import main.java.transportation.service.TransportationService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import static main.java.common.solutions.util.xml.CommonParserUtil.tieCargosCarriersToTransportations;

public class DomXmlStorageInitor implements StorageInitor {
    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;
    private static Map<String, Cargo> cargoMap;
    private static Map<String, Carrier> carrierMap;
    private DomParser xmlParser = new DomParser();

    public DomXmlStorageInitor() {
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
            DomParser.initDocument();
            cargoMap = xmlParser.getCargoMap();
            for(Map.Entry<String, Cargo> pair : cargoMap.entrySet()){
                cargoService.add(pair.getValue());
            }
        } catch (ParserConfigurationException | SAXException | IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void initCarriers() {
        try {
            carrierMap = xmlParser.getCarrierMap();
            for(Map.Entry<String, Carrier> pair : carrierMap.entrySet()){
                carrierService.add(pair.getValue());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void initTransportations() {
        try {
            Map<String, Transportation> map;
            map = xmlParser.getTransportationMap();
            tieCargosCarriersToTransportations(map, cargoMap, carrierMap);
            map.forEach((k, v) -> transportationService.add(v));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
