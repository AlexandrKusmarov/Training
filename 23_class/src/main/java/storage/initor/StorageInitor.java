package storage.initor;

import common.business.exception.checked.InitStorageException;

import java.sql.SQLException;

public interface StorageInitor {
  void initStorage() throws InitStorageException, SQLException;
}
