package main.java.carrier.repo;

import main.java.carrier.domain.Carrier;
import main.java.common.business.repo.CommonRepo;

import java.util.List;

public interface CarrierRepo extends CommonRepo {
    void add(Carrier carrier);

    Carrier getById(Long id);

    List<Carrier> getByName(String name);

    List<Carrier> getAll();

    void update(Carrier carrier);

}
