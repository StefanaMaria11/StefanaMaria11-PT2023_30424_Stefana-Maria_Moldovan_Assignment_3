package Model;
/**
 * Represents a client in the system.
 */
public class client {
    private int id;
    private  String name;
    private  String email;
    private  String phone_number;
    private  String address;
    /**
     * Constructs a new client object.
     */
    public client(){

    }
    /**
     * Returns the ID of the client.
     *
     * @return the ID of the client
     */
    public int isId() {
        return id;
    }
    /**
     * Sets the ID of the client.
     *
     * @param id the ID of the client
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Returns the name of the client.
     *
     * @return the name of the client
     */
    public  String isName() {
        return name;
    }
    /**
     * Sets the name of the client.
     *
     * @param name the name of the client
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns the email of the client.
     *
     * @return the email of the client
     */
    public  String isEmail() {
        return email;
    }
    /**
     * Sets the email of the client.
     *
     * @param email the email of the client
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Returns the phone number of the client.
     *
     * @return the phone number of the client
     */
    public  String isPhone_number() {
        return phone_number;
    }
    /**
     * Sets the phone number of the client.
     *
     * @param phone_number the phone number of the client
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    /**
     * Returns the address of the client.
     *
     * @return the address of the client
     */
    public  String isAddress() {
        return address;
    }
    /**
     * Sets the address of the client.
     *
     * @param address the address of the client
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Returns a string representation of the client.
     *
     * @return a string representation of the client
     */
    public String toString(){
        return "Client [Customer_ID=" + id + ", Customer_name=" + name + ", Email=" + email + ", Phone_Number=" + phone_number + ", Address=" + address + "]";
    }
}
