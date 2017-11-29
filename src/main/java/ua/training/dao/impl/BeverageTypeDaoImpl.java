package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.BeverageTypeDao;
import ua.training.dao.OrderStatusDao;
import ua.training.entity.BeverageType;
import ua.training.entity.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BeverageTypeDaoImpl extends AbstractDao<BeverageType> implements BeverageTypeDao {
    public BeverageTypeDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[0];
    }

    @Override
    protected void setEntityParameters(BeverageType entity, PreparedStatement statement) {
    }
}
