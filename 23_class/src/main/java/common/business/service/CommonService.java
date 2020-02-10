package common.business.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CommonService<TYPE, ID> {
  Optional<TYPE> findById(ID id) throws SQLException;

  void save(TYPE entity);

  boolean update(TYPE entity);

  boolean deleteById(ID id) throws SQLException;

  List<TYPE> getAll();

  int countAll() throws SQLException;

  void printAll();
}
