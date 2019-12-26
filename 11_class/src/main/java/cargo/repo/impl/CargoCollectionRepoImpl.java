package main.java.cargo.repo.impl;

import main.java.cargo.domain.Cargo;
import main.java.cargo.search.CargoSearchCondition;
import main.java.common.solutions.util.CollectionUtils;
import main.java.storage.IdGenerator;
import main.java.storage.Storage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class CargoCollectionRepoImpl extends CommonCargoRepo {
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
    public boolean deleteById(Object id) {
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
            if(cargo!= null) {
                System.out.println(cargo);
            }
        }
    }

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
    public Cargo getByIdFetchingTransportations(long id) {
        return getById(id);
    }

    @Override
    public List<Cargo> search(CargoSearchCondition searchCondition) {
        List<Cargo> cargos = getAll();

        if (CollectionUtils.isNotEmpty(cargos)) {
            if (searchCondition.needSorting()) {
                Comparator<Cargo> cargoComparator = createCargoComparator(searchCondition);
                cargos.sort(searchCondition.isAscOrdering() ? cargoComparator : cargoComparator.reversed());
            }
        }

        return cargos;
    }

    @Override
    public Object findById(Object o) {
        return null;
    }

    @Override
    public void save(Object entity) {

    }

    @Override
    public boolean update(Object entity) {
        return false;
    }
}