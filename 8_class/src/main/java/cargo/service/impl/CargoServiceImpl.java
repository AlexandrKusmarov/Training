package main.java.cargo.service.impl;

import main.java.cargo.domain.Cargo;
import main.java.cargo.domain.CargoType;
import main.java.cargo.repo.CargoRepo;
import main.java.cargo.service.CargoService;
import main.java.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.List;

import static main.java.storage.Storage.arrCargo;

public class CargoServiceImpl implements CargoService {
    private CargoRepo arrRepo;

    public CargoServiceImpl(CargoRepo arrRepo) {
        this.arrRepo = arrRepo;
    }

    @Override
    public void add(Cargo cargo) {
        arrRepo.add(cargo);
    }

    @Override
    public Cargo getById(Long id) {
        return arrRepo.getById(id);
    }

    @Override
    public List<Cargo> getByName(String name) {
        return arrRepo.getByName(name);
    }

    @Override
    public List<Cargo> getAll() {
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
    public void printAll() {
        for (Cargo cargo : arrRepo.getAll()) {
            if (cargo != null) {
                System.out.println(cargo);
            }
        }
    }

    @Override
    public void update(Long id, String name, int weight, CargoType cargoType, Transportation[] transportation) {
        arrRepo.update(id, name, weight, cargoType, transportation);
    }
}
