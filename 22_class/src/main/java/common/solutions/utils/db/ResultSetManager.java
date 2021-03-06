package common.solutions.utils.db;

import cargo.domain.Cargo;
import cargo.domain.CargoType;
import cargo.domain.ClothersCargo;
import cargo.domain.FoodCargo;
import common.solutions.utils.JavaUtilDateUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Optional;

public class ResultSetManager {
    private ResultSetManager() {
    }

    public static Optional<Cargo> getCargoFromResultSet(ResultSet resultSet) throws SQLException, ParseException {

        if (resultSet != null) {
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

            return Optional.of(cargo);
        }
        return Optional.empty();
    }
}
