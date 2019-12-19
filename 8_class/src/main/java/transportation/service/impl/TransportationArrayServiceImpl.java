package main.java.transportation.service.impl;

import main.java.cargo.domain.Cargo;
import main.java.carrier.domain.Carrier;
import main.java.transportation.domain.Transportation;
import main.java.transportation.repo.TransportationRepo;
import main.java.transportation.service.TransportationService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TransportationArrayServiceImpl implements TransportationService {
    private TransportationRepo transportationArrRepo;

    public TransportationArrayServiceImpl(TransportationRepo transportationArrRepo) {
        this.transportationArrRepo = transportationArrRepo;
    }

    @Override
    public void add(Transportation transportation) {
        transportationArrRepo.add(transportation);
    }

    @Override
    public Transportation getById(Long id) {
        return transportationArrRepo.getById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return transportationArrRepo.deleteById(id);
    }

    @Override
    public List<Transportation> getAll() {
        return transportationArrRepo.getAll();
    }

    @Override
    public void print(Transportation transportation) {
        System.out.println(transportation);
    }

    @Override
    public void printAll() {
        for (Transportation transportation : transportationArrRepo.getAll()) {
            System.out.println(transportation);
        }
    }

    @Override
    public void update(Long id, Cargo cargo, Carrier carrier, String description, String billTo, Date date) {
        transportationArrRepo.update(id, cargo, carrier, description, billTo, date);
    }
}
