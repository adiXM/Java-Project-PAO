package food.delivery.service;

import config.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseService {

    private static final String[] TABLES = {
            "event_logs",
            "order_line_items",
            "orders",
            "product_ingredients",
            "menu",
            "restaurants",
            "products",
            "drivers",
            "customers",
    };

    private static final String[] QUERY_CREATE_TABLES = {
            "customers (id VARCHAR(36) PRIMARY KEY, username varchar(30) UNIQUE, firstname varchar(20), lastname varchar(20), email varchar(30), birthdate date, phone varchar(10), address varchar(20))",
            "drivers (id VARCHAR(36) PRIMARY KEY, username varchar(30) UNIQUE, firstname varchar(20), lastname varchar(20), email varchar(30),total_orders int, total_income float, birthdate date, phone varchar(10))",
            "products (id VARCHAR(36) NOT NULL, name VARCHAR(255), price DECIMAL(10,2), PRIMARY KEY (id));",
            "restaurants (id VARCHAR(36) NOT NULL, name VARCHAR(255), address VARCHAR(255), PRIMARY KEY (id));",
            "menu (restaurant_id VARCHAR(36) NOT NULL, product_id VARCHAR(36) NOT NULL, FOREIGN KEY (restaurant_id) REFERENCES restaurants(id), FOREIGN KEY (product_id) REFERENCES products(id));",
            "product_ingredients (product_id VARCHAR(36) NOT NULL, ingredient VARCHAR(255), FOREIGN KEY (product_id) REFERENCES products(id));",
            "orders (id VARCHAR(36) NOT NULL, customer_id VARCHAR(36), driver_id VARCHAR(36), status VARCHAR(255), created_at DATETIME, updated_at DATETIME, PRIMARY KEY (id), FOREIGN KEY (customer_id) REFERENCES customers(id), FOREIGN KEY (driver_id) REFERENCES drivers(id));",
            "order_line_items (order_id VARCHAR(36) NOT NULL, product_name VARCHAR(255), quantity INT, FOREIGN KEY (order_id) REFERENCES orders(id));",
            "event_logs (id VARCHAR(36) NOT NULL, action VARCHAR(255), date DATE, PRIMARY KEY (id));"
    };

    public void setUpTables() {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String sql = "CREATE TABLE IF NOT EXISTS ";

        try {
            for (String query: QUERY_CREATE_TABLES) {
                this.executeSql(databaseConnection, sql + query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTables() {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String query = "DROP TABLE IF EXISTS ";

        for (String tabel: TABLES) {
            try {
                this.executeSql(databaseConnection, query + tabel);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void executeSql(Connection connection, String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
    }
}
