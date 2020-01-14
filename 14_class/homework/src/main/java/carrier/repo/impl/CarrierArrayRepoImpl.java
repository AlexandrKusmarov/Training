package main.java.carrier.repo.impl;

import main.java.carrier.domain.Carrier;
import main.java.carrier.repo.CarrierRepo;
import main.java.common.solutions.util.ArrayCapacityChanger;
import main.java.storage.IdGenerator;

import java.util.Arrays;
import java.util.List;

import static main.java.storage.Storage.*;

public class CarrierArrayRepoImpl implements CarrierRepo {
    @Override
    public void add(Carrier carrier) {
        if (carrier != null) {
            if (currentIndexCarrier < arrCarrier.length) {
                carrier.setId(IdGenerator.generateId());
                arrCarrier[currentIndexCarrier] = carrier;
                currentIndexCarrier++;
            } else {
                arrCarrier = (Carrier[]) ArrayCapacityChanger.expandArrCapacity(arrCarrier);
                add(carrier);
            }
        }
    }

    @Override
    public Carrier getById(Long id) {
        for (Carrier carrier : arrCarrier) {
            if (carrier != null && Long.valueOf(id).equals(carrier.getId())) {
                return carrier;
            }
        }
        return null;
    }

    @Override
    public List<Carrier> getByName(String name) {
        Carrier[] foundCarrier = new Carrier[ARR_CAPACITY];
        int index = 0;
        if (name != null && arrCarrier.length != 0) {
            for (Carrier carrier : arrCarrier) {
                if (name.equals(carrier.getName())) {
                    if (index < foundCarrier.length) {
                        foundCarrier[index] = carrier;
                        index++;
                    } else {
                        foundCarrier = (Carrier[]) ArrayCapacityChanger.expandArrCapacityByOne(foundCarrier);
                        foundCarrier[index] = carrier;
                        index++;
                    }
                }
            }
            if (foundCarrier[0] != null) {
                return Arrays.asList(foundCarrier);
            }
        }
        return null;
    }

    @Override
    public List<Carrier> getAll() {
        if (arrCarrier.length != 0) {
            return Arrays.asList(arrCarrier);
        }
        return null;
    }

    @Override
    public boolean deleteById(Object id) {
        int len = arrCarrier.length;
            for (int i = 0; i < len; i++) {
                if (arrCarrier[i].getId().equals(id)) {
                    arrCarrier[i] = null;
                    arrCarrier = (Carrier[]) ArrayCapacityChanger.shiftArrFromEndToIndexByOnePos(arrCarrier, i);
                    if (len > 1) {
                        arrCarrier = (Carrier[]) ArrayCapacityChanger.constrictionArrCapacityByOne(arrCarrier);
                    }
                    return true;
                }
            }
        return false;
    }

    @Override
    public void printAll() {
        for (Carrier carrier : arrCarrier) {
            System.out.println(carrier);
        }
    }

    public Integer getIndexById(Long id) {
        if (id != null) {
            for (int i = 0; i < arrCarrier.length; i++) {
                if (Long.valueOf(id).equals(arrCarrier[i].getId())) {
                    return i;
                }
            }
        }
        return null;
    }

    @Override
    public void update(Carrier carrier) {
        if (carrier != null) {
            Integer index = getIndexById(carrier.getId());
            if (index != null) {
                arrCarrier[index] = carrier;
            }
        }
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
