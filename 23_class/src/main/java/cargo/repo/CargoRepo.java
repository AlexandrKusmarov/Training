package cargo.repo;

import cargo.domain.Cargo;
import cargo.search.CargoSearchCondition;
import carrier.domain.Carrier;
import common.business.repo.CommonRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

  Optional<Cargo> getByIdFetchingTransportations(long id) throws SQLException;

  Cargo[] findByName(String name) throws SQLException;

  List<Cargo> search(CargoSearchCondition cargoSearchCondition);

  void saveAll(List<Cargo> cargos, List<Carrier> carriers) throws SQLException;
}
