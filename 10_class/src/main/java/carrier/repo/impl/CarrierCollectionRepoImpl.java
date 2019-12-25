package main.java.carrier.repo.impl;

import main.java.carrier.domain.Carrier;
import main.java.carrier.repo.CarrierRepo;
import main.java.storage.IdGenerator;
import main.java.storage.Storage;

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
    public Integer getIndexById(Long id) {
        if (id != null) {
            Carrier carrier = getById(id);
            if (Storage.carrierList.contains(carrier)) {
                return Integer.valueOf(Storage.carrierList.indexOf(carrier));
            }
        }
        return null;
    }

    @Override
    public void update(Carrier carrier) {
        if (carrier != null) {
            Integer index = getIndexById(carrier.getId());
            if (index != null) {
                Storage.carrierList.set(index, carrier);
            }
        }
    }
}
