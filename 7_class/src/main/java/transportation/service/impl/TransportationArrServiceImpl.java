package main.java.transportation.service.impl;

import main.java.transportation.domain.Transportation;
import main.java.transportation.service.TransportationArrService;

public class TransportationArrServiceImpl implements TransportationArrService {
    private TransportationArrService transportService = new TransportationArrServiceImpl();

    @Override
    public void add(Transportation transportation) {
        transportService.add(transportation);
    }

    @Override
    public Transportation getById(Long id) {
        return transportService.getById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return transportService.deleteById(id);
    }

    @Override
    public Transportation[] getAll() {
        return transportService.getAll();
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
