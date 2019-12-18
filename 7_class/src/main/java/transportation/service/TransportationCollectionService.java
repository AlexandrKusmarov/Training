package main.java.transportation.service;

import main.java.transportation.domain.Transportation;

import java.util.List;

public interface TransportationCollectionService {
    void add(Transportation transportation);

    Transportation getById(Long id);

    List<Transportation> getAll();

    boolean deleteById(Long id);
}
