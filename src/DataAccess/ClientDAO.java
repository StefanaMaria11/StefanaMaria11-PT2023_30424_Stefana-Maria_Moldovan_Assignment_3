package DataAccess;

import Connection.ConnectionFactory;
import Model.client;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * This class represents a data access object for the client table in the database.
 * It provides methods to retrieve client information from the database.
 */
public class ClientDAO extends Abstract<client>{
    /**
     * Retrieves the customer ID for a given customer name.
     *
     * @param customerName the name of the customer
     * @return the customer ID if found, 0 otherwise
     */
    public int getCustomerIdByName(String customerName) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "SELECT id FROM client WHERE name = ?";
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

