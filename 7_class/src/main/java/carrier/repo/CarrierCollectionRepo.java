package main.java.carrier.repo;

import main.java.carrier.domain.Carrier;
import java.util.List;

public interface CarrierCollectionRepo {
    void add(Carrier carrier);

    Carrier getById(Long id);

    List<Carrier> getByName(String name);

    List<Carrier> getAll();

    boolean deleteById(Long id);
}
