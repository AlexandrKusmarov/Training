package main.java.carrier.service;

import main.java.carrier.domain.Carrier;

import java.util.List;

public interface CarrierService {
    void add(Carrier carrier);

    <Carrier> Carrier getById(Long id);

    <Carrier> List<Carrier> getByName(String name);

    List<Carrier> getAll();

    boolean deleteById(Long id);

    void print(Carrier carrier);

    void printAll();

    void update(Carrier carrier);

}
