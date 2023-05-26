package DataAccess;

import Model.orders;
import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
/**
 * This class represents the data access object for handling orders.
 */
public class OrdersDAO extends Abstract<orders>{
    /**
     * Deletes an order with the specified ID.
     *
     * @param id The ID of the order to be deleted.
     */
    public void deleteOrders(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM orders WHERE id = ?";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
