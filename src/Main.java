import com.mysql.cj.log.Log;
import config.DatabaseConfiguration;
import food.delivery.model.Driver;
import food.delivery.model.EventLog;
import food.delivery.repository.*;
import food.delivery.service.*;

import java.sql.Connection;
import java.text.ParseException;

import java.util.*;
public class Main {

    private static final List<String> commands = Arrays.asList(
            "create_tables",
            "remove_tables",
            "create_customer",
            "get_customer",
            "update_customer",
            "delete_customer",
            "get_all_customers",
            "create_driver",
            "get_driver",
            "delete_driver",
            "create_restaurant",
            "delete_restaurant",
            "get_restaurant",
            "create_order",
            "help",
            "end");

    static List<String> commandsDescriptions = Arrays.asList(
            "Create db tables if not exists",
            "Remove all db tables",
            "Create a new customer",
            "Get customer data by username",
            "Update customer data by username",
            "Delete a customer by username",
            "Print all the customers",
            "Create a new driver",
            "Get driver data by username",
            "Delete a driver by username",
            "Add a new restaurant",
            "Delete a restaurant by name",
            "Get restaurant data by name",
            "Create a new order",
            "Print all commands",
            "End"
    );

    public static void main(String[] args) throws ParseException {

        Scanner in = new Scanner(System.in);
        boolean end = false;

        DatabaseService dbService = new DatabaseService();

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();

        CustomerRepository customerRepository = new CustomerRepository(databaseConnection);
        RestaurantRepository restaurantRepository = new RestaurantRepository(databaseConnection);
        ProductRepository productRepository = new ProductRepository(databaseConnection);
        IngredientRepository ingredientRepository = new IngredientRepository(databaseConnection);
        MenuRepository menuRepository = new MenuRepository(databaseConnection);
        DriverRepository driverRepository = new DriverRepository(databaseConnection);
        OrderRepository orderRepository  = new OrderRepository(databaseConnection);
        LoggerRepository loggerRepository = new LoggerRepository(databaseConnection);

        CustomerService customerService = new CustomerService(customerRepository);
        DriverService driverService = new DriverService(driverRepository);
        MenuService menuService = new MenuService(menuRepository);
        IngredientService ingredientService = new IngredientService(ingredientRepository);
        ProductService productService = new ProductService(productRepository, ingredientService);
        RestaurantService restaurantService = new RestaurantService(restaurantRepository, productService, menuService);
        OrderService orderService = new OrderService(restaurantService, customerService, driverService, orderRepository);
        LoggerService loggerService = new LoggerService(loggerRepository);

        while (!end){
            System.out.println("Insert command: (help - see commands)");
            String command = in.nextLine().toLowerCase(Locale.ROOT);
            try{
                switch (command) {
                    case "create_tables" -> dbService.setUpTables();
                    case "remove_tables" -> dbService.dropTables();
                    case "create_customer" -> customerService.createCustomer(in);
                    case "get_customer" -> customerService.getCustomer(in);
                    case "update_customer" -> customerService.updateCustomer(in);
                    case "delete_customer" -> customerService.deleteCustomer(in);
                    case "get_all_customers" -> customerService.getAllCustomers();
                    case "create_driver" -> driverService.createDriver(in);
                    case "get_driver" -> driverService.getDriver(in);
                    case "delete_driver" -> driverService.deleteDriver(in);
                    case "create_restaurant" -> restaurantService.createRestaurant(in);
                    case "delete_restaurant" -> restaurantService.deleteRestaurant(in);
                    case "get_restaurant" -> restaurantService.getRestaurant(in);
                    case "create_order" -> orderService.createOrder(in);
                    case "help" -> printCommands();
                    case "end" -> end = true;
                }
                if(commands.contains(command))
                    loggerService.createLog(new EventLog(command, new Date()));
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

    private static void printCommands() {
        for(int i = 0;i < commands.size(); ++i)
            System.out.println((i+1) + ". " + commands.get(i) + " (" + commandsDescriptions.get(i) + ")");
    }
}