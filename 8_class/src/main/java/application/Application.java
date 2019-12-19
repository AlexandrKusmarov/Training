package main.java.application;

import main.java.application.serviceholder.ServiceHolder;
import main.java.application.serviceholder.StorageType;
import main.java.cargo.domain.Cargo;
import main.java.cargo.service.CargoService;
import main.java.carrier.domain.Carrier;
import main.java.carrier.service.CarrierService;
import main.java.storage.initor.InMemoryStorageInitor;
import main.java.storage.initor.StorageInitor;
import main.java.transportation.service.TransportationService;

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

        StorageInitor storageInitor = new InMemoryStorageInitor();
        storageInitor.initStorage();

        printStorageData();
        doSearchOperations();
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
        System.out.println(cargoService.getById(1L));
        printSeparator();

        System.out.println("SEARCH CARRIER BY ID = 8");
        System.out.println(carrierService.getById(8L));
        printSeparator();

        System.out.println("SEARCH CARGOES BY NAME = 'Clothers_Name_1'");
        for (Cargo cargoName : cargoService.getByName("Clothers_Name_1")) {
            System.out.println(cargoName);
        }
        printSeparator();

        System.out.println("SEARCH CARRIERS BY NAME = 'Carrier_Name'");
        for (Carrier carrierName : carrierService.getByName("Carrier_Name")) {
            System.out.println(carrierName);
        }
    }

    private static void printSeparator() {
        System.out.println(SEPARATOR);
    }

//    private static void compareCargo(Cargo cargo1, Cargo cargo2){
//        Comparator<Cargo> cargocomp = new CargoNameComparator().thenComparing(new CargoWeightComparator());
//        List<Cargo> comparedCargoList = new ArrayList<Cargo>(cargocomp);
//    }

}