package main.java.cargo.repo.impl;

import main.java.cargo.domain.Cargo;
import main.java.cargo.repo.CargoRepo;
import main.java.storage.IdGenerator;
import main.java.storage.Storage;

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
    public Cargo [] getByName(String name) {
        List<Cargo> cargos = new ArrayList<>();
        if (Storage.cargoList.size() > 0 && name != null) {
            for (Cargo cargo : Storage.cargoList) {
                if (cargo.getName().equals(name)) {
                    cargos.add(cargo);
                }
            }
        }
        return cargos.size() > 0 ? (Cargo[]) cargos.toArray() : null;
    }

    @Override
    public Cargo [] getAll() {
        return Storage.cargoList.size() > 0 ? (Cargo[]) Storage.cargoList.toArray() : null;
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
}