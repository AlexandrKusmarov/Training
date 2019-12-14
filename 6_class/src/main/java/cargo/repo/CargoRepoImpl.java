package main.java.cargo.repo;

import main.java.cargo.domain.Cargo;
import main.java.storage.IdGenerator;
import main.java.util.ArrayCapacityChanger;

import static main.java.storage.Storage.*;

public class CargoRepoImpl implements CargoRepo {
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
        if (id != null && arrCargo.length != 0) {
            for (Cargo cargo : arrCargo) {
                if (id.equals(cargo.getId())) {
                    return cargo;
                }
            }
        }
        return null;
    }

    @Override
    public Cargo[] getByName(String name) {
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
                return foundCargos;
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
        boolean isDeleted = false;

        for (int i = 0; i < arrCargo.length; i++) {
            if (arrCargo[i].getId().equals(id)) {
                arrCargo = (Cargo[]) ArrayCapacityChanger.shiftArrFromEndToIndexByOnePos(arrCargo, i);
                isDeleted = true;
                break;
            }
        }
        if (isDeleted && arrCargo.length > 1) {
            arrCargo = (Cargo[]) ArrayCapacityChanger.constrictionArrCapacityByOne(arrCargo);
        } else if (isDeleted && arrCargo.length == 1) {
            arrCargo[0] = null;
        }
        return isDeleted;
    }

    @Override
    public void print(Cargo cargo) {
        System.out.println(cargo);
    }

    @Override
    public void printAll() {
        for (Cargo cargo : arrCargo) {
            if (cargo != null) {
                System.out.println(cargo);
            }
        }
    }
}
