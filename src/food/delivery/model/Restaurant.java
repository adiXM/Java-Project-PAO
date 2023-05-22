package food.delivery.model;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Restaurant {

    private final String id;
    private String name;
    private String address;
    private List<Product> menu;

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
        menu = new ArrayList<Product>();
        this.id = UUID.randomUUID().toString();
    }

    public Restaurant(String id, String name, String address) {
        this.name = name;
        this.address = address;
        menu = new ArrayList<Product>();
        this.id = id;
    }

    public Restaurant(String name, String address, String id, List<Product> menu) {
        this.name = name;
        this.address = address;
        this.menu = menu;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", adress='" + address + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Product> getMenu() {
        return menu;
    }

    public void setMenu(List<Product> menu) {
        this.menu = menu;
    }

    public void addProduct(Product product){
        this.menu.add(product);
    }
}
