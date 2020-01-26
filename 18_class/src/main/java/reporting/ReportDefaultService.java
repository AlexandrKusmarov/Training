package reporting;

import cargo.domain.Cargo;
import cargo.service.CargoService;
import carrier.domain.Carrier;
import carrier.service.CarrierService;
import common.business.exception.checked.ReportException;
import transportation.domain.Transportation;
import transportation.service.TransportationService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static common.solutions.utils.CollectionUtils.isNotEmpty;

public class ReportDefaultService implements ReportService {

    private final CargoService cargoService;
    private final CarrierService carrierService;
    private final TransportationService transportationService;

    public ReportDefaultService(
            CargoService cargoService,
            CarrierService carrierService,
            TransportationService transportationService) {
        this.cargoService = cargoService;
        this.carrierService = carrierService;
        this.transportationService = transportationService;
    }

    @Override
    public void exportData() throws ReportException {
        List<String> reportData = new ArrayList<>();
        reportData.addAll(getCargosReportData());
        reportData.addAll(getCarriersReportData());
        reportData.addAll(getTransportationsReportData());

        if (isNotEmpty(reportData)) {
            try {
                exportDataToFile(reportData);
            } catch (Exception e) {
                throw new ReportException(e.getMessage());
            }
        }
    }

    private void exportDataToFile(List<String> data) throws IOException {
        Path tempFile = null;
        try {
            tempFile = Files.createTempFile("report", "all-data");
            Files.write(tempFile, data);

            List<String> fileContent = Files.readAllLines(tempFile);
            System.out.println("File content");

            fileContent.forEach(System.out::println);

//      for (String line : fileContent) {
//        System.out.println(line);
//      }
        } finally {
            if (tempFile != null) {
                Files.delete(tempFile);
            }
        }
    }

    private List<String> getCargosReportData() {
        List<Cargo> cargos = cargoService.getAll();

        List<String> result = Collections.emptyList();
        if (isNotEmpty(cargos)) {
            result = cargos.stream().filter(Objects::nonNull).map(this::cargoAsString).collect(Collectors.toList());

//      for (Cargo cargo : cargos) {
//        if (cargo != null) {
//          result.add(cargoAsString(cargo));
//        }
//      }
        }

        return result;
    }

    private String cargoAsString(Cargo cargo) {
        return "id:" + cargo.getId() + "| " + "Name:" + cargo.getName() + "| weight:" + cargo
                .getWeight() + "| type: " + cargo.getCargoType();
    }

    private List<String> getCarriersReportData() {
        List<Carrier> carriers = carrierService.getAll();

        List<String> result = Collections.emptyList();
        if (isNotEmpty(carriers)) {
            result = carriers.stream().filter(Objects::nonNull).map(this::carrierAsString).collect(Collectors.toList());

//      for (Carrier carrier : carriers) {
//        if (carrier != null) {
//          result.add(carrierAsString(carrier));
//        }
//      }
        }

        return result;
    }

    private String carrierAsString(Carrier carrier) {
        return "id:" + carrier.getId() + " |name:" + carrier.getName() + "| address:" + carrier
                .getAddress();
    }

    private List<String> getTransportationsReportData() {
        List<Transportation> transportations = transportationService.getAll();

        List<String> result = Collections.emptyList();
        if (isNotEmpty(transportations)) {
            result = transportations.stream().filter(Objects::nonNull).map(this::transportationAsString).collect(Collectors.toList());

//      for (Transportation transportation : transportations) {
//        if (transportation != null) {
//          result.add(transportationAsString(transportation));
//        }
//      }
        }

        return result;
    }

    private String transportationAsString(Transportation transportation) {
        return "id:" + transportation.getId() + "| description: " + transportation.getDescription()
                + "| cargoRef: " + transportation.getCargo().getId() + " carrierRef: " + transportation
                .getCarrier().getId();
    }


}
