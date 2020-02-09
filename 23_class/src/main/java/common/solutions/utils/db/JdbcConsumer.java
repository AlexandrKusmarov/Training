package common.solutions.utils.db;

public interface JdbcConsumer<T> {
    void accept(T t) throws Exception;
}
