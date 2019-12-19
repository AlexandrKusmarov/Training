package main.java.carrier.repo.impl;

import main.java.carrier.domain.Carrier;
import main.java.carrier.domain.CarrierType;
import main.java.carrier.repo.CarrierRepo;
import main.java.storage.IdGenerator;
import main.java.storage.Storage;
import main.java.transportation.domain.Transportation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarrierCollectionRepoImpl implements CarrierRepo {
    @Override
    public void add(Carrier carrier) {
        if (carrier != null) {
            carrier.setId(IdGenerator.generateId());
            Storage.carrierList.add(carrier);
        }
    }

    @Override
    public Carrier getById(Long id) {
        for (Carrier carrier : Storage.carrierList) {
            if (carrier != null && Long.valueOf(id).equals(carrier.getId())) {
                return carrier;
            }
        }
        return null;
    }

    @Override
    public List<Carrier> getByName(String name) {
        List<Carrier> carriers = new ArrayList<>();
        if (Storage.carrierList.size() > 0 && name != null) {
            for (Carrier carrier : Storage.carrierList) {
                if (carrier.getName().equals(name)) {
                    carriers.add(carrier);
                }
            }
        }
        return carriers.size() > 0 ? carriers : null;
    }

    @Override
    public List<Carrier> getAll() {
        return Storage.carrierList.size() > 0 ? Storage.carrierList : null;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDeleted = false;
        if (Storage.carrierList.size() > 0) {
            Iterator<Carrier> iterator = Storage.carrierList.iterator();
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
        for (Carrier carrier : Storage.carrierList) {
            System.out.println(carrier);
        }
    }

    @Override
    public void update(Long id, String name, String address, CarrierType carrierType, Transportation[] transportation) {
        Carrier carrier = getById(id);
        if (carrier != null) {
            carrier.setName(name);
            carrier.setAddress(address);
            carrier.setCarrierType(carrierType);
            carrier.setTransportations(transportation);
        }
    }
}
