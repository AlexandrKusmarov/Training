package main.java.transportation.service.impl;

import main.java.transportation.domain.Transportation;
import main.java.transportation.repo.TransportationRepo;
import main.java.transportation.service.TransportationService;

import java.util.Arrays;
import java.util.List;

public class TransportationCollectionServiceImpl implements TransportationService {
    private TransportationRepo transportCollectionRepo;

    public TransportationCollectionServiceImpl(TransportationRepo transportCollectionRepo) {
        this.transportCollectionRepo = transportCollectionRepo;
    }

    @Override
    public void add(Transportation transportation) {
        transportCollectionRepo.add(transportation);
    }

    @Override
    public Transportation getById(Long id) {
        return transportCollectionRepo.getById(id);
    }

    @Override
    public List<Transportation> getAll() {
        return Arrays.asList(transportCollectionRepo.getAll());
    }

    @Override
    public boolean deleteById(Long id) {
        return transportCollectionRepo.deleteById(id);
    }

    @Override
    public void print(Transportation transportation) {
        System.out.println(transportation);
    }

    @Override
    public void printAll() {
        for (Transportation transportation : transportCollectionRepo.getAll()) {
            System.out.println(transportation);
        }
    }
}
