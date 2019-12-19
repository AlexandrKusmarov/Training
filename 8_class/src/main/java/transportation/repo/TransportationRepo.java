package main.java.transportation.repo;

import main.java.cargo.domain.Cargo;
import main.java.carrier.domain.Carrier;
import main.java.common.repo.CommonRepo;
import main.java.transportation.domain.Transportation;

import java.util.Date;
import java.util.List;

public interface TransportationRepo extends CommonRepo {
    void add(Transportation transportation);

    Transportation getById(Long id);

    List<Transportation> getAll();

    void update(Long id, Cargo cargo, Carrier carrier, String description, String billTo, Date date);
}
