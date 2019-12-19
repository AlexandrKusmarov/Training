package main.java.cargo.repo;

import main.java.cargo.domain.Cargo;
import main.java.cargo.domain.CargoType;
import main.java.common.repo.CommonRepo;
import main.java.transportation.domain.Transportation;

import java.util.List;

public interface CargoRepo extends CommonRepo {
    void add(Cargo cargo);

    Cargo getById(Long id);

    List<Cargo> getByName(String name);

    Cargo[] getAll();

    void update(Long id, String name, int weight, CargoType cargoType, Transportation[] transportation);

}
