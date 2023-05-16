package food.delivery.model;

import java.util.Date;

public class Driver extends Person {

    protected int totalOrders;

    protected float totalIncome;

    public Driver(int id, String firstName, String lastName, Date birthDate, String email, String phone, int totalOrders, float totalIncome) {
        super(id, firstName, lastName, birthDate, email, phone);
        this.totalOrders = 0;
        this.totalIncome = 0;
    }
}
