package food.delivery.service;

import config.DatabaseConfiguration;
import food.delivery.model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseService {

    public void setUpTables() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS customers" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(30)," +
                "age double)";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();

        try {
            this.executeSql(databaseConnection, createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeSql(Connection connection, String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
    }
}
