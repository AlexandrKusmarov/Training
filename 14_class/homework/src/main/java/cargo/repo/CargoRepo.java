package main.java.cargo.repo;

import main.java.cargo.domain.Cargo;
import main.java.cargo.search.CargoSearchCondition;
import main.java.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo {
    void add(Cargo cargo);

    Cargo getById(Long id);

    List<Cargo> getByName(String name);

    List<Cargo> getAll();

    void update(Cargo cargo);

//    void sort(List<Cargo> cargoList, Comparator<Cargo> comp);

    Cargo getByIdFetchingTransportations(long id);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
