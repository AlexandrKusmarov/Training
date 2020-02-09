package common.solutions.utils.db;

public class QuerySql {
    private QuerySql() {
    }

    public static final String SELECT_CARGOS_BY_NAME = "SELECT * FROM cargo WHERE name=?";
    public static final String SELECT_CARGO_BY_ID = "SELECT * FROM cargo WHERE id=?";
    public static final String UPDATE_CARGO_CLOTHERS = "UPDATE cargo SET name=?, weight=?, cargoType=?, size=?, material=?, " + "WHERE id=?";
    public static final String UPDATE_CARGO_FOOD = "UPDATE cargo SET name=?, weight=?, cargoType=?, expirationLocalDateTime=?, storeTemperature=? " + "WHERE id=?";
    public static final String SELECT_ALL_CARGOS = "SELECT * FROM cargo";
    public static final String DELETE_CARGO_BY_ID = "DELETE FROM cargo where id=?";
    public static final String COUNT_ALL_CARGOS = "SELECT COUNT(*) as number FROM cargo";
    public static final String INSERT_NEW_CARGO_CLOTHERS = "INSERT INTO cargo " +
            "(name, weight, cargoType, size, material, id)" +
            " VALUES(?, ?, ?, ?, ?, ?)";
    public static final String INSERT_NEW_CARGO_FOOD = "INSERT INTO cargo " +
            "(name, weight, cargoType, expirationLocalDateTime, storeTemperature, id)" +
            " VALUES(?, ?, ?, ?, ?, ?)";

}
