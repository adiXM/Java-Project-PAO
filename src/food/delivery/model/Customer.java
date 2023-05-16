package food.delivery.model;

import java.util.Date;

public class Customer extends Person {

    protected String username;

    protected String address;

    public Customer(String firstName, String lastName, Date birthDate, String email, String phone, String username, String address) {
        super(firstName, lastName, birthDate, email, phone);
        this.username = username;
        this.address = address;
    }

    public Customer() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return
                "first name: " + firstName + '\'' +
                        ", lastname: " + lastName + '\'' +
                        ", email: " + email + '\'' +
                        ", phone: " + phone + '\'' +
                        ", username: " + username + '\'' +
                        ", birthdate:" + birthDate + '\n'
                ;
    }
}
