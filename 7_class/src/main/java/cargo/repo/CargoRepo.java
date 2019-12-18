package main.java.cargo.repo;

import main.java.cargo.domain.Cargo;

public interface CargoRepo {
    void add(Cargo cargo);

    Cargo getById(Long id);

    Cargo[] getByName(String name);

    Cargo[] getAll();

    boolean deleteById(Long id);

    void print(Cargo cargo);

    void printAll();
}
