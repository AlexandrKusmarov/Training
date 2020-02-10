package application;

import application.serviceholder.ServiceHolder;
import application.serviceholder.StorageType;
import cargo.domain.Cargo;
import cargo.domain.CargoField;
import cargo.domain.FoodCargo;
import cargo.repo.impl.CargoDBRepoImpl;
import cargo.search.CargoSearchCondition;
import cargo.service.CargoService;
import carrier.domain.Carrier;
import carrier.domain.CarrierType;
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

import java.sql.SQLException;
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

//            demoSQLFindCargosByName();
//            demoSQLFindById();
            demoSQLUpdateCargo();
            demoSQLgetAllCargos();
//            demoSQLDeleteCargoById();
            demoSQLCountAllCargos();
//            demoSQLInsertCargo();
            demoSQLSaveAllCargosAndCarriers();
            demoSQLgetAllCargos();

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

    private static void demoSQLSaveAllCargosAndCarriers(){
        CargoDBRepoImpl cargoDBRepo = new CargoDBRepoImpl();
        List<Cargo> cargos = new ArrayList<>();
        List<Carrier> carriers = new ArrayList<>();

        FoodCargo foodCargo = new FoodCargo();
        foodCargo.setId(95L);
        foodCargo.setName("Tomat");
        foodCargo.setWeight(9900);
        foodCargo.setExpirationLocalDateTime(LocalDateTime.of(LocalDate.ofYearDay(2015, 25), LocalTime.now()));
        foodCargo.setStoreTemperature(18);

        FoodCargo foodCargo2 = new FoodCargo();
        foodCargo2.setId(96L);
        foodCargo2.setName("Potato");
        foodCargo2.setWeight(10);
        foodCargo2.setExpirationLocalDateTime(LocalDateTime.of(LocalDate.ofYearDay(2015, 25), LocalTime.now()));
        foodCargo2.setStoreTemperature(8);

//        cargos.add(foodCargo);
//        cargos.add(foodCargo2);

        Carrier carrier = new Carrier();
        carrier.setId(124L);
        carrier.setName("TestCarrier55");
        carrier.setAddress("jgadf kjh");
        carrier.setCarrierType(CarrierType.CAR);

        Carrier carrier2 = new Carrier();
        carrier2.setId(123L);
        carrier2.setName("TestCarrier58");
        carrier2.setAddress("jgadf kjhs");
        carrier2.setCarrierType(CarrierType.PLANE);

        carriers.add(carrier);
        carriers.add(carrier2);

        try {
            cargoDBRepo.saveAll(cargos, carriers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void demoSQLCountAllCargos(){
        CargoDBRepoImpl cargoDBRepo = new CargoDBRepoImpl();
        try {
            System.out.println("Count of cargos: " + cargoDBRepo.countAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        foodCargo.setName("Tomat");
        foodCargo.setWeight(9900);
        foodCargo.setExpirationLocalDateTime(LocalDateTime.of(LocalDate.ofYearDay(2015, 25), LocalTime.now()));
        foodCargo.setStoreTemperature(18);
        System.out.println(cargoDBRepo.update(foodCargo));

    }

    private static void demoSQLFindCargosByName() throws SQLException {
        CargoDBRepoImpl cargoDBRepo = new CargoDBRepoImpl();
        System.out.println("Trying to find Cargo with name=Apple...");

        Cargo[] cargos = cargoDBRepo.findByName("Apple");
        CollectionUtils.printCollection(Arrays.asList(cargos));
    }

    private static void demoSQLFindById() throws SQLException {
        CargoDBRepoImpl cargoDBRepo = new CargoDBRepoImpl();
        System.out.println("Trying to find Cargo with id=2...");
        Optional<Cargo> cargo = cargoDBRepo.findById(2L);
        cargo.ifPresent(System.out::println);
    }

    private static void demoSQLInsertCargo(){
        CargoDBRepoImpl cargoDBRepo = new CargoDBRepoImpl();
        System.out.println("Insert new Cargo : ");
        FoodCargo foodCargo = new FoodCargo();
        foodCargo.setId(11L);
        foodCargo.setName("Orange");
        foodCargo.setWeight(3333);
        foodCargo.setExpirationLocalDateTime(LocalDateTime.of(LocalDate.ofYearDay(2015, 25), LocalTime.now()));
        foodCargo.setStoreTemperature(3);
        cargoDBRepo.save(foodCargo);
    }

    private static void demoSearchOperations() throws SQLException {
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

    private static void demoExceptions() throws SQLException {
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
