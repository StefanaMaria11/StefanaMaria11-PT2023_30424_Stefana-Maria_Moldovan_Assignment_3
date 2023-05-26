import Connection.ConnectionFactory;
import DataAccess.ClientDAO;
import PresentationLayer.View;

import java.util.logging.Logger;
/**
 * The main class for your application.
 */
public class Main {
    /**
     * The logger instance for the ConnectionFactory class.
     */
    protected static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    /**
     * The main method of the application.
     * @param args The command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        View v = new View();
        //ClientView clientView = new ClientView();
    }
}
