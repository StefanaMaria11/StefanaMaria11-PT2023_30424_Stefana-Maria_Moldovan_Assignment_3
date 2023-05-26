package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * A factory class for creating database connections.
 */
public class ConnectionFactory {
    protected static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String DBURL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "Naruhina99";

    private static ConnectionFactory singleInstance = new ConnectionFactory();
    /**
     * Constructs a new instance of ConnectionFactory.
     * It registers the JDBC driver.
     */
    public ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Creates a new database connection.
     *
     * @return the newly created Connection object.
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
           // System.out.println("Connection successful!");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }
    /**
     * Gets a database connection.
     *
     * @return the database Connection object.
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

}

