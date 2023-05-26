package DataAccess;

import Model.product;
import Connection.ConnectionFactory;
import java.sql.*;
/**
 * The ProductDAO class provides data access methods for the Product model.
 */
public class ProductDAO extends Abstract<product>{
    /**
     * Retrieves the product ID by name.
     *
     * @param customerName the name of the product
     * @return the product ID corresponding to the given name
     */
    public int getProductIdByName(String customerName) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "SELECT id FROM product WHERE name = ?";
        int customerId = 0;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, customerName);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                customerId = resultSet.getInt("id");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerId;
    }

}
