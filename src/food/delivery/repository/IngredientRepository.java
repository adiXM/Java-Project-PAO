package food.delivery.repository;

import food.delivery.model.Ingredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IngredientRepository {

    private final Connection connection;

    public IngredientRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(Ingredient ingredient, String productId) throws SQLException {
        String sql = "INSERT INTO product_ingredients (product_id, ingredient) " +
                "VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, productId);
            statement.setString(2, ingredient.getName());
            statement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
