package food.delivery.repository;
import food.delivery.model.Customer;
import food.delivery.model.Restaurant;

import java.sql.*;
public class RestaurantRepository {

    private final Connection connection;

    public RestaurantRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(Restaurant restaurant) throws SQLException {

        String restaurantSql = "INSERT INTO restaurants (id, name, address) VALUES (?, ?, ?)";

        try (PreparedStatement restaurantStatement = connection.prepareStatement(restaurantSql)) {
            restaurantStatement.setString(1, restaurant.getId());
            restaurantStatement.setString(2, restaurant.getName());
            restaurantStatement.setString(3, restaurant.getAddress());

            restaurantStatement.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(Restaurant restaurant) throws SQLException {
        String sql = "DELETE FROM restaurants WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, restaurant.getId());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Restaurant with ID " + restaurant.getId() + " deleted successfully.");
            } else {
                System.out.println("Failed to restaurant customer. Please check the provided customer ID.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Restaurant getByName(String restaurantName) {
        Restaurant restaurant = null;
        String sql = "SELECT * FROM restaurants WHERE name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, restaurantName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");

                // Create a new Restaurant object
                restaurant = new Restaurant(id, name, address);
            } else {
                System.out.println("Restaurant with name " + restaurantName + " does not exist.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return restaurant;
    }
}
