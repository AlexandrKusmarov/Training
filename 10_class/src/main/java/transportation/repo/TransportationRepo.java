package main.java.transportation.repo;

import main.java.common.business.repo.CommonRepo;
import main.java.transportation.domain.Transportation;

import java.util.List;

public interface TransportationRepo<T extends Transportation> extends CommonRepo {
    void add(T transportation);

    T getById(Long id);

    List<Transportation> getAll();

    void update(T transportation);
}
