package main.java.transportation.repo;

import main.java.common.business.repo.CommonRepo;
import main.java.transportation.domain.Transportation;

import java.util.List;

public interface TransportationRepo extends CommonRepo {
    void add(Transportation transportation);

    <Transportation> Transportation getById(Long id);

    List<Transportation> getAll();

    void update(Transportation transportation);
}
