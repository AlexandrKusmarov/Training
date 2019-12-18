package main.java.transportation.service.impl;

import main.java.transportation.domain.Transportation;
import main.java.transportation.repo.TransportationArrRepo;
import main.java.transportation.repo.impl.TransportationArrRepoImpl;
import main.java.transportation.service.TransportationArrService;

public class TransportationArrServiceImpl implements TransportationArrService {
    private TransportationArrRepo transportationArrRepo = new TransportationArrRepoImpl();

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
    public Transportation[] getAll() {
        return transportationArrRepo.getAll();
    }

    @Override
    public void print(Transportation transportation) {
        System.out.println(transportation);
    }

    @Override
    public void printAll(Transportation[] transportations) {
        for (Transportation transportation : transportations) {
            System.out.println(transportation);
        }
    }
}
