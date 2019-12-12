package main.java;

import main.java.model.cargo.domain.Cargo;
import main.java.model.cargo.domain.CargoType;
import main.java.model.cargo.domain.LimitedShelfLife;
import main.java.model.cargo.domain.UnlimitedShelfLife;
import main.java.model.carrier.domain.Carrier;
import main.java.model.transportation.domain.Transportation;

import java.util.Date;

public class DemoTransportCompany {
    public static void main(String[] args) {
        Storage storage = new Storage();

        Cargo apple = new LimitedShelfLife(new Date(1,12,13), new Date(1,1,14));
        apple.setName("Apple");

        Cargo orange = new LimitedShelfLife(new Date(11,12,13), new Date(11,11,14));
        orange.setName("Orange");

        Cargo banana = new LimitedShelfLife(new Date(21,12,13), new Date(21,1,14));
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

        storage.addCargo(apple);
        storage.addCargo(orange);
        storage.addCargo(banana);
        storage.addCargo(computer);
        storage.printAllCargo();

        storage.addCarrier(company_1);
        storage.addCarrier(company_2);
        storage.printAllCarrier();

        storage.addTransportation(transportation);
        storage.addTransportation(transportation2);
        storage.printAllTransportation();

    }
}
