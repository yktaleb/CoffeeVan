package ua.training.dao.factory;

import ua.training.dao.datasource.ConnectionPool;

import javax.sql.DataSource;

public class DataSourceFactory {
    private DataSource dataSource;

    private DataSourceFactory() {
        dataSource = ConnectionPool.getDataSource();
    }

    private static class DataSourceFactoryHolder {
        private final static DataSourceFactory INSTANCE = new DataSourceFactory();
    }

    public static DataSourceFactory getInstance() {
        return DataSourceFactoryHolder.INSTANCE;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
