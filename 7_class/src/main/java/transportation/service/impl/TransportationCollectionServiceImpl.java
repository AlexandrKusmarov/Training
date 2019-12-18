package main.java.transportation.service.impl;

import main.java.transportation.domain.Transportation;
import main.java.transportation.service.TransportationCollectionService;

import java.util.List;

public class TransportationCollectionServiceImpl implements TransportationCollectionService {
    private TransportationCollectionService transportService = new TransportationCollectionServiceImpl();

    @Override
    public void add(Transportation transportation) {
        transportService.add(transportation);
    }

    @Override
    public Transportation getById(Long id) {
        return transportService.getById(id);
    }

    @Override
    public List<Transportation> getAll() {
        return transportService.getAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return transportService.deleteById(id);
    }
}
