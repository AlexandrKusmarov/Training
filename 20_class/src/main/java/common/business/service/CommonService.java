package common.business.service;

import java.util.List;
import java.util.Optional;

public interface CommonService<TYPE, ID> {
  Optional<TYPE> findById(ID id);

  void save(TYPE entity);

  boolean upLocalDateTime(TYPE entity);

  boolean deleteById(ID id);

  List<TYPE> getAll();

  int countAll();

  void printAll();
}
