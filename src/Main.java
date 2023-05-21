import config.DatabaseConfiguration;
import food.delivery.repository.CustomerRepository;
import food.delivery.service.CustomerService;
import food.delivery.service.DatabaseService;

import java.sql.Connection;
import java.text.ParseException;

import java.util.*;
public class Main {
    public static void main(String[] args) throws ParseException {

        Scanner in = new Scanner(System.in);
        boolean end = false;

        DatabaseService dbService = new DatabaseService();

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        CustomerRepository customerRepository = new CustomerRepository(databaseConnection);
        CustomerService customerService = new CustomerService(customerRepository);


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
                    //case "help" -> printAllCommands();
                    case "end" -> end = true;
                }
               /* if(availableCommands.contains(command))
                    auditService.logAction(command);*/
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }

    }
}