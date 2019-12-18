package main.java;

import main.java.cargo.domain.Cargo;
import main.java.cargo.domain.CargoType;
import main.java.cargo.domain.LimitedShelfLife;
import main.java.cargo.domain.UnlimitedShelfLife;
import main.java.cargo.repo.CargoArrRepo;
import main.java.cargo.repo.impl.CargoArrRepoImpl;
import main.java.carrier.domain.Carrier;
import main.java.carrier.repo.CarrierRepo;
import main.java.carrier.repo.CarrierRepoImpl;
import main.java.transportation.domain.Transportation;
import main.java.transportation.repo.TransportationRepo;
import main.java.transportation.repo.TransportationRepoImpl;

import java.util.Date;

public class DemoTransportCompany {
    public static void main(String[] args) {
        CargoArrRepo cargoRepo = new CargoArrRepoImpl();
        CarrierRepo carrierRepo = new CarrierRepoImpl();
        TransportationRepo transportationRepo = new TransportationRepoImpl();

        Cargo apple = new LimitedShelfLife(new Date(1, 12, 13), new Date(1, 1, 14));
        apple.setName("Apple");

        Cargo orange = new LimitedShelfLife(new Date(11, 12, 13), new Date(11, 11, 14));
        orange.setName("Orange");

        Cargo banana = new LimitedShelfLife(new Date(21, 12, 13), new Date(21, 1, 14));
        banana.setName("banana");
        banana.setCargoType(CargoType.FOOD);

        Cargo computer = new UnlimitedShelfLife(true, true);
        computer.setName("Computer2019");
        computer.setWeight(3);
        computer.setCargoType(CargoType.COMPUTERS);

        Carrier company_1 = new Carrier();
        company_1.setName("Company 1");

        Carrier company_2 = new Carrier();
        company_2.setName("Company 2");


        Transportation transportation = new Transportation();
        transportation.setBillTo("Ivan ivanich");
        transportation.setCargo(apple);
        transportation.setCargo(banana);
        transportation.setCargo(computer);
        transportation.setCarrier(company_1);

        Transportation transportation2 = new Transportation();
        transportation2.setDate(new Date(2019, 12, 20));
        transportation2.setCarrier(company_2);
        transportation2.setCargo(banana);

        apple.setTransportations(
                new Transportation[]{transportation}
        );

        cargoRepo.add(apple);
        cargoRepo.add(orange);
        cargoRepo.add(banana);
        cargoRepo.add(computer);
        cargoRepo.printAll();

        carrierRepo.add(company_1);
        carrierRepo.add(company_2);
        carrierRepo.printAll();

        transportationRepo.add(transportation);
        transportationRepo.add(transportation2);
        transportationRepo.printAll();

        System.out.println();
//----------------------- Все товары -----------------------
        for (Cargo allCargo : cargoRepo.getAll()) {
            System.out.println(allCargo);
        }
//----------------------- Все товары -----------------------
        System.out.println();
        System.out.println("Obtain Cargo by id: " + cargoRepo.getById(3L));
        for (Cargo cargo : cargoRepo.getByName("banana")) {
            System.out.println("Obtain Cargo by name: " + cargo);
        }
        System.out.println();
//----------------------- Все перевозчики -----------------------
        for (Carrier allCarrier : carrierRepo.getAll()) {
            System.out.println(allCarrier);
        }
//----------------------- Все перевозчики -----------------------
        System.out.println();
        System.out.println("Obtain Carrier by id: " + carrierRepo.getById(5L));
        for (Carrier allCarrier : carrierRepo.getByName("Company 2")) {
            System.out.println("Obtain Carrier by name: " + allCarrier);
        }
        System.out.println();
//----------------------- Все перевозки -----------------------
        for (Transportation allTransportation : transportationRepo.getAll()) {
            System.out.println(allTransportation);
        }
//----------------------- Все перевозки -----------------------
        System.out.println();
        System.out.println("Obtain Transportation by id: " + transportationRepo.getById(7L));
        System.out.println("Obtain Transportation by id: " + transportationRepo.getById(8L));
        System.out.println("Obtain Transportation by id: " + transportationRepo.getById(1L));
//----------------------- Удаление по ID -----------------------
        cargoRepo.deleteById(3L);
        cargoRepo.printAll();
        System.out.println();
        cargoRepo.deleteById(1L);
        cargoRepo.printAll();
        System.out.println();
        cargoRepo.deleteById(2L);
        System.out.println("----------------------------------");
        cargoRepo.printAll();
        System.out.println(cargoRepo.deleteById(2L));
        cargoRepo.printAll();
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        System.out.println(cargoRepo.deleteById(4L));
        cargoRepo.printAll();



    }
}
