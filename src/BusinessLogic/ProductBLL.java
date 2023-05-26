package BusinessLogic;


import DataAccess.ProductDAO;
import Model.client;
import Model.product;

import java.util.List;
/**
 * This class represents the business logic for managing products.
 */
public class ProductBLL {
    private ProductDAO productDAO;
    /**
     * Constructs a new instance of the ProductBLL class.
     */
    public ProductBLL(){
        productDAO=new ProductDAO();
    }
    /**
     * Inserts a product into the database.
     *
     * @param product The product to be inserted.
     */
    public void insertProduct(product product){
        productDAO.insert(product);
    }
    /**
     * Finds a product by its name.
     *
     * @param name The name of the product to find.
     * @return The found product, or null if not found.
     */
    public product findProductByName(String name) {
        return productDAO.findByName(name);
    }
    /**
     * Retrieves all products from the database.
     *
     * @return A list of all products.
     */
    public List<product> getAllProducts() {
        return productDAO.findAll();
    }
    /**
     * Updates a product in the database by its name.
     *
     * @param product The updated product.
     */
    public void updateProductByName(product product) {
        productDAO.update(product, product.isId());
    }
    /**
     * Deletes a product from the database by its name.
     *
     * @param product The product to be deleted.
     */
    public void deleteProductByName(product product){
        productDAO.delete(product);
    }
    /**
     * Retrieves and displays all products.
     */
    public void viewAllProducts(){
        productDAO.findAll();
    }
}
