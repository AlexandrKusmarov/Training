package main.java.cargo.service;

import main.java.cargo.domain.Cargo;
import main.java.cargo.domain.CargoType;
import main.java.transportation.domain.Transportation;

import java.util.List;

public interface CargoService {
    void add(Cargo cargo);

    Cargo getById(Long id);

    List<Cargo> getByName(String name);

    List<Cargo> getAll();

    boolean deleteById(Long id);

    void print(Cargo cargo);

    void printAll();

    void update(Long id, String name, int weight, CargoType cargoType, Transportation[] transportation);
}
