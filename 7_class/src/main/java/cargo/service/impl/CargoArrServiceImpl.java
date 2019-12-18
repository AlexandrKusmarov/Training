package main.java.cargo.service.impl;

import main.java.cargo.domain.Cargo;
import main.java.cargo.repo.CargoArrRepo;
import main.java.cargo.repo.impl.CargoArrRepoImpl;
import main.java.cargo.service.CargoArrService;

import static main.java.storage.Storage.arrCargo;

public class CargoArrServiceImpl implements CargoArrService {
    private CargoArrRepo arrRepo = new CargoArrRepoImpl();

    @Override
    public void add(Cargo cargo) {
        arrRepo.add(cargo);
    }

    @Override
    public Cargo getById(Long id) {
        return arrRepo.getById(id);
    }

    @Override
    public Cargo[] getByName(String name) {
        return arrRepo.getByName(name);
    }

    @Override
    public Cargo[] getAll() {
        return arrRepo.getAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return arrRepo.deleteById(id);
    }

    @Override
    public void print(Cargo cargo) {
        System.out.println(cargo);
    }

    @Override
    public void printAll(Cargo[] cargos) {
        for (Cargo cargo : arrCargo) {
            if (cargo != null) {
                System.out.println(cargo);
            }
        }
    }
}
