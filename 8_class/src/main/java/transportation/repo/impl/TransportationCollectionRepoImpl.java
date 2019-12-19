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
    public void update(Long id, Cargo cargo, Carrier carrier, String description, String billTo, Date date) {
        Transportation transportation = getById(id);
        if (transportation != null) {
            transportation.setCargo(cargo);
            transportation.setCarrier(carrier);
            transportation.setDescription(description);
            transportation.setBillTo(billTo);
            transportation.setDate(date);
        }
    }
}
