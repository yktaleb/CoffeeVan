package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.BeverageDao;
import ua.training.dao.BeverageQualityDao;
import ua.training.entity.Beverage;
import ua.training.entity.BeverageQuality;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BeverageDaoImpl extends AbstractDao<Beverage> implements BeverageDao {
    public BeverageDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[0];
    }

    @Override
    protected void setEntityParameters(Beverage entity, PreparedStatement statement) {
    }
}
