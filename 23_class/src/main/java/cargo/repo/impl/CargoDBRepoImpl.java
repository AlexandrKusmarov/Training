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
        String sql = entity.getCargoType().equals(CargoType.CLOTHERS) ?
                QuerySql.INSERT_NEW_CARGO_CLOTHERS : QuerySql.INSERT_NEW_CARGO_FOOD;
        DbUtils.executeUpdate(sql, ps -> {
            ResultSetManager.getFilledPreparedStatement(entity, ps);
        });
    }

    @Override
    public boolean update(Cargo entity) {
        String sql = entity.getCargoType().equals(CargoType.CLOTHERS) ?
                QuerySql.UPDATE_CARGO_CLOTHERS : QuerySql.UPDATE_CARGO_FOOD;
        int affectedRows = DbUtils.executeUpdate(sql, ps -> {
            ResultSetManager.getFilledPreparedStatement(entity, ps);
        });

        return affectedRows == 1;
    }

    @Override
    public boolean deleteById(Long aLong) {
        int affectedRows = DbUtils.executeUpdate(QuerySql.DELETE_CARGO_BY_ID, ps ->
                ps.setLong(1, aLong));
        return affectedRows == 1;
    }

    @Override
    public List<Cargo> getAll() {
        return DbUtils.selectAll(QuerySql.SELECT_ALL_CARGOS, ResultSetManager::mapCargo);
    }

    @Override
    public int countAll() throws SQLException {

        try (Connection con = ConnectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(QuerySql.COUNT_ALL_CARGOS)) {
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("number");
            }
        }
        return 0;
    }
}
