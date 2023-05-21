package food.delivery.service;

import food.delivery.model.Customer;
import food.delivery.repository.CustomerRepository;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
            this.customerRepository.create(customer);
            System.out.println("New customer created in the database.");
        } catch (SQLException e) {
            System.out.println("Failed to create a new entry in the database.");
            e.printStackTrace();
        }
    }

    public void getCustomer(Scanner scanner) throws SQLException {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        Customer customer = this.getCustomer(username);

        if(customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Something went wrong.");
        }
    }

    public Customer getCustomer(String username) throws SQLException {
        return this.customerRepository.getByUsername(username);
    }

    public void updateCustomer(Scanner scanner) throws SQLException, ParseException {

        System.out.print("Enter customer username: ");
        String username = scanner.nextLine();

        Customer existingCustomer = this.getCustomer(username);

        System.out.print("Enter updated first name (leave empty to keep existing value \"" + existingCustomer.getFirstName() + "\"): ");
        String updatedFirstName = scanner.nextLine();
        updatedFirstName = updatedFirstName.isEmpty() ? existingCustomer.getFirstName() : updatedFirstName;

        System.out.print("Enter updated last name (leave empty to keep existing value \"" + existingCustomer.getLastName() + "\"): ");
        String updatedLastName = scanner.nextLine();
        updatedLastName = updatedLastName.isEmpty() ? existingCustomer.getLastName() : updatedLastName;

        System.out.print("Enter updated Birth Date (yyyy-MM-dd) (leave empty to keep existing value \"" + existingCustomer.getBirthDate() + "\"): ");
        String birthDateStr = scanner.nextLine();
        Date birthDateUpdated = existingCustomer.getBirthDate();
        if (!birthDateStr.isEmpty()) {
            birthDateUpdated = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateStr);
        }

        System.out.print("Enter updated email (leave empty to keep existing value \"" + existingCustomer.getEmail() + "\"): ");
        String updatedEmail = scanner.nextLine();
        updatedEmail = updatedEmail.isEmpty() ? existingCustomer.getEmail() : updatedEmail;

        System.out.print("Enter updated phone (leave empty to keep existing value \"" + existingCustomer.getPhone() + "\"): ");
        String updatedPhone = scanner.nextLine();
        updatedPhone = updatedPhone.isEmpty() ? existingCustomer.getPhone() : updatedPhone;

        System.out.print("Enter updated address (leave empty to keep existing value \"" + existingCustomer.getAddress() + "\"): ");
        String updatedAddress = scanner.nextLine();
        updatedAddress = updatedAddress.isEmpty() ? existingCustomer.getAddress() : updatedAddress;

        this.updateCustomer(new Customer(existingCustomer.getId(), updatedFirstName, updatedLastName, birthDateUpdated, updatedEmail, updatedPhone, username, updatedAddress));
    }

    public void updateCustomer(Customer customer) {
        customerRepository.update(customer);
    }

    public void deleteCustomer(Scanner scanner) throws SQLException, ParseException {

        System.out.print("Enter customer username: ");
        String username = scanner.nextLine();

        Customer existingCustomer = this.getCustomer(username);

        this.deleteCustomer(existingCustomer);
    }

    public void deleteCustomer(Customer customer) throws SQLException {
        customerRepository.delete(customer);
    }

    public void getAllCustomers() throws SQLException {
        List<Customer> customerList = customerRepository.getAll();

        if(customerList.size() == 0) {
            System.out.println("No customers found in database.");
            return;
        }

        for (Customer customer: customerList) {
            System.out.println(customer);
        }
    }
}
