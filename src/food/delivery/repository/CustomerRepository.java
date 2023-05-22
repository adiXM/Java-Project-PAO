package food.delivery.repository;
import food.delivery.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private final Connection connection;

    public CustomerRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (id, username, firstname, lastname, email, phone, birthdate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getId());
            statement.setString(2, customer.getUsername());
            statement.setString(3, customer.getFirstName());
            statement.setString(4, customer.getLastName());
            statement.setString(5, customer.getEmail());
            statement.setString(6, customer.getPhone());
            statement.setDate(7, new java.sql.Date(customer.getBirthDate().getTime()));
            statement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Customer getByUsername(String username) {
        Customer customer = null;
        String sql = "SELECT * FROM customers WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                Date birthDate = resultSet.getDate("birthdate");

                // Create a new Customer object
                customer = new Customer(id, firstName, lastName, birthDate, email, phone, username, address);
            } else {
                System.out.println("Customer with username " + username + " does not exist.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return customer;
    }

    public void update(Customer customer){
        String updateSql = "UPDATE customers SET firstname = ?, lastname = ?, email = ?, birthDate = ?, phone = ?, address = ? WHERE id = ?";

        try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {

            // Set parameters in the update statement
            updateStatement.setString(1, customer.getFirstName());
            updateStatement.setString(2, customer.getLastName());
            updateStatement.setString(3, customer.getEmail());
            updateStatement.setDate(4, new java.sql.Date(customer.getBirthDate().getTime()));
            updateStatement.setString(5, customer.getPhone());
            updateStatement.setString(6, customer.getAddress());
            updateStatement.setString(7, customer.getId());

            // Execute the update statement
            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("Failed to update customer. Please check the provided username.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Customer customer) throws SQLException {
        String sql = "DELETE FROM customers WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getId());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer with ID " + customer.getId() + " deleted successfully.");
            } else {
                System.out.println("Failed to delete customer. Please check the provided customer ID.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Customer> getAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Customer customer = mapResultSetToCustomer(resultSet);
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return customers;
    }

    private Customer mapResultSetToCustomer(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String firstName = resultSet.getString("firstname");
        String lastName = resultSet.getString("lastname");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        String address = resultSet.getString("address");
        Date birthDate = resultSet.getDate("birthDate");

        return new Customer(id, firstName, lastName, birthDate, email, phone, username, address);
    }
}
