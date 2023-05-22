package food.delivery.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MenuRepository {
    private final Connection connection;

    public MenuRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(String restaurantId, String productId) {
        String sql = "INSERT INTO menu (restaurant_id, product_id) " +
                "VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, restaurantId);
            statement.setString(2, productId);
            statement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
