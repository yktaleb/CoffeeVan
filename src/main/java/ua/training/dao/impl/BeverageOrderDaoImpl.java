package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.BeverageOrderDao;
import ua.training.dao.BeverageQualityDao;
import ua.training.entity.BeverageOrder;
import ua.training.entity.BeverageQuality;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BeverageOrderDaoImpl extends AbstractDao<BeverageOrder> implements BeverageOrderDao {
    public BeverageOrderDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[0];
    }

    @Override
    protected void setEntityParameters(BeverageOrder entity, PreparedStatement statement) {
    }
}
