package ua.training.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProxyUtil {
    public static Long getIdDesiredColumnByQuery(Connection connection,String query, Long currentId) {
        Long id = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, currentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
