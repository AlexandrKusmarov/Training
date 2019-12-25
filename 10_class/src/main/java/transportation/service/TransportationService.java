package main.java.transportation.service;

import main.java.transportation.domain.Transportation;

import java.util.List;

public interface TransportationService {
    void add(Transportation transportation);

    <Transportattion> Transportation getById(Long id);

    <Transportattion> List<Transportation> getAll();

    boolean deleteById(Long id);

    void print(Transportation transportation);

    void printAll();

    void update(Transportation transportation);
}
