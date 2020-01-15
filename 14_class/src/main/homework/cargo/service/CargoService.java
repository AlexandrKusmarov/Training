package main.homework.cargo.service;

import main.homework.cargo.domain.Cargo;
import main.homework.cargo.search.CargoSearchCondition;
import main.homework.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo, Long> {

    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
