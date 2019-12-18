package main.java.carrier.service.impl;

import main.java.carrier.domain.Carrier;
import main.java.carrier.service.CarrierArrService;
import main.java.storage.Storage;

public class CarrierArrServiceImpl implements CarrierArrService {
    private CarrierArrService arrService = new CarrierArrServiceImpl();

    @Override
    public void add(Carrier carrier) {
        arrService.add(carrier);
    }

    @Override
    public Carrier getById(Long id) {
        return arrService.getById(id);
    }

    @Override
    public Carrier[] getByName(String name) {
        return arrService.getByName(name);
    }

    @Override
    public Carrier[] getAll() {
        return arrService.getAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return arrService.deleteById(id);
    }

    @Override
    public void print(Carrier carrier) {
        System.out.println(carrier);
    }

    @Override
    public void printAll(Carrier[] carriers) {
        for (Carrier carrier : Storage.arrCarrier) {
            if (carrier != null) {
                System.out.println(carrier);
            }
        }
    }
}
