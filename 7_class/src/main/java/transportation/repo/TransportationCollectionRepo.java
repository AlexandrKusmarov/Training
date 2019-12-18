package main.java.transportation.repo;

import main.java.transportation.domain.Transportation;

import java.util.List;

public interface TransportationCollectionRepo {
    void add(Transportation transportation);

    Transportation getById(Long id);

    List<Transportation> getAll();

    boolean deleteById(Long id);
}
