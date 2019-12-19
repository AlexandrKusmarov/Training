package main.java.carrier.repo;

import main.java.carrier.domain.Carrier;
import main.java.common.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo {
    void add(Carrier carrier);

    Carrier getById(Long id);

    Carrier[] getByName(String name);

    Carrier[] getAll();

}
