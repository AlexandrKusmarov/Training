package common.solutions.utils.db;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionBuilder {

    private ConnectionBuilder() {
    }

    public static Connection getConnection() throws NamingException, SQLException {
        InitialContext initContext = new InitialContext();
        DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/transport");
        return ds.getConnection();
    }
}
