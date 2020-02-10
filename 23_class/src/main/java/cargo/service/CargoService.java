package cargo.service;

import cargo.domain.Cargo;
import cargo.search.CargoSearchCondition;
import carrier.domain.Carrier;
import common.business.service.CommonService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CargoService extends CommonService<Cargo, Long> {

    Optional<Cargo> getByIdFetchingTransportations(Long id) throws SQLException;

    List<Cargo> findByName(String name) throws SQLException;

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

    void persistCargosAndCarriers(List<Cargo> cargos, List<Carrier> carriers) throws SQLException;

}
