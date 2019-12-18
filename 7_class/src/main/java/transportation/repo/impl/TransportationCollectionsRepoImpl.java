package main.java.transportation.repo.impl;

import main.java.storage.Storage;
import main.java.transportation.domain.Transportation;
import main.java.transportation.repo.TransportationCollectionRepo;

import java.util.Iterator;
import java.util.List;

public class TransportationCollectionsRepoImpl implements TransportationCollectionRepo {
    @Override
    public void add(Transportation transportation) {
        if (transportation != null) {
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
}
