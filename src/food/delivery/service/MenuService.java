package food.delivery.service;

import food.delivery.model.Customer;
import food.delivery.repository.MenuRepository;

import java.awt.*;
import java.sql.SQLException;

public class MenuService {

    private final MenuRepository menuRepository;
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void assignMenu(String restaurantId, String productId) {
        this.menuRepository.create(restaurantId, productId);
        System.out.println("Product with ID " + productId + " has been assigned to the menu with Restaurant ID " + restaurantId);
    }
}
