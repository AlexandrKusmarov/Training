package main.java.transportation.repo;

import main.java.carrier.domain.Carrier;
import main.java.transportation.domain.Transportation;

public interface TransportationRepo {
    void add(Transportation transportation);

    Transportation getById(Long id);

    Transportation[] getAll();

    boolean deleteById(Long id);

    void print(Transportation transportation);

    void printAll();
}
