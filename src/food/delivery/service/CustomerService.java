package food.delivery.service;

import food.delivery.model.Customer;
import food.delivery.repository.CustomerRepository;

import java.sql.SQLException;

public class CustomerService {

    private DatabaseService databaseService;

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public void createCustomer(Customer customer) {
        try {
            customerRepository.createCustomer(customer);
            System.out.println("New customer created in the database.");
        } catch (SQLException e) {
            System.out.println("Failed to create a new entry in the database.");
            e.printStackTrace();
        }
    }

    public void updateCustomer(Customer customer) {
        try {
            customerRepository.updateCustomer(customer);
            System.out.println("Customer updated in database..");
        } catch (SQLException e) {
            System.out.println("Failed to create a new entry in the database.");
            e.printStackTrace();
        }
    }
}
