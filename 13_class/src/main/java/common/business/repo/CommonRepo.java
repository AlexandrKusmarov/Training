package main.java.common.business.repo;

import java.util.List;

public interface CommonRepo<TYPE, ID> {
    boolean deleteById(ID id);

    void printAll();

    TYPE findById(ID id);

    void save (TYPE entity);

    boolean update(TYPE entity);

    List<TYPE> getAll();

}
