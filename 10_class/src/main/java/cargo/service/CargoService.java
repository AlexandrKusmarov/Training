package main.java.cargo.service;

import main.java.cargo.domain.Cargo;
import main.java.cargo.search.CargoSearchCondition;

import java.util.List;

public interface CargoService<T extends Cargo> {
    void add(T cargo);

    T getById(Long id);

    List<Cargo> getByName(String name);

    List<Cargo> getAll();

    boolean deleteById(Long id);

    void print(T cargo);

    void printAll();

    void update(T cargo);

//    void sort(List<Cargo> cargoList, Comparator<Cargo> comp);

    T getByIdFetchingTransportations(Long id);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
