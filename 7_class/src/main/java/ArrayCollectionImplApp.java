package main.java;

import main.java.cargo.domain.Cargo;
import main.java.cargo.domain.CargoType;
import main.java.cargo.domain.LimitedShelfLife;
import main.java.cargo.domain.UnlimitedShelfLife;
import main.java.cargo.service.CargoService;
import main.java.cargo.service.impl.CargoServiceImpl;
import main.java.carrier.domain.Carrier;
import main.java.carrier.service.CarrierService;
import main.java.carrier.service.impl.CarrierServiceImpl;
import main.java.transportation.domain.Transportation;
import main.java.transportation.service.TransportationService;
import main.java.transportation.service.impl.TransportationArrServiceImpl;
import main.java.transportation.service.impl.TransportationCollectionServiceImpl;

import java.util.Date;

public class ArrayCollectionImplApp {
    private CargoService cargoArrService = new CargoServiceImpl();
    private CarrierService carrierArrService = new CarrierServiceImpl();
    private TransportationService transportationArrService = new TransportationArrServiceImpl();
    private CargoCollectionService cargoCollectionService = new CargoCollectionServiceImpl();
    private CarrierCollectionService carrierCollectionService = new CarrierCollectionServiceImpl();
    private TransportationCollectionService transportationCollectionService = new TransportationCollectionServiceImpl();
    private Cargo apple;
    private Cargo banana;
    private Cargo orange;
    private Cargo computer;
    private Carrier company_1;
    private Carrier company_2;
    private Transportation transportation;
    private Transportation transportation2;

    public void createCargos() {
        apple = new LimitedShelfLife(new Date(1, 12, 13), new Date(1, 1, 14));
        apple.setName("Apple");

        orange = new LimitedShelfLife(new Date(11, 12, 13), new Date(11, 11, 14));
        orange.setName("Orange");

        banana = new LimitedShelfLife(new Date(21, 12, 13), new Date(21, 1, 14));
        banana.setName("banana");
        banana.setCargoType(CargoType.FOOD);

        computer = new UnlimitedShelfLife(true, true);
        computer.setName("Computer2019");
        computer.setWeight(3);
        computer.setCargoType(CargoType.COMPUTERS);
    }

    public void createCarriers() {
        company_1 = new Carrier();
        company_1.setName("Company 1");

        company_2 = new Carrier();
        company_2.setName("Company 2");
    }

    public void createTransportations() {
        transportation = new Transportation();
        transportation.setBillTo("Ivan ivanich");
        transportation.setCargo(apple);
        transportation.setCargo(banana);
        transportation.setCargo(computer);
        transportation.setCarrier(company_1);

        transportation2 = new Transportation();
        transportation2.setDate(new Date(2019, 12, 20));
        transportation2.setCarrier(company_2);
        transportation2.setCargo(banana);
    }

    public void fillArrStorage() {
        apple.setTransportations(
                new Transportation[]{
                        transportation
                }
        );
        cargoArrService.add(apple);
        cargoArrService.add(orange);
        cargoArrService.add(banana);
        cargoArrService.add(computer);
        cargoArrService.printAll();

        carrierArrService.add(company_1);
        carrierArrService.add(company_2);
        carrierArrService.printAll();

        transportationArrService.add(transportation);
        transportationArrService.add(transportation2);
        transportationArrService.printAll();
    }

    public void fillCollectionStorage() {
        cargoCollectionService.add(apple);
        cargoCollectionService.add(orange);
        cargoCollectionService.add(banana);
        cargoCollectionService.add(computer);
        cargoCollectionService.printAll();

        carrierCollectionService.add(company_1);
        carrierCollectionService.add(company_2);
        carrierCollectionService.printAll();

        transportationCollectionService.add(transportation);
        transportationCollectionService.add(transportation2);
        transportationCollectionService.printAll();
    }

    public void testingCollectionsMethods(){
        System.out.println("----------------------------");
        cargoCollectionService.printAll();
        for (Cargo cargo : cargoCollectionService.getByName("banana")) {
            cargoCollectionService.print(cargo);
        }
        System.out.println("----------------------");

        cargoCollectionService.deleteById(2L);
        cargoCollectionService.deleteById(3L);
        cargoCollectionService.printAll();
    }

    public void testingArraysMethods(){
        System.out.println("----------------------------");
        cargoArrService.printAll();
        System.out.println("-----------------------------");
        cargoArrService.print(cargoArrService.getById(1L));

        cargoArrService.print(cargoArrService.getById(55L));
        System.out.println(cargoArrService.deleteById(1L));
        System.out.println(cargoArrService.deleteById(1L));
    }

}
