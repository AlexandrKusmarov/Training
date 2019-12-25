package main.java.transportation.service;

import main.java.transportation.domain.Transportation;

import java.util.List;

public interface TransportationService<T extends Transportation> {
    void add(T transportation);

    T getById(Long id);

    List<Transportation> getAll();

    boolean deleteById(Long id);

    void print(T transportation);

    void printAll();

    void update(T transportation);
}
