package food.delivery.service;

import food.delivery.model.*;
import food.delivery.repository.OrderRepository;

import javax.sound.sampled.Port;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderService {
    private final RestaurantService restaurantService;

    private final CustomerService customerService;

    private final DriverService driverService;

    private final OrderRepository orderRepository;

    public OrderService(RestaurantService restaurantService,
                        CustomerService customerService,
                        DriverService driverService,
                        OrderRepository orderRepository) {
        this.restaurantService = restaurantService;
        this.customerService = customerService;
        this.driverService = driverService;
        this.orderRepository = orderRepository;
    }
    public void createOrder(Scanner scanner) throws ParseException, SQLException {

        System.out.print("Choose the restaurant (choose a number): \n");
        List<Restaurant> restaurants = this.getRestaurants();
        for(int i = 0; i < restaurants.size(); i++) {
            System.out.println(i + ". " + restaurants.get(i).getName());
        }

        String readRestaurantIndex = scanner.nextLine();
        int restaurantIndex = Integer.parseInt(readRestaurantIndex);

        if(readRestaurantIndex.isEmpty() || restaurantIndex < 0 || restaurantIndex > restaurants.size() - 1) {
            System.out.println("Please enter a valid restaurant number");
            return;
        }

        List<Product> menu = restaurants.get(restaurantIndex).getMenu();
        System.out.print("Choose the products: \n");
        for(int i = 0; i < menu.size(); i++) {
            System.out.println(i + ". " + menu.get(i).getName() + "-----------" + menu.get(i).getPrice() + " lei");
        }

        List<OrderLineItem> orderLineItems = new ArrayList<>();

        while(true) {
            System.out.println("Enter a product index");

            String productIndexesStr = scanner.nextLine();

            if (productIndexesStr.equalsIgnoreCase("done")) {
                break;
            }

            System.out.println("Enter the quantity");

            int quantity = Integer.parseInt(scanner.nextLine());

            if(!productIndexesStr.equals("done"))
            {
                Product prod = menu.get(Integer.parseInt(productIndexesStr));

                OrderLineItem orderLineItem = new OrderLineItem(prod, quantity);

                if(orderLineItems.contains(orderLineItem)) {
                    orderLineItem.setQuantity(quantity + 1);
                }

                orderLineItems.add(orderLineItem);
            }
        }


        System.out.print("Choose the customer (choose a number): \n");
        List<Customer> customers = this.customerService.getAllCustomersAsList();
        for(int i = 0; i < customers.size(); i++) {
            System.out.println(i + ". " + customers.get(i).getFirstName() + " " + customers.get(i).getLastName());
        }

        String readCustomerIndex = scanner.nextLine();
        int customerIndex = Integer.parseInt(readCustomerIndex);

        System.out.print("Choose the driver (choose a number): \n");
        List<Driver> drivers = this.driverService.getAllDriversAsList();
        for(int i = 0; i < drivers.size(); i++) {
            System.out.println(i + ". " + drivers.get(i).getFirstName() + " " + drivers.get(i).getLastName());
        }

        String readDriverIndex = scanner.nextLine();
        int driverIndex = Integer.parseInt(readDriverIndex);

        Customer customer = customers.get(customerIndex);
        Driver driver = drivers.get(driverIndex);
        Restaurant restaurant = restaurants.get(restaurantIndex);

        Date now = new Date();
        Order order = new Order(customer, driver, restaurant, orderLineItems, "new_order", now, now);
        System.out.print("Here is your order, please confirm with yes/no: \n");
        this.orderConfirmation(order);

        String confirm = scanner.nextLine();

        if(confirm.equals("yes")) {
            this.createOrder(order);
        } else {
            System.out.println("Order process closed.");
        }
    }

    private void orderConfirmation(Order order) {
        System.out.println("Customer name: " + order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName());
        System.out.println("Driver name: " + order.getDriver().getFirstName() + " " + order.getDriver().getLastName());
        System.out.println("Restaurant: " + order.getRestaurant().getName());
        for (OrderLineItem item: order.getOrderLineItems()) {
            System.out.println(item.getProduct().getName() + "-------" + item.getQuantity());
        }
        System.out.println("Total:-------" + order.calculateTotalPrice());
    }

    public void createOrder(Order order) throws SQLException {
        this.orderRepository.create(order);
        for(OrderLineItem item: order.getOrderLineItems()) {
            this.orderRepository.createOrderItems(order.getId(), item.getProduct().getName(), item.getQuantity());
        }
    }

    private List<Restaurant> getRestaurants() throws SQLException {
        return this.restaurantService.getAllRestaurantsAsList();
    }
}
