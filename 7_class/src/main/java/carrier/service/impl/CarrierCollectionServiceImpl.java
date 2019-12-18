package main.java.carrier.service.impl;

import main.java.carrier.domain.Carrier;
import main.java.carrier.service.CarrierCollectionService;

import java.util.List;

public class CarrierCollectionServiceImpl implements CarrierCollectionService {
    private CarrierCollectionService collectionService = new CarrierCollectionServiceImpl();

    @Override
    public void add(Carrier carrier) {
        collectionService.add(carrier);
    }

    @Override
    public Carrier getById(Long id) {
        return collectionService.getById(id);
    }

    @Override
    public List<Carrier> getByName(String name) {
        return collectionService.getByName(name);
    }

    @Override
    public List<Carrier> getAll() {
        return collectionService.getAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return collectionService.deleteById(id);
    }
}
