package main.java.transportation.repo.impl;

import main.java.cargo.domain.Cargo;
import main.java.carrier.domain.Carrier;
import main.java.storage.IdGenerator;
import main.java.storage.Storage;
import main.java.transportation.domain.Transportation;
import main.java.transportation.repo.TransportationRepo;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TransportationCollectionRepoImpl implements TransportationRepo {
    @Override
    public void add(Transportation transportation) {
        if (transportation != null) {
            transportation.setId(IdGenerator.generateId());
            Storage.transportationList.add(transportation);
        }
    }

    @Override
    public Transportation getById(Long id) {
        for (Transportation transportation : Storage.transportationList) {
            if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
                return transportation;
            }
        }
        return null;
    }

    @Override
    public List<Transportation> getAll() {
        return Storage.cargoList.size() > 0 ? Storage.transportationList : null;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDeleted = false;
        if (Storage.transportationList.size() > 0) {
            Iterator<Transportation> iterator = Storage.transportationList.iterator();
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
        for (Transportation transportation : Storage.transportationList) {
            System.out.println(transportation);
        }
    }

    @Override
    public Integer getIndexById(Long id) {
        if (id != null) {
            Transportation transportation = getById(id);
            if (Storage.transportationList.contains(transportation)) {
                return Integer.valueOf(Storage.transportationList.indexOf(transportation));
            }
        }
        return null;
    }

    @Override
    public void update(Transportation transportation) {
        if (transportation != null) {
            Integer index = getIndexById(transportation.getId());
            if (index != null) {
                Storage.transportationList.set(index, transportation);
            }
        }
    }
}
