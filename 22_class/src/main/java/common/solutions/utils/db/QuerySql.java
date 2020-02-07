package common.solutions.utils.db;

public class QuerySql {
    private QuerySql() {
    }
    public static String SELECT_CARGOS_BY_NAME = "SELECT * FROM cargo WHERE name=?";
    public static String SELECT_CARGO_BY_ID = "SELECT * FROM cargo WHERE id=?";
    public static String UPDATE_CARGO_CLOTHERS = "UPDATE cargo SET name=?, weight=?, cargoType=?, size=?, material=?, " + "WHERE id=?";
    public static String UPDATE_CARGO_FOOD = "UPDATE cargo SET name=?, weight=?, cargoType=?, expirationLocalDateTime=?, storeTemperature=? " + "WHERE id=?";
    public static String SELECT_ALL_CARGOS = "SELECT * FROM cargo";
    public static String DELETE_CARGO_BY_ID = "DELETE FROM cargo where id=?";
    public static String COUNT_ALL_CARGOS = "SELECT COUNT(*) FROM cargo";
}
