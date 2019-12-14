package main.java.cargo.service;

import main.java.cargo.domain.Cargo;

public class CargoServiceImpl implements CargoService {
    @Override
    public void add(Cargo cargo) {

    }

    @Override
    public Cargo getById(Long id) {
        return null;
    }

    @Override
    public Cargo[] getByName(String name) {
        return new Cargo[0];
    }

    @Override
    public Cargo[] getAll() {
        return new Cargo[0];
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public void print(Cargo cargo) {

    }

    @Override
    public void printAll(Cargo[] cargos) {

    }
}
