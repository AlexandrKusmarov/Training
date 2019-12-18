package main.java.cargo.repo;

import main.java.cargo.domain.Cargo;

import java.util.List;

public interface CargoCollectionRepo {
    void add(Cargo cargo);

    Cargo getById(Long id);

    List<Cargo> getByName(String name);

    List<Cargo> getAll();

    boolean deleteById(Long id);
}
