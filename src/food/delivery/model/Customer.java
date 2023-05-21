package food.delivery.model;

import java.util.Date;

public class Customer extends Person {

    protected String username;

    protected String address;

    public Customer(String id, String firstName, String lastName, Date birthDate, String email, String phone, String username, String address) {
        super(id, firstName, lastName, birthDate, email, phone);
        this.username = username;
        this.address = address;
    }

    public Customer(String firstName, String lastName, Date birthDate, String email, String phone, String username, String address) {
        super(firstName, lastName, birthDate, email, phone);
        this.username = username;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
