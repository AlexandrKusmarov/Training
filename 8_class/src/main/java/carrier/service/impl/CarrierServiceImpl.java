package main.java.carrier.service.impl;

import main.java.carrier.domain.Carrier;
import main.java.carrier.domain.CarrierType;
import main.java.carrier.repo.CarrierRepo;
import main.java.carrier.service.CarrierService;
import main.java.storage.Storage;
import main.java.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.List;

public class CarrierServiceImpl implements CarrierService {
    private CarrierRepo carrierArrRepo;

    public CarrierServiceImpl(CarrierRepo carrierArrRepo) {
        this.carrierArrRepo = carrierArrRepo;
    }

    @Override
    public void add(Carrier carrier) {
        carrierArrRepo.add(carrier);
    }

    @Override
    public Carrier getById(Long id) {
        return carrierArrRepo.getById(id);
    }

    @Override
    public List<Carrier> getByName(String name) {
        return carrierArrRepo.getByName(name);
    }

    @Override
    public List<Carrier> getAll() {
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
        for (Carrier carrier : carrierArrRepo.getAll()) {
            if (carrier != null) {
                System.out.println(carrier);
            }
        }
    }

    @Override
    public void update(Long id, String name, String address, CarrierType carrierType, Transportation[] transportation) {
        carrierArrRepo.update(id, name, address, carrierType, transportation);
    }
}
