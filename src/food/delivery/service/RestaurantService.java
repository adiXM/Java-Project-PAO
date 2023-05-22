package food.delivery.service;

import food.delivery.model.Customer;
import food.delivery.model.Ingredient;
import food.delivery.model.Product;
import food.delivery.model.Restaurant;
import food.delivery.repository.RestaurantRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ProductService productService;
    private final MenuService menuService;

    public RestaurantService(RestaurantRepository restaurantRepository, ProductService productService, MenuService menuService) {
        this.restaurantRepository = restaurantRepository;
        this.productService = productService;
        this.menuService = menuService;
    }

    public void createRestaurant(Scanner scanner) throws SQLException {

        System.out.print("Enter restaurant name: ");
        String name = scanner.nextLine();

        System.out.print("Enter restaurant address: ");
        String address = scanner.nextLine();

        // Create a new restaurant object
        Restaurant restaurant = new Restaurant(name, address);

        System.out.println("Add products to the menu (enter 'done' to finish):");

        List<Product> menu = new ArrayList<>();

        while (true) {
            System.out.print("Enter product name: ");
            String productName = scanner.nextLine();

            if (productName.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter product price: ");
            double productPrice = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter ingredient names (comma-separated, leave empty if none): ");
            String ingredientNames = scanner.nextLine();


            List<String> ingredients = new ArrayList<>();

            if (!ingredientNames.isEmpty()) {
                // Extract ingredient names and save them
                String[] ingredientsArray = ingredientNames.split(",");
                for (String ingredientName : ingredientsArray) {
                    ingredientName = ingredientName.trim();
                    ingredients.add(ingredientName);
                }

                System.out.println("Ingredients added to product.");
            }

            // Create a new product object
            Product product = new Product(productName, ingredients, productPrice);
            menu.add(product);
            System.out.println("Product added to menu.");

            restaurant.setMenu(menu);

            System.out.println("Menu updated.");
        }

        this.createRestaurant(restaurant);

        for (Product product: restaurant.getMenu()) {
            this.productService.createProduct(product);
            this.menuService.assignMenu(restaurant.getId(), product.getId());
        }
    }

    public void deleteRestaurant(Scanner scanner) throws SQLException {
        System.out.print("Enter restaurant name: ");
        String name = scanner.nextLine();

        Restaurant restaurant = this.getRestaurant(name);

        this.deleteRestaurant(restaurant);
    }

    public void getRestaurant(Scanner scanner) throws SQLException {
        System.out.print("Enter restaurant name: ");
        String restaurantName = scanner.nextLine();

        if(restaurantName.isEmpty()) {
            System.out.println("Please provide a restaurant name. It cannot be empty.");
            return;
        }
        Restaurant restaurant = this.getRestaurant(restaurantName);

        if(restaurant != null) {
            System.out.println(restaurant);
        } else {
            System.out.println("Something went wrong.");
        }
    }

    public void createRestaurant(Restaurant restaurant) throws SQLException {
        this.restaurantRepository.create(restaurant);
    }

    public Restaurant getRestaurant(String restaurantName) {
        return this.restaurantRepository.getByName(restaurantName);
    }

    public void deleteRestaurant(Restaurant restaurant) throws SQLException {
        this.restaurantRepository.delete(restaurant);
    }
}
