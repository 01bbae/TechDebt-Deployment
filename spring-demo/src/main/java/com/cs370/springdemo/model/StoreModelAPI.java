package com.cs370.springdemo.model;


/**
 * StoreModelAPI interface representing StoreModel API for external entities such as StoreModelController
 *
 * @author Sergey L. Sundukovskiy
 * @version 1.0
 * @since 2021-09-27
 */
public interface StoreModelAPI {

    /**
     * Method for Store provisioning
     *
     * @param storeId
     * @param name
     * @param address
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Store provisionStore(String storeId, String name, String address, String token) throws StoreModelException;

    /**
     * Method for display Store information
     *
     * @param storeId
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Store showStore(String storeId, String token) throws StoreModelException;

    /**
     * Method for provisioning Aisle in the Store
     *
     * @param storeId
     * @param aisleNumber
     * @param name
     * @param description
     * @param location
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Aisle provisionAisle(String storeId, String aisleNumber, String name,
                                String description, AisleLocation location, String token) throws StoreModelException;

    /**
     * Method for displaying Aisle information
     *
     * @param storeId
     * @param aisleNumber
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Aisle showAisle(String storeId, String aisleNumber, String token) throws StoreModelException;

    /**
     * Method for provisioning Shelf in the Aisle
     *
     * @param storeId
     * @param aisleNumber
     * @param shelfId
     * @param name
     * @param level
     * @param description
     * @param temperature
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Shelf provisionShelf(String storeId, String aisleNumber, String shelfId,
                                String name, ShelfLevel level, String description,
                                Temperature temperature, String token) throws StoreModelException;

    /**
     * Method for displaying Shelf information
     *
     * @param storeId
     * @param aisleNumber
     * @param shelfId
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Shelf showShelf(String storeId, String aisleNumber, String shelfId, String token) throws StoreModelException;

    /**
     * Method for provisioning Inventory on the Shelf
     *
     * @param inventoryId
     * @param storeId
     * @param aisleNumber
     * @param shelfId
     * @param capacity
     * @param count
     * @param productId
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Inventory provisionInventory(String inventoryId, String storeId, String aisleNumber,
                                        String shelfId, int capacity, int count, String productId,
                                        String token) throws StoreModelException;

    /**
     * Method for displaying Inventory information
     *
     * @param inventoryId
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Inventory showInventory(String inventoryId, String token) throws StoreModelException;

    /**
     * Method for updating Inventory information
     *
     * @param inventoryId
     * @param count
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Inventory updateInventory(String inventoryId, int count, String token) throws StoreModelException;

    /**
     * Method for provisioning Product
     *
     * @param productId
     * @param name
     * @param description
     * @param size
     * @param category
     * @param price
     * @param temperature
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Product provisionProduct(String productId, String name, String description, String size,
                                    String category, double price, Temperature temperature,
                                    String token) throws StoreModelException;

    /**
     * Method for displaying Product information
     *
     * @param productId
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Product showProduct(String productId, String token) throws StoreModelException;

    /**
     * Method for provisioning a Customer
     *
     * @param customerId
     * @param firstName
     * @param lastName
     * @param type
     * @param email
     * @param address
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Customer provisionCustomer(String customerId, String firstName, String lastName,
                                      CustomerType type, String email, String address, String token) throws StoreModelException;

    /**
     * Method for updating Customer information
     *
     * @param customerId
     * @param storeId
     * @param aisleNumber
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Customer updateCustomer(String customerId, String storeId, String aisleNumber, String token)
            throws StoreModelException;

    /**
     * Method for displaying Customer information
     *
     * @param customerId
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Customer showCustomer(String customerId, String token) throws StoreModelException;

    /**
     * Method for provisioning Basket
     *
     * @param basketId
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Basket provisionBasket(String basketId, String token) throws StoreModelException;

    /**
     * Method for assigning Basket to the Customer
     *
     * @param customerId
     * @param basketId
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Basket assignCustomerBasket(String customerId, String basketId, String token) throws StoreModelException;

    /**
     * Method for displaying Customer Basket information
     *
     * @param customerId
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Basket getCustomerBasket(String customerId, String token) throws StoreModelException;

    /**
     * Method for adding Product to the Customer Basket
     *
     * @param basketId
     * @param productId
     * @param count
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Basket addBasketProduct(String basketId, String productId, int count, String token)
            throws StoreModelException;

    /**
     * Method for removing Product from Customer Basket
     *
     * @param basketId
     * @param productId
     * @param count
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Basket removeBasketProduct(String basketId, String productId, int count, String token) throws
            StoreModelException;

    /**
     * Method for removing all Products from the Customer Basket
     *
     * @param basketId
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Basket clearBasket(String basketId, String token) throws StoreModelException;

    /**
     * Method for displaying Customer Basket information
     *
     * @param basketId
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Basket showBasket(String basketId, String token) throws StoreModelException;

    /**
     * Method for provisioning Device in the Store
     *
     * @param deviceId
     * @param name
     * @param deviceType
     * @param storeId
     * @param aisleNumber
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Device provisionDevice(String deviceId, String name, String deviceType,
                                  String storeId, String aisleNumber, String token) throws StoreModelException;

    /**
     * Method for displaying Store Device information
     *
     * @param deviceId
     * @param token
     * @return
     * @throws StoreModelException
     */
    public Device showDevice(String deviceId, String token) throws StoreModelException;

    /**
     * Method for triggering Store Device event
     *
     * @param deviceId
     * @param event
     * @param token
     * @throws StoreModelException
     */
    public void raiseEvent(String deviceId, String event, String token) throws StoreModelException;

    /**
     * Method for issuing Store Appliance command
     *
     * @param deviceId
     * @param command
     * @param token
     * @throws StoreModelException
     */
    public void issueCommand(String deviceId, String command, String token) throws StoreModelException;
}
