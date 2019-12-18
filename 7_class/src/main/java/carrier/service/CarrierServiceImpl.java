package main.java.carrier.service;

import main.java.carrier.domain.Carrier;

public class CarrierServiceImpl implements CarrierService {
    @Override
    public void add(Carrier carrier) {

    }

    @Override
    public Carrier getById(Long id) {
        return null;
    }

    @Override
    public Carrier[] getByName(String name) {
        return new Carrier[0];
    }

    @Override
    public Carrier[] getAll() {
        return new Carrier[0];
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public void print(Carrier carrier) {

    }

    @Override
    public void printAll(Carrier[] carriers) {

    }
}
