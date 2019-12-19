package main.java.carrier.service;

import main.java.carrier.domain.Carrier;
import main.java.carrier.domain.CarrierType;
import main.java.transportation.domain.Transportation;

import java.util.List;

public interface CarrierService {
    void add(Carrier carrier);

    Carrier getById(Long id);

    List<Carrier> getByName(String name);

    List<Carrier> getAll();

    boolean deleteById(Long id);

    void print(Carrier carrier);

    void printAll();

    void update(Long id, String name, String address, CarrierType carrierType, Transportation[] transportation);
}
