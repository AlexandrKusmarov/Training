package main.java.cargo.repo.impl;

import main.java.cargo.domain.Cargo;
import main.java.cargo.repo.CargoRepo;
import main.java.exception.EmptyArrayException;
import main.java.storage.IdGenerator;
import main.java.storage.Storage;

import java.util.ArrayList;
import java.util.Comparator;
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
        if (id != null) {
            for (Cargo cargo : Storage.cargoList) {
                if (cargo != null && Long.valueOf(id).equals(cargo.getId())) {
                    return cargo;
                }
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
        } else {
            try {
                throw new EmptyArrayException("Instance can't be deleted. Array is empty.");
            } catch (EmptyArrayException e) {
                e.printStackTrace();
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
    public Integer getIndexById(Long id) {
        Cargo cargo = getById(id);
        if (cargo != null) {
            if (Storage.cargoList.contains(cargo)) {
                return Integer.valueOf(Storage.cargoList.indexOf(cargo));
            }
        }
        return null;
    }

    @Override
    public void update(Cargo cargo) {
        if (cargo != null) {
            Integer index = getIndexById(cargo.getId());
            if (index != null) {
                Storage.cargoList.set(index, cargo);
            }
        }
    }

    @Override
    public void sort(List<Cargo> cargoList, Comparator<Cargo> comp) {
        cargoList.sort(comp);
    }
}