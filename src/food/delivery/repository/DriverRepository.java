package food.delivery.repository;

import food.delivery.model.Customer;
import food.delivery.model.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverRepository {

    private final Connection connection;

    public DriverRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(Driver driver) throws SQLException {
        String sql = "INSERT INTO drivers (id, username, firstname, lastname, email, total_orders, total_income, phone, birthdate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, driver.getId());
            statement.setString(2, driver.getUsername());
            statement.setString(3, driver.getFirstName());
            statement.setString(4, driver.getLastName());
            statement.setString(5, driver.getEmail());
            statement.setInt(6, driver.getTotalOrders());
            statement.setDouble(7, driver.getTotalIncome());
            statement.setString(8, driver.getPhone());
            statement.setDate(9, new java.sql.Date(driver.getBirthDate().getTime()));
            statement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Driver getByUsername(String username) {
        Driver driver = null;
        String sql = "SELECT * FROM drivers WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                int totalOrders = resultSet.getInt("total_orders");
                double totalIncome = resultSet.getDouble("total_income");
                Date birthDate = resultSet.getDate("birthdate");

                // Create a new Driver object
                driver = new Driver(id, username, firstName, lastName, birthDate, email, phone, totalOrders, totalIncome);
            } else {
                System.out.println("Driver with username " + username + " does not exist.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return driver;
    }

    public void delete(Driver driver) throws SQLException {
        String sql = "DELETE FROM drivers WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, driver.getId());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Driver with ID " + driver.getId() + " deleted successfully.");
            } else {
                System.out.println("Failed to delete customer. Please check the provided customer ID.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Driver> getAll() throws SQLException {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM drivers";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Driver driver = mapResultSetToDriver(resultSet);
                drivers.add(driver);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return drivers;
    }

    private Driver mapResultSetToDriver(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String firstName = resultSet.getString("firstname");
        String lastName = resultSet.getString("lastname");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        int totalOrders = resultSet.getInt("total_orders");
        double totalIncome = resultSet.getInt("total_income");
        Date birthDate = resultSet.getDate("birthDate");

        return new Driver(id, username, firstName, lastName, birthDate, email,phone, totalOrders, totalIncome);
    }
}
