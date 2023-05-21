package food.delivery.repository;
import food.delivery.model.Customer;

import java.sql.*;
public class CustomerRepository {

    private final Connection connection;

    public CustomerRepository(Connection connection) {
        this.connection = connection;
    }

    public void createCustomer(Customer customer) throws SQLException {
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

}
