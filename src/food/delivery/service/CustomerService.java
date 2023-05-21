package food.delivery.service;

import food.delivery.model.Customer;
import food.delivery.repository.CustomerRepository;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CustomerService {

    private DatabaseService databaseService;

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void createCustomer(Scanner scanner) throws ParseException {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Birth Date (yyyy-MM-dd): ");
        String birthDateStr = scanner.nextLine();
        Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateStr);

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        createCustomer(new Customer(firstName, lastName, birthDate, email, phone, username, address));
    }
    public void createCustomer(Customer customer) {
        try {
            this.customerRepository.createCustomer(customer);
            System.out.println("New customer created in the database.");
        } catch (SQLException e) {
            System.out.println("Failed to create a new entry in the database.");
            e.printStackTrace();
        }
    }

    public void updateCustomer(Customer customer) {
        /*try {
            customerRepository.updateCustomer(customer);
            System.out.println("Customer updated in database..");
        } catch (SQLException e) {
            System.out.println("Failed to create a new entry in the database.");
            e.printStackTrace();
        }*/
    }
}
