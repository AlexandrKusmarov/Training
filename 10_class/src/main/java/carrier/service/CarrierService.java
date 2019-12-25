package main.java.carrier.service;

import main.java.carrier.domain.Carrier;

import java.util.List;

public interface CarrierService<T extends Carrier> {
    void add(T carrier);

    T getById(Long id);

    List<Carrier> getByName(String name);

    List<Carrier> getAll();

    boolean deleteById(Long id);

    void print(T carrier);

    void printAll();

    void update(T carrier);

}
