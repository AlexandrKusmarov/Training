package main.java;

import main.java.model.cargo.domain.Cargo;
import main.java.model.cargo.domain.CargoType;
import main.java.model.carrier.domain.Carrier;
import main.java.model.transportation.domain.Transportation;

import java.util.Date;

public class DemoTransportCompany {
    public static void main(String[] args) {
        Storage storage = new Storage();

        Cargo apple = new Cargo();
        apple.setId(1L);
        apple.setName("Apple");

        Cargo orrange = new Cargo();
        orrange.setId(2L);
        orrange.setName("Orrange");

        Cargo banana = new Cargo();
        banana.setId(3L);
        banana.setName("banana");
        banana.setCargoType(CargoType.FOOD);

        Carrier company_1 = new Carrier();
        company_1.setId(1L);
        company_1.setName("Company 1");

        Carrier company_2 = new Carrier();
        company_2.setId(2L);
        company_2.setName("Company 2");


        Transportation transportation = new Transportation();
        transportation.setBillTo("Ivan ivanich");
        transportation.setId(1L);
        transportation.setCargo(apple);
        transportation.setCargo(banana);
        transportation.setCarrier(company_1);

        Transportation transportation2 = new Transportation();
        transportation2.setId(2L);
        transportation2.setDate(new Date(2019, 12, 20));
        transportation2.setCarrier(company_2);
        transportation2.setCargo(banana);

        apple.setTransportations(
                new Transportation[]{transportation}
        );

        storage.addCargo(apple);
        storage.addCargo(orrange);
        storage.addCargo(banana);
        storage.printAllCargo();

        storage.addCarrier(company_1);
        storage.addCarrier(company_2);
        storage.printAllCarrier();

        storage.addTransportation(transportation);
        storage.addTransportation(transportation2);
        storage.printAllTransportation();

    }
}
