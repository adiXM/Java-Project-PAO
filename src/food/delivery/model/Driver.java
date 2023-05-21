package food.delivery.model;

import java.util.Date;

public class Driver extends Person {

    protected int totalOrders;

    protected double totalIncome;

    public Driver(String id, String firstName, String lastName, Date birthDate, String email, String phone, int totalOrders, double totalIncome) {
        super(id, firstName, lastName, birthDate, email, phone);
        this.totalOrders = 0;
        this.totalIncome = 0;
    }
}
