package cargo.repo.impl;

import cargo.domain.Cargo;
import cargo.search.CargoSearchCondition;
import common.solutions.utils.db.ConnectionBuilder;
import common.solutions.utils.db.QuerySql;
import common.solutions.utils.db.ResultSetManager;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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
             PreparedStatement ps = con.prepareStatement(QuerySql.SELECT_CARGO_BY_NAME)) {
            ps.setString(1, name);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Optional<Cargo> cargo = ResultSetManager.getCargoFromResultSet(resultSet);
                cargo.ifPresent(cargos::add);
            }
        } catch (SQLException | ParseException e) {
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
        return Optional.empty();
    }

    @Override
    public void save(Cargo entity) {

    }

    @Override
    public boolean upLocalDateTime(Cargo entity) {
        return false;
    }

    @Override
    public boolean deleteById(Long aLong) {
        return false;
    }

    @Override
    public List<Cargo> getAll() {
        return null;
    }

    @Override
    public int countAll() {
        return 0;
    }
}
