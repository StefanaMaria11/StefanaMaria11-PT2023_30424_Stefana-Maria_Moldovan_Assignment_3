package Model;
/**
 * The orders class represents an order in the system.
 */
public class orders {
    private int id;
    private int customer_id;
    private int item_id;
    private int quantity;
    private String total_price;
    /**
     * Constructs an empty order object.
     */

    public void orders(){

    }
    /**
     * Returns the ID of the order.
     *
     * @return the order ID
     */

    public int isId() {
        return id;
    }
    /**
     * Sets the ID of the order.
     *
     * @param id the order ID to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Sets the customer ID associated with the order.
     *
     * @param customer_id the customer ID to set
     */
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    /**
     * Sets the item ID associated with the order.
     *
     * @param item_id the item ID to set
     */

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }
    /**
     * Returns the quantity of the ordered item.
     *
     * @return the quantity of the item
     */
    public int isQuantity() {
        return quantity;
    }
    /**
     * Sets the quantity of the ordered item.
     *
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * Returns the total price of the order.
     *
     * @return the total price of the order
     */
    public String isTotal_price() {
        return total_price;
    }
    /**
     * Sets the total price of the order.
     *
     * @param total_price the total price to set
     */
    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }
    /**
     * Returns the customer ID associated with the order.
     *
     * @return the customer ID of the order
     */
    public int isCustomer_id() {
        return customer_id;
    }
    /**
     * Returns the item ID associated with the order.
     *
     * @return the item ID of the order
     */
    public int isItem_id() {
        return item_id;
    }
    /**
     * Returns a string representation of the order.
     *
     * @return a string representation of the order
     */
    @Override
    public String toString() {
        return "Orders[" +
                "Order_ID=" + id +
                ", Customer_ID=" + customer_id +
                ", Item_ID=" + item_id +
                ", Quantity=" + quantity +
                ", Total_price='" + total_price +
                ']';
    }
}
