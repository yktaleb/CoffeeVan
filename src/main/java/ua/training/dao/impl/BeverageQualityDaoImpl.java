package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.BeverageQualityDao;
import ua.training.dao.BeverageTypeDao;
import ua.training.entity.BeverageQuality;
import ua.training.entity.BeverageType;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BeverageQualityDaoImpl extends AbstractDao<BeverageQuality> implements BeverageQualityDao {
    public BeverageQualityDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[0];
    }

    @Override
    protected void setEntityParameters(BeverageQuality entity, PreparedStatement statement) {
    }
}
