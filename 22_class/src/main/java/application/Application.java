package application;

import application.serviceholder.ServiceHolder;
import application.serviceholder.StorageType;
import cargo.domain.Cargo;
import cargo.domain.CargoField;
import cargo.domain.FoodCargo;
import cargo.repo.impl.CargoDBRepoImpl;
import cargo.search.CargoSearchCondition;
import cargo.service.CargoService;
import carrier.service.CarrierService;
import common.business.exception.checked.InitStorageException;
import common.business.exception.checked.ReportException;
import common.solutions.search.OrderType;
import common.solutions.utils.CollectionUtils;
import reporting.ReportDefaultService;
import reporting.ReportService;
import storage.initor.InitStorageType;
import storage.initor.StorageInitor;
import transportation.service.TransportationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static cargo.domain.CargoField.NAME;
import static cargo.domain.CargoField.WEIGHT;
import static common.solutions.search.OrderType.ASC;
import static common.solutions.search.OrderType.DESC;
import static java.util.Collections.singletonList;
import static storage.initor.StorageInitorFactory.getStorageInitor;

public class Application {

    private static final String SEPARATOR = "--------------";
    private static CargoService cargoService;
    private static CarrierService carrierService;
    private static TransportationService transportationService;

    public static void main(String[] args) {
        try {
            ServiceHolder.initServiceHolder(StorageType.COLLECTION);
            cargoService = ServiceHolder.getInstance().getCargoService();
            carrierService = ServiceHolder.getInstance().getCarrierService();
            transportationService = ServiceHolder.getInstance().getTransportationService();

            StorageInitor storageInitor = getStorageInitor(InitStorageType.MULTI_THREAD);
            storageInitor.initStorage();

            demoSQLFindCargosByName();
            demoSQLFindById();
            demoSQLUpdateCargo();
            demoSQLgetAllCargos();
//            demoSQLDeleteCargoById();
            demoSQLCountAllCargos();

//      printStorageData();
//      demoSearchOperations();
//      demoSortOperations();
//      demoExceptions();
//      demoReportService();

        } catch (InitStorageException e) {
            e.getCause().printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void demoSQLCountAllCargos(){
        CargoDBRepoImpl cargoDBRepo = new CargoDBRepoImpl();
        System.out.println("Count of cargos: " + cargoDBRepo.countAll());
    }

    private static void demoSQLDeleteCargoById(){
        CargoDBRepoImpl cargoDBRepo = new CargoDBRepoImpl();
        System.out.println(cargoDBRepo.deleteById(1L));
    }

    private static void demoSQLgetAllCargos(){
        CargoDBRepoImpl cargoDBRepo = new CargoDBRepoImpl();
        System.out.println("-------------All cargos from DB---------------");
        CollectionUtils.printCollection(cargoDBRepo.getAll());
    }

    private static void demoSQLUpdateCargo(){
        CargoDBRepoImpl cargoDBRepo = new CargoDBRepoImpl();
        FoodCargo foodCargo = new FoodCargo();
        foodCargo.setId(3L);
        foodCargo.setName("Potato");
        foodCargo.setWeight(1000);
        foodCargo.setExpirationLocalDateTime(LocalDateTime.of(LocalDate.ofYearDay(2015, 25), LocalTime.now()));
        foodCargo.setStoreTemperature(18);
        System.out.println(cargoDBRepo.update(foodCargo));

    }

    private static void demoSQLFindCargosByName() {
        CargoDBRepoImpl cargoDBRepo = new CargoDBRepoImpl();
        System.out.println("Trying to find Cargo with name=Apple...");

        Cargo[] cargos = cargoDBRepo.findByName("Apple");
        CollectionUtils.printCollection(Arrays.asList(cargos));
    }

    private static void demoSQLFindById() {
        CargoDBRepoImpl cargoDBRepo = new CargoDBRepoImpl();
        System.out.println("Trying to find Cargo with id=2...");
        Optional<Cargo> cargo = cargoDBRepo.findById(2L);
        cargo.ifPresent(System.out::println);
    }

    private static void demoSearchOperations() {
        System.out.println("SEARCH CARGO BY ID = 1");
        System.out.println(cargoService.findById(1L));
        printSeparator();

        System.out.println("SEARCH CARRIER BY ID = 8");
        System.out.println(carrierService.findById(8L));
        printSeparator();

        System.out.println("SEARCH CARGOES BY NAME = 'APPLE'");
        CollectionUtils.printCollection(cargoService.findByName("APPLE"));
        printSeparator();

        System.out.println("SEARCH CARRIERS BY NAME = 'Carrier_Name'");
        CollectionUtils.printCollection(carrierService.findByName("Carrier_Name"));
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
        Long firstCargo = cargoService.getAll().get(0).getId();
        Optional<Cargo> cargoOptional = cargoService.getByIdFetchingTransportations(firstCargo);

        if (cargoOptional.isPresent()) {
            Cargo cargo = cargoOptional.get();

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
    }

    private static void demoReportService() throws ReportException {
        System.out.println("----------Demo report service ---------------");
        ReportService reportService = new ReportDefaultService(
                cargoService, carrierService, transportationService
        );
        reportService.exportData();
    }
}
