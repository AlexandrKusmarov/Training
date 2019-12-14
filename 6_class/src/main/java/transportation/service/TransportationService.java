package main.java.transportation.service;

import main.java.transportation.domain.Transportation;

public interface TransportationService {
    void add(Transportation transportation);

    Transportation getById(Long id);

    Transportation[] getAll();

    boolean deleteById(Long id);

    void print(Transportation cargo);

    void printAll(Transportation[] carriers);
}
