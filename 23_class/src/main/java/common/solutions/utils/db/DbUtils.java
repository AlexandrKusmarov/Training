package common.solutions.utils.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public final class DbUtils {
    private DbUtils() {

    }

    public static int executeUpdate(String sql,
                                    JdbcConsumer<PreparedStatement> psConsumer) {
        try (Connection con = ConnectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
        ) {
            psConsumer.accept(ps);
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet executeQuery(String sql, JdbcConsumer<PreparedStatement> psConsumer){
        try {
            Connection con = ConnectionBuilder.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            psConsumer.accept(ps);

            return ps.executeQuery();
        } catch (Exception e){
            throw new RuntimeException(e){};
        }
    }


    public static <T> List<T> selectAll(String sql,
                                        JdbcFunction<ResultSet, T> rsConverter) {
        try (Connection con = ConnectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ResultSet resultSet = ps.executeQuery();

            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                T t = rsConverter.apply(resultSet);
                result.add(t);
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T selectById(String sql,Long id,  JdbcFunction<ResultSet, T> rsConverter) {
        try (Connection con = ConnectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            T result = null;
            while (resultSet.next()) {
                result = rsConverter.apply(resultSet);
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
