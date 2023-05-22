package food.delivery.service;

import food.delivery.model.Ingredient;
import food.delivery.model.Product;
import food.delivery.repository.CustomerRepository;
import food.delivery.repository.IngredientRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public void createIngredient(Ingredient ingredient, Product product) throws SQLException {
        this.ingredientRepository.create(ingredient, product.getId());
    }

}
