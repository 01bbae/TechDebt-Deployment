package com.cs370.springdemo.model;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This is the main service of the system implementing Command API for processing CLI commands and
 * ModelService API for processing Store events
 */
public class StoreModelService implements StoreModelAPI, CommandAPI {

    private static final Map<String, Store> storeMap;
    private static final Map<String, Customer> customerMap;
    private static final Map<String, Product> productMap;
    private static final Map<String, Inventory> inventoryMap;
    private static final Map<String, Basket> basketMap;
    private static final Map<String, Device> deviceMap;

    private static StoreModelService storeModelService;

    // Initialize genesis block and the account list
    static {
        storeMap = new HashMap<>();
        customerMap = new HashMap<>();
        productMap = new HashMap<>();
        inventoryMap = new HashMap<>();
        basketMap = new HashMap<>();
        deviceMap = new HashMap<>();
    }

    /**
     * Singleton Pattern implementation
     *
     * @return
     */
    public static synchronized StoreModelService getInstance() {
        if (storeModelService == null) {
            storeModelService = new StoreModelService();
        }
        return storeModelService;
    }


    @Override
    public void processCommand(String commandBefore) throws CommandException, StoreModelException {

        List<String> tokens = new ArrayList<>();
        //Split the line into tokens between spaces and quotes
        Matcher matcher = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(commandBefore);
        while (matcher.find())
            tokens.add(matcher.group(1).replace("\"", ""));

        System.out.println(">>> Processing DSL : " + commandBefore);

        String command = commandBefore.trim().replaceAll(" +", " ");

        if (command.toLowerCase().contains("define store")) {
            Store store = provisionStore(tokens.get(2), tokens.get(4), tokens.get(6), null);

            //System.out.println(store);
        } else if (command.toLowerCase().contains("show store")) {

            System.out.println("<<< " + showStore(tokens.get(2), null));

        } else if (command.toLowerCase().contains("define aisle")) {

            String[] location = tokens.get(2).split(":");
            Aisle aisle = provisionAisle(location[0], location[1], tokens.get(4), tokens.get(6),
                    AisleLocation.valueOf(tokens.get(8)), null);
            //System.out.println(aisle);

        } else if (command.toLowerCase().contains("show aisle")) {
            String[] location = tokens.get(2).split(":");

            System.out.println("<<< " + showAisle(location[0], location[1], null));

        } else if (command.toLowerCase().contains("define shelf")) {

            String[] location = tokens.get(2).split(":");
            Shelf shelf = provisionShelf(location[0], location[1], location[2], tokens.get(4), ShelfLevel.valueOf(tokens.get(6)),
                    tokens.get(8), Temperature.valueOf(tokens.get(10)), null);

            //System.out.println(shelf);

        } else if (command.toLowerCase().contains("show shelf")) {
            String[] location = tokens.get(2).split(":");

            System.out.println("<<< " + showShelf(location[0], location[1], location[2], null));

        } else if (command.toLowerCase().contains("define product")) {

            Product product = provisionProduct(tokens.get(2), tokens.get(4), tokens.get(6),
                    tokens.get(8), tokens.get(10), Double.parseDouble(tokens.get(12)),
                    Temperature.valueOf(tokens.get(14)), null);

            //System.out.println(product);

        } else if (command.toLowerCase().contains("show product")) {

            Product product = showProduct(tokens.get(2), null);
            System.out.println("<<< " + product);
        } else if (command.toLowerCase().contains("define inventory")) {

            String[] location = tokens.get(4).split(":");

            Inventory inventory = provisionInventory(tokens.get(2), location[0], location[1],
                    location[2], Integer.parseInt(tokens.get(6)), Integer.parseInt(tokens.get(8)),
                    tokens.get(10), null);

            //System.out.println(inventory);
        } else if (command.toLowerCase().contains("show inventory")) {

            System.out.println("<<< " + showInventory(tokens.get(2), null));

        } else if (command.toLowerCase().contains("update inventory")) {

            Inventory inventory = updateInventory(tokens.get(2), Integer.parseInt(tokens.get(4)), null);
            System.out.println(inventory);

        } else if (command.toLowerCase().contains("define customer")) {

            Customer customer = provisionCustomer(tokens.get(2), tokens.get(4), tokens.get(6),
                    CustomerType.valueOf(tokens.get(8)), tokens.get(10), tokens.get(12), null);

            //System.out.println(customer);
        } else if (command.toLowerCase().contains("update customer")) {

            String[] location = tokens.get(4).split(":");
            Customer customer = updateCustomer(tokens.get(2), location[0], location[1], null);

            System.out.println("<<< " + customer);
        } else if (command.toLowerCase().contains("show customer")) {

            System.out.println(showCustomer(tokens.get(2), null));
        } else if (command.toLowerCase().contains("define basket")) {

            Basket basket = provisionBasket(tokens.get(2), null);

            //System.out.println(basket);
        } else if (command.toLowerCase().contains("assign basket")) {

            Basket basket = assignCustomerBasket(tokens.get(4), tokens.get(2), null);
            //System.out.println(basket);
        } else if (command.toLowerCase().contains("get_customer_basket")) {

            Basket basket = getCustomerBasket(tokens.get(1), null);
            System.out.println("<<< " + basket);
        } else if (command.toLowerCase().contains("add_basket_item")) {

            Basket basket = addBasketProduct(tokens.get(1), tokens.get(3),
                    Integer.parseInt(tokens.get(5)), null);
            System.out.println("<<< " + basket);
        } else if (command.toLowerCase().contains("remove_basket_item")) {

            Basket basket = removeBasketProduct(tokens.get(1), tokens.get(3),
                    Integer.parseInt(tokens.get(5)), null);
            System.out.println(basket);
        } else if (command.toLowerCase().contains("clear_basket")) {

            Basket basket = clearBasket(tokens.get(1), null);
            System.out.println("<<< " + basket);
        } else if (command.toLowerCase().contains("show basket_items")) {

            Basket basket = showBasket(tokens.get(2), null);
            System.out.println("<<< " + basket);
        } else if (command.toLowerCase().contains("define device")) {

            String[] location = tokens.get(8).split(":");
            Device device = provisionDevice(tokens.get(2), tokens.get(4),
                    tokens.get(6), location[0], location[1], null);

            //System.out.println(device);
        } else if (command.toLowerCase().contains("show device")) {
            System.out.println("<<< " + showDevice(tokens.get(2), null));
        } else if (command.toLowerCase().contains("create event")) {
            raiseEvent(tokens.get(2), tokens.get(4) + " " + tokens.get(5), null);
        } else if (command.toLowerCase().contains("create_event")) {
            raiseEvent(tokens.get(1), tokens.get(3) + " " + tokens.get(4) + " " + tokens.get(5), null);
        } else if (command.toLowerCase().contains("create command")) {
            issueCommand(tokens.get(2), tokens.get(4) + " " + tokens.get(5), null);
        } else {
            throw new CommandException(command, "Unrecognized Command");
        }
    }

    @Override
    public void processCommandFile(String fileName) {
        Path path = FileSystems.getDefault().getPath(Path.of(fileName).toAbsolutePath().toString());
        List<String> tokens = new ArrayList<>();

        //Process all the lines in the file
        try (var stream = Files.lines(path)) {
            tokens = stream
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator<String> iterator = tokens.iterator();

        //Filter out any empty lines and lines that start with #
        for (int i = 0; iterator.hasNext(); i++) {
            String temp = iterator.next();
            if (!temp.trim().startsWith("#") && temp.trim().length() != 0) {
                try {
                    processCommand(temp);
                } catch (CommandException e) {
                    e.setLineNumber(i + 1);
                    System.out.println("\u001B[31m" + "Failed due to: " + e.getReason() + " for Command: " + e.getCommand()
                            + " On Line Number: " + e.getLineNumber() + "\u001B[0m");
                } catch (StoreModelException e) {
                    System.out.println("\u001B[31m" + "Failed due to: " + e.getReason() + " for Command: " + e.getAction() + "\u001B[0m");
                }
            }
        }

    }

    @Override
    public Store provisionStore(String storeId, String name, String address, String token)
            throws StoreModelException {

        Store store = new Store(storeId, name, address);

        //If Store already exists throw and exception
        if (storeMap.putIfAbsent(storeId, store) != null) {
            throw new StoreModelException("Provision Store", "Store Already Exists");
        }
        return store;
    }

    @Override
    public Store showStore(String storeId, String token) throws StoreModelException {

        //If Store does not exist throw and exception
        Store store = storeMap.get(storeId);
        if (store == null)
            throw new StoreModelException("Show Store", "Store Does Not Exist");

        return store;
    }

    @Override
    public Aisle provisionAisle(String storeId, String aisleNumber, String name, String description,
                                AisleLocation location, String token) throws StoreModelException {

        Store store = storeMap.get(storeId);
        Aisle aisle;

        //Check to see if Store already exists;
        if (store == null) {
            throw new StoreModelException("Provision Aisle", "Store Does Not Exist");
        } else {
            aisle = store.addAisle(aisleNumber, name, description, location);
        }

        return aisle;
    }

    @Override
    public Aisle showAisle(String storeId, String aisleNumber, String token) throws StoreModelException {
        Store store = storeMap.get(storeId);
        Aisle aisle;
        //Check to see if Store exists
        if (store == null) {
            throw new StoreModelException("Show Aisle", "Store Does Not Exist");
        } else {
            //Check to see if Aisle already exists
            aisle = store.getAisle(aisleNumber);
            if (aisle == null) {
                throw new StoreModelException("Show Aisle", "Aisle Does Not Exist");
            }
        }
        return aisle;
    }

    @Override
    public Shelf provisionShelf(String storeId, String aisleNumber, String shelfId, String name,
                                ShelfLevel level, String description, Temperature temperature, String token) throws StoreModelException {

        Store store = storeMap.get(storeId);
        Shelf shelf;

        //Check to see if Store exists
        if (store == null) {
            throw new StoreModelException("Provision Shelf", "Store Does Not Exist");
        } else {
            Aisle aisle = store.getAisle(aisleNumber);
            //Check to see if Aisle exists
            if (aisle == null) {
                throw new StoreModelException("Provision Shelf", "Aisle Does Not Exist");
            } else {
                shelf = aisle.getShelf(shelfId);
                //Check to see if Shelf exists
                if (shelf != null) {
                    throw new StoreModelException("Provision Shelf", "Shelf Already Exists");
                }

                //Add Shelf to the Aisle
                shelf = aisle.addShelf(shelfId, name, level, description, temperature);
            }
        }
        return shelf;
    }

    @Override
    public Shelf showShelf(String storeId, String aisleNumber, String shelfId, String token) throws StoreModelException {
        Store store = storeMap.get(storeId);
        Shelf shelf;

        //Check to see if Store exists
        if (store == null) {
            throw new StoreModelException("Show Shelf", "Store Does Not Exist");
        } else {
            //Check to see if Aisle exists
            Aisle aisle = store.getAisle(aisleNumber);
            if (aisle == null) {
                throw new StoreModelException("Show Shelf", "Aisle Does Not Exist");
            } else {
                //Check to see if Shelf exists
                shelf = aisle.getShelf(shelfId);
                if (shelf == null) {
                    throw new StoreModelException("Show Shelf", "Shelf Does Not Exist");
                }
            }
        }
        return shelf;
    }

    @Override
    public Inventory provisionInventory(String inventoryId, String storeId, String aisleNumber, String shelfId,
                                        int capacity, int count, String productId, String token) throws StoreModelException {

        Store store = storeMap.get(storeId);
        Product product = productMap.get(productId);
        Inventory inventory;

        //Check to see if Store exists
        if (store == null) {
            throw new StoreModelException("Provision Inventory", "Store Does Not Exist");
        } else {
            //Check to see if Aisle exists
            Aisle aisle = store.getAisle(aisleNumber);
            if (aisle == null) {
                throw new StoreModelException("Provision Inventory", "Aisle Does Not Exist");
            } else {
                //Check to see if Shelf exists
                Shelf shelf = aisle.getShelf(shelfId);
                if (shelf == null) {
                    throw new StoreModelException("Provision Inventory", "Shelf Does Not Exist");
                } else if (product == null) {
                    //Check to see if Product exists
                    throw new StoreModelException("Provision Inventory", "Product Does Not Exist");
                } else if (!shelf.getTemperature().equals(product.getTemperature())) {
                    //Make sure that Product Temperature and Shelf Temperature are consistent
                    throw new StoreModelException("Provision Inventory", "Product and Shelf Temperature " +
                            "Is Not Consistent");
                }

                //Add Inventory to the Shelf
                inventory = shelf.addInventory(inventoryId, storeId, aisleNumber, shelfId,
                        capacity, count, productId);

                //Add Inventory to the global Inventory Map
                inventoryMap.put(inventoryId, inventory);

                //Add Inventory to the Store
                store.addInventory(inventory);

            }
        }

        return inventory;
    }

    @Override
    public Inventory showInventory(String inventoryId, String token) throws StoreModelException {

        Inventory inventory = inventoryMap.get(inventoryId);
        //Check to see if Inventory exists
        if (inventory == null)
            throw new StoreModelException("Show Inventory", "Inventory Does Not Exist");
        return inventory;
    }

    @Override
    public Inventory updateInventory(String inventoryId, int count, String token) throws StoreModelException {
        Inventory inventory = inventoryMap.get(inventoryId);
        //Check to see if Inventory exists
        if (inventory == null)
            throw new StoreModelException("Update Inventory", "Inventory Does Not Exist");

        //Update Inventory count
        inventory.updateInventory(count);

        return inventory;
    }

    @Override
    public Product provisionProduct(String productId, String name, String description, String size, String category,
                                    double price, Temperature temperature, String token) throws StoreModelException {
        Product product = new Product(productId, name, description, size, category, price, temperature);

        //Check to see if Product already exists
        if (productMap.putIfAbsent(productId, product) != null)
            throw new StoreModelException("Provision Product", "Product Already Exists");

        return product;
    }

    @Override
    public Product showProduct(String productId, String token) throws StoreModelException {
        Product product = productMap.get(productId);
        //Check to see if Product exists
        if (product == null)
            throw new StoreModelException("Show Product", "Product Does Not Exist");
        return product;
    }

    @Override
    public Customer provisionCustomer(String customerId, String firstName, String lastName,
                                      CustomerType type, String email, String address, String token)
            throws StoreModelException {

        Customer customer = new Customer(customerId, firstName, lastName, type, email, address);
        //Check to see if the Customer already exists
        if (customerMap.putIfAbsent(customerId, customer) != null)
            throw new StoreModelException("Provision Customer", "Customer Already Exists");

        return customer;
    }

    @Override
    public Customer updateCustomer(String customerId, String storeId, String aisleNumber, String token)
            throws StoreModelException {
        Store store = storeMap.get(storeId);
        Customer customer;

        //Check to see if the Store exists
        if (store == null) {
            throw new StoreModelException("Update Customer", "Store Does Not Exist");
        } else {
            //Check to see if Aisle exists
            Aisle aisle = store.getAisle(aisleNumber);
            if (aisle == null) {
                throw new StoreModelException("Update Customer", "Aisle Does Not Exist");
            } else {
                //Check to see if Customer exists
                customer = customerMap.get(customerId);
                if (customer == null) {
                    throw new StoreModelException("Update Customer", "Customer Does Not Exist");
                }
            }
        }

        //Check to see if Customer changing Stores
        if (customer.getStoreLocation() != null && !customer.getStoreLocation().getStoreId().equals(storeId)) {
            //Check to see if Customer already exists in other Stores
            Map<Store, Customer> customerStores = storeMap.entrySet()
                    .stream()
                    .filter(tempStore -> (tempStore.getValue().getCustomer(customerId) != null && tempStore.getValue().getCustomer(customerId).getId().equals(customerId)))
                    .collect(Collectors.toMap(Map.Entry::getValue, tempStore -> tempStore.getValue().
                            getCustomer(customerId)));

            //If Customer exist in other stores remove him/her
            customerStores.forEach((key, value) -> key.removeCustomer(customer));

            //Before Customer can change the Store he/she must clear the Basket
            if (customer.getBasket() != null)
                customer.getBasket().clearBasket();

            //If the Customer moves to a different Store clear out the basket and the time seen
            customer.assignBasket(null);
            customer.setLastSeen(null);

            //Add Customer to another store
            store.addCustomer(customer);
        } else {

            customer.setStoreLocation(new StoreLocation(storeId, aisleNumber));
            customer.setLastSeen(new Date(System.currentTimeMillis()));
        }

        return customer;
    }

    @Override
    public Customer showCustomer(String customerId, String token) throws StoreModelException {

        //Check to see if the Customer exists
        Customer customer = customerMap.get(customerId);
        if (customer == null)
            throw new StoreModelException("Show Customer", "Customer Does Not Exist");

        return customer;
    }

    @Override
    public Basket provisionBasket(String basketId, String token) throws StoreModelException {

        Basket basket = new Basket(basketId);
        //Check if Basket already exists
        if (basketMap.putIfAbsent(basketId, basket) != null)
            throw new StoreModelException("Provision Basket", "Basket Already Exists");

        return basket;
    }

    @Override
    public Basket assignCustomerBasket(String customerId, String basketId, String token) throws StoreModelException {

        Customer customer = customerMap.get(customerId);
        Basket basket = basketMap.get(basketId);

        //Check to see Customer and the Basket already exist
        if (customer == null) {
            throw new StoreModelException("Assign Customer Basket", "Customer Does Not Exist");
        } else {
            if (basket == null) {
                throw new StoreModelException("Assign Customer Basket", "Basket Does Not Exist");
            }
        }

        //Assign Basket to the Customer
        customer.assignBasket(basket);
        //Keep the global copy of all the baskets
        basketMap.put(basketId, basket);

        Store store = storeMap.get(customerMap.get(customerId).getStoreLocation().getStoreId());

        //Associate basket with the customer
        basket.setCustomer(customer);
        //Create bidirectional association between Store and the Basket
        basket.setStore(store);
        store.addBasket(basket);

        return basket;
    }

    @Override
    public Basket getCustomerBasket(String customerId, String token) throws StoreModelException {
        Customer customer = customerMap.get(customerId);
        Basket basket;

        //Check if Customer exists
        if (customer == null) {
            throw new StoreModelException("Get Customer Basket", "Customer Does Not Exist");
        } else {
            basket = customer.getBasket();
            //Check to see if Customer has been assigned the Basket
            if (basket == null) {
                throw new StoreModelException("Get Customer Basket", "Customer Does Not Have a Basket");
            }
        }
        return basket;
    }

    @Override
    public Basket addBasketProduct(String basketId, String productId, int count, String token)
            throws StoreModelException {
        Basket basket = basketMap.get(basketId);
        Product product = productMap.get(productId);

        //Check to see if basket already exists product we are trying to add to the basket
        //exists as well and basket has been assigned to the customer
        if (basket == null) {
            throw new StoreModelException("Add Basket Product", "Basket Does Not Exist");
        } else if (product == null) {
            throw new StoreModelException("Add Basket Product", "Product Does Not Exist");
        } else if (basket.getCustomer() == null) {
            throw new StoreModelException("Add Basket Product", "Basket Has Not Being Assigned");
        }
        //Add a product to the basket
        basket.addProduct(productId, count);

        return basket;
    }

    @Override
    public Basket removeBasketProduct(String basketId, String productId, int count, String token) throws StoreModelException {
        Basket basket = basketMap.get(basketId);
        Product product = productMap.get(productId);

        //Check to see if basket already exists product we are trying to add to the basket
        //exists as well and basket has been assigned to the customer
        if (basket == null) {
            throw new StoreModelException("Remove Basket Product", "Basket Does Not Exist");
        } else if (product == null) {
            throw new StoreModelException("Remove Basket Product", "Product Does Not Exist");
        } else if (basket.getCustomer() == null) {
            throw new StoreModelException("Remove Basket Product", "Basket Has Not Being Assigned");
        }
        //Remove product from the basket
        basket.removeProduct(productId, count);

        return basket;
    }

    @Override
    public Basket clearBasket(String basketId, String token) throws StoreModelException {

        Basket basket = basketMap.get(basketId);

        //Check to see if basket already exists and basket has been assigned to the customer
        if (basket == null) {
            throw new StoreModelException("Clear Basket", "Basket Does Not Exist");
        } else if (basket.getCustomer() == null) {
            throw new StoreModelException("Clear Basket", "Basket Has Not Being Assigned");
        }
        basket.clearBasket();

        return basket;
    }

    @Override
    public Basket showBasket(String basketId, String token) throws StoreModelException {
        Basket basket = basketMap.get(basketId);

        //Check to see if basket already exists and basket has been assigned to the customer
        if (basket == null) {
            throw new StoreModelException("Show Basket Product", "Basket Does Not Exist");
        } else if (basket.getCustomer() == null) {
            throw new StoreModelException("Show Basket Product", "Basket Has Not Being Assigned");
        }

        return basket;
    }

    @Override
    public Device provisionDevice(String deviceId, String name, String deviceType, String storeId,
                                  String aisleNumber, String token) throws StoreModelException {

        Store store = storeMap.get(storeId);
        Device device;
        StoreLocation storeLocation;

        //Check to see if store exists
        if (store == null) {
            throw new StoreModelException("Provision Device", "Store Does Not Exist");
        } else {

            //Check to see if aisle exists
            Aisle aisle = store.getAisle(aisleNumber);
            if (aisle == null) {
                throw new StoreModelException("Provision Device", "Aisle Does Not Exist");
            } else {
                storeLocation = new StoreLocation(storeId, aisleNumber);

                //Check to see if device already exists
                device = deviceMap.get(deviceId);
                if (device != null) {
                    throw new StoreModelException("Provision Device", "Device Already Exists");
                }

                //Determine wha type of device we are trying to add
                for (SensorType sensor : SensorType.values()) {
                    if (sensor.name().equals(deviceType)) {
                        device = new Sensor(deviceId, name, storeLocation, deviceType);
                    }
                }
                for (ApplianceType appliance : ApplianceType.values()) {
                    if (appliance.name().equals(deviceType)) {
                        device = new Appliance(deviceId, name, storeLocation, deviceType);
                    }
                }

                //Add device to the global map
                deviceMap.put(deviceId, device);
                //Add device to the local store
                store.addDevice(device);

            }
        }
        return device;
    }

    @Override
    public Device showDevice(String deviceId, String token) throws StoreModelException {
        Device device = deviceMap.get(deviceId);

        //Check to see if device exists
        if (device == null)
            throw new StoreModelException("Show Device", "Device Does Not Exist");

        return device;
    }

    @Override
    public void raiseEvent(String deviceId, String event, String token) throws StoreModelException {
        Device device = deviceMap.get(deviceId);

        //Check to see if device exists
        if (device == null) {
            throw new StoreModelException("Raise Event", "Device Does Not Exist");
        }
        device.processEvent(event);

    }

    @Override
    public void issueCommand(String deviceId, String command, String token) throws StoreModelException {

        Appliance appliance = (Appliance) deviceMap.get(deviceId);

        //Check to see if appliance exists
        if (appliance == null) {
            throw new StoreModelException("Issue Command", "Device Does Not Exist");
        }
        appliance.processCommand(command);
    }
}
