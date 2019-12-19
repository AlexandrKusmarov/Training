package main.java.cargo.repo;

import main.java.cargo.domain.Cargo;
import main.java.common.repo.CommonRepo;

public interface CargoRepo extends CommonRepo {
    void add(Cargo cargo);

    Cargo getById(Long id);

    Cargo[] getByName(String name);

    Cargo[] getAll();

}
