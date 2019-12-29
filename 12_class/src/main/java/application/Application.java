package main.java.application;

import main.java.application.serviceholder.ServiceHolder;
import main.java.application.serviceholder.StorageType;
import main.java.cargo.domain.Cargo;
import main.java.cargo.domain.CargoField;
import main.java.cargo.domain.CargoType;
import main.java.cargo.domain.LimitedShelfLife;
import main.java.cargo.search.CargoSearchCondition;
import main.java.cargo.service.CargoService;
import main.java.carrier.domain.Carrier;
import main.java.carrier.service.CarrierService;
import main.java.common.business.domain.BaseEntity;
import main.java.common.solutions.parser.EntityReader;
import main.java.common.solutions.search.OrderType;
import main.java.common.solutions.serialization.ExporterTxtFile;
import main.java.common.solutions.util.MessagePrinter;
import main.java.storage.Storage;
import main.java.storage.initor.FileStorageInitor;
import main.java.storage.initor.StorageInitor;
import main.java.transportation.service.TransportationService;

import java.io.IOException;
import java.util.*;

import static java.util.Collections.singletonList;
import static main.java.cargo.domain.CargoField.NAME;
import static main.java.cargo.domain.CargoField.WEIGHT;
import static main.java.common.solutions.search.OrderType.ASC;
import static main.java.common.solutions.search.OrderType.DESC;

public class Application {


    private static final String SEPARATOR = "--------------";
    private static final StorageType storageType = StorageType.COLLECTION;
    private static CargoService cargoService;
    private static CarrierService carrierService;
    private static TransportationService transportationService;

    public static void main(String[] args) {

        ServiceHolder.initServiceHolder(storageType);
        cargoService = ServiceHolder.getInstance().getCargoService();
        carrierService = ServiceHolder.getInstance().getCarrierService();
        transportationService = ServiceHolder.getInstance().getTransportationService();

        StorageInitor storageInitor = new FileStorageInitor();
//        StorageInitor storageInitor = new InMemoryStorageInitor();
        try {
            storageInitor.initStorage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        printStorageData();
//        doSearchOperations();
//        demoExceptions();
//        demoSortOperations();
        printStorageData();
        readAndShowFile();

        //HOMEWORK 12. EXPORT INTO TXT FILE.
        sortEntitiesById();
        ExporterTxtFile.exportIntoTxt(cargoService.getAll(), carrierService.getAll(), transportationService.getAll());

    }

    private static void sortEntitiesById() {
        Storage.cargoList.sort(Comparator.comparing(BaseEntity::getId));
        Storage.carrierList.sort(Comparator.comparing(BaseEntity::getId));
        Storage.transportationList.sort(Comparator.comparing(BaseEntity::getId));
    }

    private static void printStorageData() {
        System.out.println("ALL CARGOS");
        cargoService.printAll();
        printSeparator();

        System.out.println("ALL CARRIERS");
        carrierService.printAll();
        printSeparator();

        System.out.println("ALL TRANSPOORTATIONS");
        transportationService.printAll();
        printSeparator();
    }

    private static void doSearchOperations() {
        System.out.println("SEARCH CARGO BY ID = 1");
        System.out.println((Cargo) cargoService.getById(1L));
        printSeparator();

        System.out.println("SEARCH CARRIER BY ID = 8");
        System.out.println((Carrier) carrierService.getById(8L));
        printSeparator();

        System.out.println("SEARCH CARGOES BY NAME = 'Clothers_Name_1'");
        for (Object cargoName : cargoService.getByName("Clothers_Name_1")) {
            System.out.println(cargoName);
        }
        printSeparator();

        System.out.println("SEARCH CARRIERS BY NAME = 'CarFirst'");
        try {
            for (Object carrierName : carrierService.getByName("CarFirst")) {
                System.out.println(carrierName);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private static void doUpdateOperations() {
        LimitedShelfLife limCargo = new LimitedShelfLife(new Date(), new Date());
        limCargo.setName("bb");
        limCargo.setWeight(100000);
        limCargo.setCargoType(CargoType.COMPUTERS);
        cargoService.update(limCargo);
        limCargo = (LimitedShelfLife) cargoService.getById(2L);
        limCargo.setName("Aaa");
        cargoService.update(limCargo);
        limCargo = (LimitedShelfLife) cargoService.getById(3L);
        limCargo.setName("Bb");
        limCargo.setWeight(7777);
        cargoService.update(limCargo);
    }

    private static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    private static void demoSortOperations() {
        demoCargoSorting(singletonList(NAME), ASC);
        demoCargoSorting(singletonList(NAME), DESC);

        demoCargoSorting(singletonList(WEIGHT), ASC);
        demoCargoSorting(singletonList(WEIGHT), DESC);

        demoCargoSorting(Arrays.asList(NAME, WEIGHT), ASC);
        demoCargoSorting(Arrays.asList(NAME, WEIGHT), DESC);
    }

    private static String getOrderingConditionsAsString(CargoSearchCondition condition) {
        StringBuilder result = new StringBuilder();
        result.append(" ORDER BY ");

        Iterator<CargoField> iter = condition.getSortFields().iterator();
        while (iter.hasNext()) {
            CargoField fld = iter.next();
            result.append(fld);

            if (iter.hasNext()) {
                result.append(",");
            }
        }

        result.append(" ").append(condition.getOrderType());

        return result.toString();
    }

    private static void demoCargoSorting(Collection<CargoField> sortFields, OrderType orderType) {
        CargoSearchCondition cargoSearchCondition = new CargoSearchCondition();
        cargoSearchCondition.setOrderType(orderType);
        cargoSearchCondition.setSortFields(new LinkedHashSet<>(sortFields));
        System.out.println(
                "---------Sorting '" + getOrderingConditionsAsString(cargoSearchCondition) + "'------");
        cargoService.search(cargoSearchCondition);
        cargoService.printAll();
        System.out.println();
    }

    private static void demoExceptions() {
        System.out.println("------Demo  exceptions------------");
        Cargo firstCargo = (Cargo) cargoService.getAll().get(0);
        Long idFirstCargo = firstCargo.getId();

        Cargo cargo = cargoService.getByIdFetchingTransportations(idFirstCargo);
        System.out.println("Try to delete cargo");
        System.out.println("Cargo details:");
        System.out.println("id: " + cargo.getId());
        System.out.println("name: " + cargo.getName());
        System.out.println("total transportations: " + (cargo.getTransportations() != null ? cargo
                .getTransportations().size() : 0));
        System.out.println();
        try {
            cargoService.deleteById(cargo.getId());
        } catch (Exception e) {
            System.out.println("OOPS, something went wrong!");
            System.out.println(e.getMessage());
        }
    }

    private static void readAndShowFile() {
        MessagePrinter.printMessage(EntityReader.stringBuilderFile);
    }


}