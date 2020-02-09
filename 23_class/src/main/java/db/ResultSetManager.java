package db;

import cargo.domain.Cargo;
import cargo.domain.CargoType;
import cargo.domain.ClothersCargo;
import cargo.domain.FoodCargo;
import common.solutions.utils.JavaUtilDateUtils;
import common.solutions.utils.db.ConnectionBuilder;

import java.sql.*;
import java.time.LocalDateTime;

public class ResultSetManager {
    private ResultSetManager() {
    }

    public static Cargo mapCargo(ResultSet resultSet) throws SQLException {

        Cargo cargo;
        String cargoType = resultSet.getString("cargoType");
        if (CargoType.valueOf(cargoType).equals(CargoType.FOOD)) {
            FoodCargo foodCargo = new FoodCargo();
            LocalDateTime localDateTime = JavaUtilDateUtils.convertSqlTimeToLocalDateTime
                    (resultSet.getDate("expirationLocalDateTime"));
            foodCargo.setExpirationLocalDateTime(localDateTime);
            foodCargo.setStoreTemperature(resultSet.getInt("storeTemperature"));
            cargo = foodCargo;
        } else {
            ClothersCargo clothersCargo = new ClothersCargo();
            clothersCargo.setMaterial(resultSet.getString("material"));
            clothersCargo.setSize(resultSet.getString("size"));
            cargo = clothersCargo;
        }
        cargo.setId(resultSet.getLong("id"));
        cargo.setName(resultSet.getString("name"));
        cargo.setWeight(resultSet.getInt("weight"));

        return cargo;
    }

    public static PreparedStatement getFilledPreparedStatement(Cargo entity, PreparedStatement ps) throws SQLException {
        String cargoType = entity.getCargoType().toString();

        ps.setString(1, entity.getName());
        ps.setInt(2, entity.getWeight());
        ps.setString(3, entity.getCargoType().toString());

        if (CargoType.valueOf(cargoType).equals(CargoType.CLOTHERS)) {
            ps.setString(4, ((ClothersCargo) entity).getSize());
            ps.setString(5, ((ClothersCargo) entity).getMaterial());
        } else {
            Date sqlDate = JavaUtilDateUtils.
                    convertLocalDateTimeToSqlTime(((FoodCargo) entity).getExpirationLocalDateTime());
            ps.setDate(4, sqlDate);
            ps.setInt(5, ((FoodCargo) entity).getStoreTemperature());
        }
        ps.setLong(6, entity.getId());
        return ps;
    }
}
