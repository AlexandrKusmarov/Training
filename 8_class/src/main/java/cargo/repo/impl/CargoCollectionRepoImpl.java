package main.java.cargo.repo.impl;

import main.java.cargo.domain.Cargo;
import main.java.cargo.domain.CargoType;
import main.java.cargo.repo.CargoRepo;
import main.java.storage.IdGenerator;
import main.java.storage.Storage;
import main.java.transportation.domain.Transportation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CargoCollectionRepoImpl implements CargoRepo {
    @Override
    public void add(Cargo cargo) {
        if (cargo != null) {
            cargo.setId(IdGenerator.generateId());
            Storage.cargoList.add(cargo);
        }
    }

    @Override
    public Cargo getById(Long id) {
        for (Cargo cargo : Storage.cargoList) {
            if (cargo != null && Long.valueOf(id).equals(cargo.getId())) {
                return cargo;
            }
        }
        return null;
    }

    @Override
    public List<Cargo> getByName(String name) {
        List<Cargo> cargos = new ArrayList<>();
        if (Storage.cargoList.size() > 0 && name != null) {
            for (Cargo cargo : Storage.cargoList) {
                if (cargo.getName().equals(name)) {
                    cargos.add(cargo);
                }
            }
        }
        return cargos.size() > 0 ? cargos : new ArrayList<>();
    }

    @Override
    public List<Cargo> getAll() {
        return Storage.cargoList.size() > 0 ? Storage.cargoList : null;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDeleted = false;
        if (Storage.cargoList.size() > 0) {
            Iterator<Cargo> iterator = Storage.cargoList.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getId().equals(id)) {
                    iterator.remove();
                    isDeleted = true;
                    break;
                }
            }
        }
        return isDeleted;
    }

    @Override
    public void printAll() {
        for (Cargo cargo : Storage.cargoList) {
            System.out.println(cargo);
        }
    }

    @Override
    public void update(Long id, String name, int weight, CargoType cargoType, Transportation[] transportation) {
        Cargo cargo = getById(id);
        if (cargo != null) {
            cargo.setCargoType(cargoType);
            cargo.setName(name);
            cargo.setWeight(weight);
            cargo.setTransportations(transportation);
        }
    }
}