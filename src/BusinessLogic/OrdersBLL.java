package BusinessLogic;

import DataAccess.ClientDAO;
import DataAccess.OrdersDAO;
import Model.client;
import Model.orders;
import Model.product;

import java.util.List;
/**
 * This class represents the business logic layer for orders.
 */
public class OrdersBLL {
    private OrdersDAO ordersDAO;
    /**
     * Constructs an instance of OrdersBLL and initializes the OrdersDAO.
     */
    public OrdersBLL(){
        ordersDAO = new OrdersDAO();
    }
    /**
     * Inserts an order into the data access layer.
     *
     * @param orders The order to be inserted.
     */
    public void insertOrders(orders orders){
        ordersDAO.insert(orders);
    }
    /**
     * Finds an order by name from the data access layer.
     *
     * @param name The name of the order to find.
     * @return The found order, or null if not found.
     */
    public orders findOrderByName(String name) {
        return ordersDAO.findByName(name);
    }
    /**
     * Finds an order by ID from the data access layer.
     *
     * @param id The ID of the order to find.
     * @return The found order, or null if not found.
     */
    public orders findOrderById(int id){
        return ordersDAO.findById(id);
    }
    /**
     * Retrieves all orders from the data access layer.
     *
     * @return A list of all orders.
     */
    public List<orders> getAllOrders() {
        return ordersDAO.findAll();
    }
    /**
     * Updates an order in the data access layer by name.
     *
     * @param orders The updated order.
     */
    public void updateOrdersByName(orders orders) {
        ordersDAO.updateId(orders, orders.isId());
    }
    /**
     * Deletes an order by ID from the data access layer.
     *
     * @param id The ID of the order to delete.
     */
    public void deleteOrderByName(int id){
        ordersDAO.deleteOrders(id);
    }
    /**
     * Retrieves and displays all orders from the data access layer.
     */
    public void viewAllOrders(){
        ordersDAO.findAll();
    }
}
