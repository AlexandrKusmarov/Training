package main.java.common.business.repo;

public interface CommonRepo {
    boolean deleteById(Long id);

    void printAll();

    Integer getIndexById(Long id);
}
