package food.delivery.repository;
import food.delivery.model.Customer;
import food.delivery.model.Product;
import food.delivery.model.Restaurant;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Restaurant> getAll() throws SQLException {
        List<Restaurant> restaurants = new ArrayList<>();
        String sql = "SELECT id, name, address FROM restaurants";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Restaurant restaurant = mapResultSetToRestaurant(resultSet);
                restaurant.setMenu(this.getMenu(restaurant.getId()));
                restaurants.add(restaurant);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return restaurants;
    }

    private List<Product> getMenu(String restaurantId) {

        List<Product> menu = new ArrayList<>();
        String sql = "SELECT m.product_id, p.name, p.price FROM menu m " +
                "INNER JOIN products p ON p.id = m.product_id " +
                "WHERE m.restaurant_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, restaurantId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = mapResultSetToProduct(resultSet);
                menu.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return menu;
    }

    private Restaurant mapResultSetToRestaurant(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");

        return new Restaurant(id, name, address);
    }

    private Product mapResultSetToProduct(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("product_id");
        double price = resultSet.getDouble("price");
        String name = resultSet.getString("name");

        return new Product(name, price, id);
    }
}
