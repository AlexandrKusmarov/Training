package main.java.storage.initor;

import main.java.application.serviceholder.ServiceHolder;
import main.java.cargo.domain.Cargo;
import main.java.cargo.service.CargoService;
import main.java.carrier.domain.Carrier;
import main.java.carrier.service.CarrierService;
import main.java.common.solutions.parser.txt.EntityParser;
import main.java.common.solutions.parser.txt.EntityReader;
import main.java.transportation.domain.Transportation;
import main.java.transportation.service.TransportationService;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static main.java.common.solutions.util.CollectionUtils.isNotEmpty;

public class FileStorageInitor implements StorageInitor {

    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;
    private static final String FILE_PATH = "D:\\JAVA\\EPAM_SPB\\Training\\11_class\\src\\main\\java\\resources\\TransportTable.txt";
    private static String text;

    public FileStorageInitor() {
        carrierService = ServiceHolder.getInstance().getCarrierService();
        cargoService = ServiceHolder.getInstance().getCargoService();
        transportationService = ServiceHolder.getInstance().getTransportationService();
    }

    @Override
    public void initStorage() throws IOException {
        EntityReader.readFromFile(new File(FILE_PATH));
        text = EntityReader.stringBuilderFile.toString();
        try {
            initCargos();
            initCarriers();
            initTransportations();
            tieEntities();
            addAllEntities();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void addAllEntities() {
        for (Cargo cargo : EntityParser.getCargosTemp()) {
            if (cargo != null) {
                cargoService.add(cargo);
            }
        }
        for (Carrier carrier : EntityParser.getCarriersTemp()) {
            if (carrier != null) {
                carrierService.add(carrier);
            }
        }
        EntityParser.getTransportMapTemp().forEach((k, v) -> transportationService.add(v));
    }

    private void tieEntities() {
        EntityParser.tieCargosCarriersToTransportations();
    }

    private void initCargos() throws ParseException {
        EntityParser.parseCargos(text);
    }

    private void initCarriers() throws ParseException {
        EntityParser.parseCarriers(text);
    }

    private void initTransportations() throws ParseException {
        EntityParser.parseTransportations(text);
    }

    private void appendTransportationsToCargos() {
        List<Cargo> cargos = cargoService.getAll();
        List<Transportation> transportations = transportationService.getAll();

        if (isNotEmpty(cargos) && isNotEmpty(transportations)) {
            for (Cargo cargo : cargos) {
                appendTransportationsToCargo(cargo, transportations);
            }
        }
    }

    private void appendTransportationsToCargo(Cargo cargo,
                                              List<Transportation> transportations) {

        List<Transportation> cargoTransportations = cargo.getTransportations();
        if (cargoTransportations == null) {
            cargoTransportations = new ArrayList<>();
        }

        for (Transportation transportation : transportations) {
            if (transportation.getCargo() != null && transportation.getCargo().getId()
                    .equals(cargo.getId())) {
                cargoTransportations.add(transportation);
            }
        }

        cargo.setTransportations(transportations);
    }
}
