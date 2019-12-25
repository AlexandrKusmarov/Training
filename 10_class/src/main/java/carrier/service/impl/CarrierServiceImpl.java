package main.java.carrier.service.impl;

import main.java.carrier.domain.Carrier;
import main.java.carrier.repo.CarrierRepo;
import main.java.carrier.service.CarrierService;

import java.util.List;

public class CarrierServiceImpl implements CarrierService {
    private CarrierRepo<Carrier> carrierArrRepo;

    public CarrierServiceImpl(CarrierRepo<Carrier> carrierArrRepo) {
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
        for (Object carrier : carrierArrRepo.getAll()) {
            if (carrier != null) {
                System.out.println(carrier);
            }
        }
    }

    @Override
    public void update(Carrier carrier) {
        carrierArrRepo.update(carrier);
    }
}
