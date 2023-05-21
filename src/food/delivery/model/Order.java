package food.delivery.model;
import java.util.*;

public class Order {

    private final String id = UUID.randomUUID().toString();
    private Customer customer;
    private Driver driver;
    private List<OrderLineItem> orderLineItems = new ArrayList<>();
    private String status;
    private Date createdAt;
    private Date updatedAt;

    public Order(Customer customer, Driver driver, List<OrderLineItem> orderLineItems, String status, Date createdAt, Date updatedAt) {
        this.customer = customer;
        this.driver = driver;
        this.orderLineItems = orderLineItems;
        this.status = status;
        this.createdAt = createdAt;
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
