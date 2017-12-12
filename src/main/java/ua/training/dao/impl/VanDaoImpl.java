package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.VanDao;
import ua.training.dao.util.QueryBuilder;
import ua.training.entity.Van;
import ua.training.entity.proxy.VanProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ua.training.util.constant.table.VanConstants.*;

public class VanDaoImpl extends AbstractDao<Van> implements VanDao {

    private VanDaoImpl(Connection connection) {
        super(TABLE, connection);
    }

    private static class VanDaoImplHolder {
        private static VanDaoImpl instance(Connection connection) {
            return new VanDaoImpl(connection);
        }
    }

    public static VanDaoImpl getInstance(Connection connection) {
        return VanDaoImplHolder.instance(connection);
    }

    @Override
    public List<Van> findByStatus(Long statusId) throws SQLException {
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE)
                .where()
                .condition(TABLE, VAN_STATUS)
                .build();
        return getEntityListByQuery(query, statusId);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[]{VAN_STATUS, NAME, CARRYING_CAPACITY, MAX_VOLUME};
    }

    @Override
    protected void setEntityParameters(Van van, PreparedStatement statement) throws SQLException {
        statement.setLong(1, van.getVanStatus().getId());
        statement.setString(2, van.getName());
        statement.setDouble(3, van.getCarryingCapacity());
        statement.setDouble(4, van.getMaxVolume());
    }

    @Override
    protected Van getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = Long.valueOf(resultSet.getString(ID));
        String name = resultSet.getString(NAME);
        Double carryingCapacity = resultSet.getDouble(CARRYING_CAPACITY);
        Double maxVolume = resultSet.getDouble(MAX_VOLUME);
        return new VanProxy.VanBuilder()
                .setId(id)
                .setName(name)
                .setCarryingCapacity(carryingCapacity)
                .setMaxVolume(maxVolume)
                .buildVanProxy();
    }

}
