package food.delivery.model;

import java.util.Date;

public class Driver extends Person {

    private String username;
    private int totalOrders;
    private double totalIncome;

    public Driver(String id, String username, String firstName, String lastName, Date birthDate, String email, String phone, int totalOrders, double totalIncome) {
        super(id, firstName, lastName, birthDate, email, phone);
        this.username = username;
        this.totalOrders = 0;
        this.totalIncome = 0;
    }

    public Driver(String username, String firstName, String lastName, Date birthDate, String email, String phone, int totalOrders, double totalIncome) {
        super(firstName, lastName, birthDate, email, phone);
        this.username = username;
        this.totalOrders = 0;
        this.totalIncome = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "username='" + username + '\'' +
                ", totalOrders=" + totalOrders +
                ", totalIncome=" + totalIncome +
                ", id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
