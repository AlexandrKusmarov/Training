package main.java.cargo.service.impl;

import main.java.cargo.domain.Cargo;
import main.java.cargo.repo.CargoCollectionRepo;

import java.util.List;

public class CargoCollectionServiceImpl implements CargoCollectionRepo {
    private CargoCollectionRepo collectionRepo = new CargoCollectionServiceImpl();

    @Override
    public void add(Cargo cargo) {
        collectionRepo.add(cargo);
    }

    @Override
    public Cargo getById(Long id) {
        return collectionRepo.getById(id);
    }

    @Override
    public List<Cargo> getByName(String name) {
        return collectionRepo.getByName(name);
    }

    @Override
    public List<Cargo> getAll() {
        return collectionRepo.getAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return collectionRepo.deleteById(id);
    }
}
