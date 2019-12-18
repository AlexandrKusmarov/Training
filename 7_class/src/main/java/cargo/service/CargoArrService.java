package main.java.cargo.service;

import main.java.cargo.domain.Cargo;

public interface CargoArrService {
    void add(Cargo cargo);

    Cargo getById(Long id);

    Cargo[] getByName(String name);

    Cargo[] getAll();

    boolean deleteById(Long id);

    void print(Cargo cargo);

    void printAll();
}
