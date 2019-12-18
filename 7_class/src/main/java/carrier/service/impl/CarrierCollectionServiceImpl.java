package main.java.carrier.service.impl;

import main.java.carrier.domain.Carrier;
import main.java.carrier.repo.CarrierCollectionRepo;
import main.java.carrier.repo.impl.CarrierCollectionRepoImpl;
import main.java.carrier.service.CarrierCollectionService;

import java.util.List;

public class CarrierCollectionServiceImpl implements CarrierCollectionService {
    private CarrierCollectionRepo collectionRepo = new CarrierCollectionRepoImpl();

    @Override
    public void add(Carrier carrier) {
        collectionRepo.add(carrier);
    }

    @Override
    public Carrier getById(Long id) {
        return collectionRepo.getById(id);
    }

    @Override
    public List<Carrier> getByName(String name) {
        return collectionRepo.getByName(name);
    }

    @Override
    public List<Carrier> getAll() {
        return collectionRepo.getAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return collectionRepo.deleteById(id);
    }
}
