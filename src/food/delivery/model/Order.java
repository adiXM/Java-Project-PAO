package food.delivery.model;
import java.util.*;

public class Order {

    private final String id = UUID.randomUUID().toString();
    private Customer customer;
    private Driver driver;
    private Restaurant restaurant;
    private List<OrderLineItem> orderLineItems = new ArrayList<>();
    private String status;
    private Date createdAt;
    private Date updatedAt;

    public Order(Customer customer, Driver driver, Restaurant restaurant, List<OrderLineItem> orderLineItems, String status, Date createdAt, Date updatedAt) {
        this.customer = customer;
        this.driver = driver;
        this.restaurant = restaurant;
        this.orderLineItems = orderLineItems;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(List<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;

        for (OrderLineItem lineItem : orderLineItems) {
            Product product = lineItem.getProduct();
            int quantity = lineItem.getQuantity();
            double price = product.getPrice();
            totalPrice += price * quantity;
        }

        return totalPrice;
    }
    public void addProduct(Product product, int quantity) {
        OrderLineItem orderItem = new OrderLineItem(product, quantity);
        this.orderLineItems.add(orderItem);
    }

    public void removeProduct(Product product) {
        for (OrderLineItem orderItem: orderLineItems) {
            if(Objects.equals(orderItem.getProduct().getId(), product.getId())) {
                this.orderLineItems.remove(orderItem);
                break;
            }
        }
    }
}
