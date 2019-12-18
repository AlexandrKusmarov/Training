package main.java.transportation.service;

import main.java.transportation.domain.Transportation;

public interface TransportationArrService {
    void add(Transportation transportation);

    Transportation getById(Long id);

    Transportation[] getAll();

    boolean deleteById(Long id);

    void print(Transportation transportation);

    void printAll(Transportation[] transportations);
}
