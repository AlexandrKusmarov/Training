package main.java.cargo.repo.impl;

import main.java.cargo.domain.Cargo;
import main.java.cargo.domain.CargoType;
import main.java.cargo.repo.CargoRepo;
import main.java.storage.IdGenerator;
import main.java.transportation.domain.Transportation;
import main.java.util.ArrayCapacityChanger;

import java.util.Arrays;
import java.util.List;

import static main.java.storage.Storage.*;

public class CargoArrayRepoImpl implements CargoRepo {
    @Override
    public void add(Cargo cargo) {
        if (cargo != null) {
            if (currentIndexCargo < arrCargo.length) {
                cargo.setId(IdGenerator.generateId());
                arrCargo[currentIndexCargo] = cargo;
                currentIndexCargo++;
            } else {
                arrCargo = (Cargo[]) ArrayCapacityChanger.expandArrCapacity(arrCargo);
                add(cargo);
            }
        }
    }

    @Override
    public Cargo getById(Long id) {
        for (Cargo cargo : arrCargo) {
            if (cargo != null && Long.valueOf(id).equals(cargo.getId())) {
                return cargo;
            }
        }
        return null;
    }

    @Override
    public List<Cargo> getByName(String name) {
        Cargo[] foundCargos = new Cargo[ARR_CAPACITY];
        int index = 0;
        if (name != null && arrCargo.length != 0) {
            for (Cargo cargo : arrCargo) {
                if (name.equals(cargo.getName())) {
                    if (index < foundCargos.length) {
                        foundCargos[index] = cargo;
                        index++;
                    } else {
                        foundCargos = (Cargo[]) ArrayCapacityChanger.expandArrCapacityByOne(foundCargos);
                        foundCargos[index] = cargo;
                        index++;
                    }
                }
            }
            if (foundCargos[0] != null) {
                return Arrays.asList(foundCargos);
            }
        }
        return null;
    }

    @Override
    public Cargo[] getAll() {
        if (arrCargo.length != 0) {
            return arrCargo;
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        int len = arrCargo.length;
        for (int i = 0; i < len; i++) {
            if (arrCargo[i].getId().equals(id)) {
                arrCargo[i] = null;
                arrCargo = (Cargo[]) ArrayCapacityChanger.shiftArrFromEndToIndexByOnePos(arrCargo, i);
                if (len > 1) {
                    arrCargo = (Cargo[]) ArrayCapacityChanger.constrictionArrCapacityByOne(arrCargo);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void printAll() {
        for (Cargo cargo : arrCargo) {
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
