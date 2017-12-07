package ua.training.util;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtil {
    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
