package main.java.transportation.service.impl;

import main.java.transportation.domain.Transportation;
import main.java.transportation.repo.TransportationCollectionRepo;
import main.java.transportation.repo.impl.TransportationCollectionsRepoImpl;
import main.java.transportation.service.TransportationCollectionService;

import java.util.List;

public class TransportationCollectionServiceImpl implements TransportationCollectionService {
    private TransportationCollectionRepo transportCollectionRepo = new TransportationCollectionsRepoImpl();

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
        return transportCollectionRepo.getAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return transportCollectionRepo.deleteById(id);
    }
}
