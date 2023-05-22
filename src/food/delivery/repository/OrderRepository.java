package food.delivery.repository;

import food.delivery.model.Order;
import food.delivery.model.Restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderRepository {

    private final Connection connection;

    public OrderRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(Order order) throws SQLException {

        String restaurantSql = "INSERT INTO orders (id, customer_id, driver_id, status, created_at, updated_at) " +
                "VALUES (?, ?, ?, ? ,?, ?)";

        try (PreparedStatement orderStatement = connection.prepareStatement(restaurantSql)) {
            orderStatement.setString(1, order.getId());
            orderStatement.setString(2, order.getCustomer().getId());
            orderStatement.setString(3, order.getDriver().getId());
            orderStatement.setString(4, order.getStatus());
            orderStatement.setDate(5, new java.sql.Date(order.getCreatedAt().getTime()));
            orderStatement.setDate(6, new java.sql.Date(order.getUpdatedAt().getTime()));

            orderStatement.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void createOrderItems(String orderId, String productName, int quantity) throws SQLException {

        String restaurantSql = "INSERT INTO order_line_items (order_id, product_name, quantity) " +
                "VALUES (?, ?, ?)";

        try (PreparedStatement orderStatement = connection.prepareStatement(restaurantSql)) {
            orderStatement.setString(1, orderId);
            orderStatement.setString(2, productName);
            orderStatement.setInt(3, quantity);

            orderStatement.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
