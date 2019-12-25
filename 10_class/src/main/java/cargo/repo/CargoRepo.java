package main.java.cargo.repo;

import main.java.cargo.domain.Cargo;
import main.java.cargo.search.CargoSearchCondition;
import main.java.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo<T extends Cargo> extends CommonRepo {
    void add(T cargo);

    T getById(Long id);

    List<Cargo> getByName(String name);

    List<Cargo> getAll();

    void update(T cargo);

//    void sort(List<Cargo> cargoList, Comparator<Cargo> comp);

    T getByIdFetchingTransportations(long id);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
