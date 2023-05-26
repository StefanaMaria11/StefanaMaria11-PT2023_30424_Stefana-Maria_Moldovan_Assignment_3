package Model;
/**
 * The `product` class represents a product in the inventory.
 */
public class product {
    private int id;
    private String name;
    private String description;
    private String price;
    private int year_of_manufacture;
    private int quantity;
    /**
     * Default constructor for the `product` class.
     */
    public product(){

    }
    /**
     * Returns the ID of the product.
     *
     * @return The ID of the product.
     */
    public int isId() {
        return id;
    }
    /**
     * Sets the ID of the product.
     *
     * @param id The ID of the product.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Returns the name of the product.
     *
     * @return The name of the product.
     */
    public String isItem_name() {
        return name;
    }
    /**
     * Sets the name of the product.
     *
     * @param name The name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }
    public  String isName() {
        return name;
    }
    /**
     * Returns the description of the product.
     *
     * @return The description of the product.
     */
    public String isDescription() {
        return description;
    }
    /**
     * Sets the description of the product.
     *
     * @param description The description of the product.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Returns the price of the product.
     *
     * @return The price of the product.
     */
    public String isPrice() {
        return price;
    }
    /**
     * Sets the price of the product.
     *
     * @param price The price of the product.
     */
    public void setPrice(String price) {
        this.price = price;
    }
    /**
     * Returns the year of manufacture of the product.
     *
     * @return The year of manufacture of the product.
     */
    public int isYear_of_manufacture() {
        return year_of_manufacture;
    }
    /**
     * Sets the year of manufacture of the product.
     *
     * @param year_of_manufacture The year of manufacture of the product.
     */
    public void setYear_of_manufacture(int year_of_manufacture) {
        this.year_of_manufacture = year_of_manufacture;
    }
    /**
     * Returns the quantity of the product.
     *
     * @return The quantity of the product.
     */
    public int isQuantity(){ return quantity;}
    /**
     * Sets the quantity of the product.
     *
     * @param quantity The quantity of the product.
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    /**
     * Returns a string representation of the product.
     *
     * @return A string representation of the product.
     */
    @Override
    public String toString() {
        return "Product [" +
                "Item_ID=" + id +
                ", Item_name=" + name +
                ", Description=" + description +
                ", Price=" + price +
                ", Year_of_manufacture=" + year_of_manufacture +
                ", Quantity=" + quantity +
                ']';
    }
}
