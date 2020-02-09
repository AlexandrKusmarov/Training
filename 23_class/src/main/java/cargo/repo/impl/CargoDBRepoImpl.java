package cargo.repo.impl;

import cargo.domain.Cargo;
import cargo.domain.CargoType;
import cargo.domain.ClothersCargo;
import cargo.domain.FoodCargo;
import cargo.search.CargoSearchCondition;
import common.solutions.utils.JavaUtilDateUtils;
import common.solutions.utils.db.ConnectionBuilder;
import common.solutions.utils.db.DbUtils;
import common.solutions.utils.db.QuerySql;
import db.ResultSetManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CargoDBRepoImpl extends CommonCargoRepo {
    @Override
    public Optional<Cargo> getByIdFetchingTransportations(long id) {
        return findById(id);
    }

    @Override
    public Cargo[] findByName(String name) {
        List<Cargo> cargos = new ArrayList<>();
        try (Connection con = ConnectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(QuerySql.SELECT_CARGOS_BY_NAME)) {
            ps.setString(1, name);

            ResultSet resultSet = ps.executeQuery();

//            while (resultSet.next()) {
//                Cargo cargo = ResultSetManager.mapCargo(resultSet);
//                cargo.ifPresent(cargos::add);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cargos.toArray(new Cargo[0]);
    }

    @Override
    public List<Cargo> search(CargoSearchCondition cargoSearchCondition) {
        return null;
    }

    @Override
    public Optional<Cargo> findById(Long aLong) {
        try (Connection con = ConnectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(QuerySql.SELECT_CARGO_BY_ID)) {
            ps.setLong(1, aLong);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return Optional.of(ResultSetManager.mapCargo(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Cargo entity) {
        update(entity);
    }

    @Override
    public boolean update(Cargo entity) {
        String sql = entity.getCargoType().equals(CargoType.CLOTHERS) ?
                QuerySql.UPDATE_CARGO_CLOTHERS : QuerySql.UPDATE_CARGO_FOOD;
        try (Connection con = ConnectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
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

            int rowUpdated = ps.executeUpdate();

            if (rowUpdated > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(Long aLong) {
        try (Connection con = ConnectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(QuerySql.DELETE_CARGO_BY_ID)) {
            ps.setLong(1, aLong);

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Cargo> getAll() {
        return DbUtils.selectAll(QuerySql.SELECT_ALL_CARGOS, ResultSetManager::mapCargo);
    }

    @Override
    public int countAll() {
        try (Connection con = ConnectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(QuerySql.COUNT_ALL_CARGOS)) {
            ResultSet rs = ps.executeQuery();
            rs.getRow();
            return rs.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
