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
import java.util.*;

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
    protected String[] getParameterNames() {
        return new String[]{VAN_STATUS, NAME, CARRYING_CAPACITY, MAX_VOLUME};
    }

    @Override
    protected void setEntityParameters(Van van, PreparedStatement statement) throws SQLException {
        statement.setLong(1, van.getVanStatus().getId());
        statement.setString(2, van.getName());
        statement.setDouble(3, van.getCarryingCapacity());
        statement.setDouble(4, van.getMaxVolume());
        if (statement.getParameterMetaData().getParameterCount() == NUMBER_OF_FIELDS_WITHOUT_ID + 1) {

        }
    }

    @Override
    public List<Van> findByStatus(Long statusId) {
        List<Van> result = new ArrayList<>();
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE)
                .where()
                .condition(TABLE, VAN_STATUS)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, statusId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    if (getEntityFromResultSet(resultSet) != null) {
                        result.add(getEntityFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {

        }
        return result;
    }

    @Override
    protected Van getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = Long.valueOf(resultSet.getString(ID));
        String name = resultSet.getString(NAME);
        Double carryingCapacity = resultSet.getDouble(CARRYING_CAPACITY);
        Double maxVolume = resultSet.getDouble(MAX_VOLUME);
        Long vanStatusId = resultSet.getLong(VAN_STATUS);
        return new VanProxy.VanBuilder()
                    .setId(id)
                    .setName(name)
                    .setCarryingCapacity(carryingCapacity)
                    .setMaxVolume(maxVolume)
                    .buildVanProxy();
    }

}
