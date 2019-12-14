package main.java.carrier.service;

import main.java.carrier.domain.Carrier;

public interface CarrierService {
    void add(Carrier carrier);

    Carrier getById(Long id);

    Carrier[] getByName(String name);

    Carrier[] getAll();

    boolean deleteById(Long id);

    void print(Carrier cargo);

    void printAll(Carrier[] carriers);
}
