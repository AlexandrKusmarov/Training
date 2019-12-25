package main.java.carrier.repo;

import main.java.carrier.domain.Carrier;
import main.java.common.business.repo.CommonRepo;

import java.util.List;

public interface CarrierRepo<T extends Carrier> extends CommonRepo {
    void add(T carrier);

    T getById(Long id);

    List<Carrier> getByName(String name);

    List<Carrier> getAll();

    void update(T carrier);

}
