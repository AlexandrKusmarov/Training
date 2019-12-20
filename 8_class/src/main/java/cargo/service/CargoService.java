package main.java.cargo.service;

import main.java.cargo.domain.Cargo;

import java.util.List;

public interface CargoService {
    void add(Cargo cargo);

    Cargo getById(Long id);

    List<Cargo> getByName(String name);

    List<Cargo> getAll();

    boolean deleteById(Long id);

    void print(Cargo cargo);

    void printAll();

    void update(Cargo cargo);
}
