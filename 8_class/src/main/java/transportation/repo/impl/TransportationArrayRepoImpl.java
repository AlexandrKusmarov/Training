package main.java.transportation.repo.impl;

import main.java.cargo.domain.Cargo;
import main.java.carrier.domain.Carrier;
import main.java.storage.IdGenerator;
import main.java.transportation.domain.Transportation;
import main.java.transportation.repo.TransportationRepo;
import main.java.util.ArrayCapacityChanger;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static main.java.storage.Storage.arrTransportation;
import static main.java.storage.Storage.currentIndexTransportation;

public class TransportationArrayRepoImpl implements TransportationRepo {

    @Override
    public void add(Transportation transportation) {
        if (transportation != null) {
            if (currentIndexTransportation < arrTransportation.length) {
                transportation.setId(IdGenerator.generateId());
                arrTransportation[currentIndexTransportation] = transportation;
                currentIndexTransportation++;
            } else {
                arrTransportation = (Transportation[]) ArrayCapacityChanger.expandArrCapacity(arrTransportation);
                add(transportation);
            }
        }
    }

    @Override
    public Transportation getById(Long id) {
        for (Transportation transportation : arrTransportation) {
            if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
                return transportation;
            }
        }
        return null;
    }

    @Override
    public List<Transportation> getAll() {
        if (arrTransportation.length != 0) {
            return Arrays.asList(arrTransportation);
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        int len = arrTransportation.length;
        for (int i = 0; i < len; i++) {
            if (arrTransportation[i].getId().equals(id)) {
                arrTransportation[i] = null;
                arrTransportation = (Transportation[]) ArrayCapacityChanger.shiftArrFromEndToIndexByOnePos(arrTransportation, i);
                if (len > 1) {
                    arrTransportation = (Transportation[]) ArrayCapacityChanger.constrictionArrCapacityByOne(arrTransportation);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void printAll() {
        for (Transportation transportation : arrTransportation) {
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
