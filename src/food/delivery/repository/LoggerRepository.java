package food.delivery.repository;

import food.delivery.model.EventLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoggerRepository {

    private final Connection connection;

    public LoggerRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(EventLog log) throws SQLException {
        String sql = "INSERT INTO event_logs (id, action, date) " +
                "VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, log.getId());
            statement.setString(2, log.getAction());
            statement.setDate(3, new java.sql.Date(log.getDate().getTime()));
            statement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
