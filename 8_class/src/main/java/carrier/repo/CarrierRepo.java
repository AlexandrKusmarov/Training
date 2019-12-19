package main.java.carrier.repo;

import main.java.carrier.domain.Carrier;
import main.java.carrier.domain.CarrierType;
import main.java.common.repo.CommonRepo;
import main.java.transportation.domain.Transportation;

import java.util.List;

public interface CarrierRepo extends CommonRepo {
    void add(Carrier carrier);

    Carrier getById(Long id);

    List<Carrier> getByName(String name);

    Carrier[] getAll();

    void update(Long id, String name, String address, CarrierType carrierType, Transportation[] transportation);

}
