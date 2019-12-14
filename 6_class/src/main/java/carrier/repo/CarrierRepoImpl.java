package main.java.carrier.repo;

import main.java.carrier.domain.Carrier;
import main.java.storage.IdGenerator;
import main.java.util.ArrayCapacityChanger;

import static main.java.storage.Storage.*;

public class CarrierRepoImpl implements CarrierRepo {
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
        if (id != null && arrCarrier.length != 0) {
            for (Carrier carrier : arrCarrier) {
                if (id.equals(carrier.getId())) {
                    return carrier;
                }
            }
        }
        return null;
    }

    @Override
    public Carrier[] getByName(String name) {
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
                return foundCarrier;
            }
        }
        return null;
    }

    @Override
    public Carrier[] getAll() {
        if (arrCarrier.length != 0) {
            return arrCarrier;
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public void print(Carrier carrier) {
        System.out.println(carrier);
    }

    @Override
    public void printAll() {
        for (Carrier carrier : arrCarrier) {
            if (carrier != null) {
                System.out.println(carrier);
            }
        }
    }
}
