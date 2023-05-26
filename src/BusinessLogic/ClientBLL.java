package BusinessLogic;

import DataAccess.ClientDAO;
import Model.client;

import java.util.List;
import java.util.Map;
/**
 * Represents the business logic layer for managing clients.
 */
public class ClientBLL {
    private ClientDAO clientDAO;
    /**
     * Constructs a new instance of the ClientBLL class.
     */
    public ClientBLL(){
        clientDAO=new ClientDAO();
    }
    /**
     * Inserts a client into the data source.
     *
     * @param client The client object to be inserted.
     */
    public void insertClient(client client){
         clientDAO.insert(client);
    }
    /**
     * Finds a client by their name.
     *
     * @param name The name of the client to be found.
     * @return The client object if found, or null otherwise.
     */
    public client findClientByName(String name) {
        return clientDAO.findByName(name);
    }
    /**
     * Retrieves a list of all clients.
     *
     * @return A list of all clients.
     */
    public List<client> getAllClients() {
        return clientDAO.findAll();
    }
    /**
     * Updates a client's information by their name.
     *
     * @param client The client object with updated information.
     */
    public void updateClientByName(client client) {
        clientDAO.update(client, client.isId());
    }
    /**
     * Deletes a client by their name.
     *
     * @param client The client object to be deleted.
     */
    public void deleteClientByName(client client){
        clientDAO.delete(client);
    }
    /**
     * Retrieves and displays all clients.
     */
    public void viewAllClients(){
        clientDAO.findAll();
    }
}
