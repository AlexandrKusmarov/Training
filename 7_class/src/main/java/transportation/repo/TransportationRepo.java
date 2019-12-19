package main.java.transportation.repo;

import main.java.common.repo.CommonRepo;
import main.java.transportation.domain.Transportation;

public interface TransportationRepo extends CommonRepo {
    void add(Transportation transportation);

    Transportation getById(Long id);

    Transportation[] getAll();
}
