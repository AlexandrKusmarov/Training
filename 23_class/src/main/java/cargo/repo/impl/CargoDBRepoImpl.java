package cargo.repo.impl;

import cargo.domain.Cargo;
import cargo.domain.CargoType;
import cargo.search.CargoSearchCondition;
import carrier.domain.Carrier;
import common.solutions.utils.db.ConnectionBuilder;
import common.solutions.utils.db.DbUtils;
import common.solutions.utils.db.QuerySql;
import db.ResultSetManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CargoDBRepoImpl extends CommonCargoRepo {
    @Override
    public Optional<Cargo> getByIdFetchingTransportations(long id) throws SQLException {
        return findById(id);
    }

    @Override
    public Cargo[] findByName(String name) throws SQLException {
        List<Cargo> cargos = new ArrayList<>();
        ResultSet resultSet = DbUtils.executeQuery(QuerySql.SELECT_CARGOS_BY_NAME, ps ->
                ps.setString(1, name)
        );
        while (resultSet.next()) {
            cargos.add(ResultSetManager.mapCargo(resultSet));
        }
        return cargos.toArray(new Cargo[0]);
    }

    @Override
    public List<Cargo> search(CargoSearchCondition cargoSearchCondition) {
        return null;
    }


    @Override
    public Optional<Cargo> findById(Long aLong) throws SQLException {
        ResultSet resultSet = DbUtils.executeQuery(QuerySql.SELECT_CARGO_BY_ID, ps -> {
            ps.setLong(1, aLong);
        });
        if (resultSet.next()) {
            return Optional.of(ResultSetManager.mapCargo(resultSet));
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
    public void saveAll(List<Cargo> cargos, List<Carrier> carriers) throws SQLException {
        Connection con = ConnectionBuilder.getConnection();
        PreparedStatement psCargo = null;
        PreparedStatement psCarrier = null;
        con.setAutoCommit(false);
        try {
            if (!cargos.isEmpty()) {
                psCargo = con.prepareStatement(QuerySql.INSERT_NEW_CARGO_CLOTHERS);
                for (Cargo cargo : cargos) {
                    if (cargo.getCargoType().equals(CargoType.CLOTHERS)) {
                        ResultSetManager.getFilledPreparedStatement(cargo, psCargo).addBatch();
                    }
                }
                psCargo.executeBatch();
                psCargo = con.prepareStatement(QuerySql.INSERT_NEW_CARGO_FOOD);
                for (Cargo cargo : cargos) {
                    if (cargo.getCargoType().equals(CargoType.FOOD)) {
                        ResultSetManager.getFilledPreparedStatement(cargo, psCargo).addBatch();
                    }
                }
                psCargo.executeBatch();
            }
            if (!carriers.isEmpty()) {
                psCarrier = con.prepareStatement(QuerySql.INSERT_NEW_CARRIER);
                for (Carrier carrier : carriers) {
                    ResultSetManager.getFilledPreparedStatement(carrier, psCarrier).addBatch();
                }
                psCarrier.executeBatch();
            }
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            if (psCargo != null && psCarrier != null) {
                psCargo.close();
                psCarrier.close();
            }
            con.close();
        }
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
