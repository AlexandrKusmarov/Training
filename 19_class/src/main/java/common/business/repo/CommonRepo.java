package common.business.repo;

import cargo.domain.Cargo;

import java.util.List;
import java.util.Optional;

public interface CommonRepo<TYPE, ID> {

  Optional<Cargo> findById(ID id);

  void save(TYPE entity);

  boolean update(TYPE entity);

  boolean deleteById(ID id);

  List<TYPE> getAll();

  int countAll();
}
