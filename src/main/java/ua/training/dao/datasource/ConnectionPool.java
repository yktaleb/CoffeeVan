package ua.training.dao.datasource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {
    private ConnectionPool() {
    }

    public static DataSource getDataSource() {
        return initDataSource();
    }

    private static DataSource initDataSource() {
        try {
            InitialContext initialContext = new InitialContext();
            return (DataSource) initialContext.lookup("java:comp/env/jdbc/coffee-van");
        } catch (NamingException e) {
            return null;
        }
    }
}

