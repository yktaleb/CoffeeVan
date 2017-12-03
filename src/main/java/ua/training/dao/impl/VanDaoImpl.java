package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.VanDao;
import ua.training.dao.factory.MySqlDaoFactory;
import ua.training.dao.util.QueryBuilder;
import ua.training.entity.User;
import ua.training.entity.Van;
import ua.training.entity.VanStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class VanDaoImpl extends AbstractDao<Van> implements VanDao {
    private static final String TABLE_NAME = "van";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CARRYING_CAPACITY = "carrying_capacity";
    private static final String MAX_VOLUME = "max_volume";
    private static final String VAN_STATUS = "van_status";
    private static final int NUMBER_OF_FIELDS_WITHOUT_ID = 4;

    private VanDaoImpl(Connection connection) {
        super(TABLE_NAME, connection);
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
    protected Optional<Van> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = Long.valueOf(resultSet.getString(ID));
        String name = resultSet.getString(NAME);
        Double carryingCapacity = resultSet.getDouble(CARRYING_CAPACITY);
        Double maxVolume = resultSet.getDouble(MAX_VOLUME);
        Long vanStatusId = resultSet.getLong(VAN_STATUS);
        VanStatus vanStatus = MySqlDaoFactory.getInstance(connection).createVanStatusDao().findOne(vanStatusId).get();
        return Optional.of(
                new Van.VanBuilder()
                    .setId(id)
                    .setName(name)
                    .setCarryingCapacity(carryingCapacity)
                    .setMaxVolume(maxVolume)
                    .setVanStatus(vanStatus)
                    .build()
        );
    }

    @Override
    public Set<Van> findAllByStatus(Long statusId) {
        Set<Van> result = new HashSet<>();
        String query = new QueryBuilder()
                .select()
                .from()
                .table(TABLE_NAME)
                .where()
                .condition(TABLE_NAME, VAN_STATUS)
                .built();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, statusId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    if (getEntityFromResultSet(resultSet).isPresent()) {
                        result.add(getEntityFromResultSet(resultSet).get());
                    }
                }
            }
        } catch (SQLException e) {

        }
        return result;
    }
}
