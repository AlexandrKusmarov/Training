package main.java.cargo.repo;

import main.java.cargo.domain.Cargo;
import main.java.common.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo {
    void add(Cargo cargo);

    Cargo getById(Long id);

    List<Cargo> getByName(String name);

    List<Cargo> getAll();

    void update(Cargo cargo);

}
