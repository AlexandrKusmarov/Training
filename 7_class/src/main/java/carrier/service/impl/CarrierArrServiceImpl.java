package main.java.carrier.service.impl;

import main.java.carrier.domain.Carrier;
import main.java.carrier.repo.CarrierArrRepo;
import main.java.carrier.repo.impl.CarrierArrRepoImpl;
import main.java.carrier.service.CarrierArrService;
import main.java.storage.Storage;

public class CarrierArrServiceImpl implements CarrierArrService {
    private CarrierArrRepo carrierArrRepo = new CarrierArrRepoImpl();

    @Override
    public void add(Carrier carrier) {
        carrierArrRepo.add(carrier);
    }

    @Override
    public Carrier getById(Long id) {
        return carrierArrRepo.getById(id);
    }

    @Override
    public Carrier[] getByName(String name) {
        return carrierArrRepo.getByName(name);
    }

    @Override
    public Carrier[] getAll() {
        return carrierArrRepo.getAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return carrierArrRepo.deleteById(id);
    }

    @Override
    public void print(Carrier carrier) {
        System.out.println(carrier);
    }

    @Override
    public void printAll() {
        for (Carrier carrier : Storage.arrCarrier) {
            if (carrier != null) {
                System.out.println(carrier);
            }
        }
    }
}
