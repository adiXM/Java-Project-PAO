package food.delivery.service;

import food.delivery.model.Customer;
import food.delivery.model.Ingredient;
import food.delivery.model.Product;
import food.delivery.repository.ProductRepository;

import java.sql.SQLException;

public class ProductService {

    private final ProductRepository productRepository;
    private final IngredientService ingredientService;
    public ProductService(ProductRepository productRepository, IngredientService ingredientService) {
        this.productRepository = productRepository;
        this.ingredientService = ingredientService;
    }
    public void createProduct(Product product) {
        try {
            this.productRepository.create(product);

            for (String ingredient: product.getIngredients()) {
                this.ingredientService.createIngredient(new Ingredient(ingredient), product);
            }

            System.out.println("New product created in the database.");
        } catch (SQLException e) {
            System.out.println("Failed to create a new entry in the database.");
            e.printStackTrace();
        }
    }
}
