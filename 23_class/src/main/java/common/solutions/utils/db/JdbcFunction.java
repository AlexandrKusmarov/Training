package common.solutions.utils.db;

public interface JdbcFunction<FROM, TO> {
    TO apply(FROM from) throws Exception;
}
