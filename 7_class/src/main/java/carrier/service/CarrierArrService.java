package main.java.carrier.service;

import main.java.carrier.domain.Carrier;

public interface CarrierArrService {
    void add(Carrier carrier);

    Carrier getById(Long id);

    Carrier[] getByName(String name);

    Carrier[] getAll();

    boolean deleteById(Long id);

    void print(Carrier carrier);

    void printAll();
}
