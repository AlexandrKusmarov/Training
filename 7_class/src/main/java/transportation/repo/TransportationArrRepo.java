package main.java.transportation.repo;

import main.java.transportation.domain.Transportation;

public interface TransportationArrRepo {
    void add(Transportation transportation);

    Transportation getById(Long id);

    Transportation[] getAll();

    boolean deleteById(Long id);
}
