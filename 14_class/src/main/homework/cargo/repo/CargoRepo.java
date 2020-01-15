package main.homework.cargo.repo;

import main.homework.cargo.domain.Cargo;
import main.homework.cargo.search.CargoSearchCondition;
import main.homework.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

  Cargo getByIdFetchingTransportations(long id);

  Cargo[] findByName(String name);

  List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
