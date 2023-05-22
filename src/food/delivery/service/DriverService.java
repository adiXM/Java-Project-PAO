package food.delivery.service;

import food.delivery.model.Customer;
import food.delivery.model.Driver;
import food.delivery.repository.DriverRepository;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }
    public void createDriver(Scanner scanner) throws ParseException {
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


        this.createDriver(new Driver(username, firstName, lastName, birthDate, email, phone, 0, 0));
    }

    public void createDriver(Driver driver) {
        try {
            this.driverRepository.create(driver);
            System.out.println("New driver created in the database.");
        } catch (SQLException e) {
            System.out.println("Failed to create a new entry in the database.");
            e.printStackTrace();
        }
    }

    public void getDriver(Scanner scanner) throws SQLException {
        System.out.print("Enter driver username: ");
        String username = scanner.nextLine();

        Driver driver = this.getDriver(username);

        if(driver != null) {
            System.out.println(driver);
        } else {
            System.out.println("Something went wrong.");
        }
    }

    public Driver getDriver(String username) throws SQLException {
        return this.driverRepository.getByUsername(username);
    }

    public void deleteDriver(Scanner scanner) throws SQLException, ParseException {

        System.out.print("Enter driver username: ");
        String username = scanner.nextLine();

        Driver existingDriver = this.getDriver(username);

        this.deleteDriver(existingDriver);
    }

    public void deleteDriver(Driver driver) throws SQLException {
        this.driverRepository.delete(driver);
    }

    public List<Driver> getAllDriversAsList() throws SQLException {
        return this.driverRepository.getAll();
    }

}
