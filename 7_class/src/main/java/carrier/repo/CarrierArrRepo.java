package main.java.carrier.repo;

import main.java.carrier.domain.Carrier;

public interface CarrierArrRepo {
    void add(Carrier carrier);

    Carrier getById(Long id);

    Carrier[] getByName(String name);

    Carrier[] getAll();

    boolean deleteById(Long id);
}
