package model;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The User class. Holds all the data for a user
 */
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    /**
     * Overloaded constructor of user to include UUID
     * @author Duayne (edited by ctaks (using the setters))
     * @param id UUID that represents the id
     * @param userName string that represents the user's username
     * @param firstName string that represents the user's first name
     * @param lastName string that represents the user's last name
     * @param password string that represents the user's password
     */
    public User(UUID id, String userName, String firstName, String lastName, String password) {
        setID(id);
        setFirstName(firstName);
        setLastName(lastName);
        setUserName(userName);
        setPassword(password);
    }

    /**
    * Constructor for making a new user
    * @author ctaks
    * @param userName string that represents the user's username
    * @param firstName string that represents the user's first name
    * @param lastName string that represents the user's last name
    * @param password string that represents the user's password
    */
    public User(String firstName, String lastName, String userName, String password) {
        setID(this.id);
        setFirstName(firstName);
        setLastName(lastName);
        setUserName(userName);
        setPassword(password);
    }
    
    /**
     * Setter for id
     * @author ctaks
     * @param id to be set
     * @return boolean determining success
     */
    public boolean setID(UUID id) {
        if (id != null) {
            this.id = id;
            return true;
        }
        else {
            this.id = UUID.randomUUID();
            return true;
        }
    }
    
    /**
     * setter for firstName
     * @author ctaks
     * @param firstName to be set
     * @return boolean determining success
     */
    public boolean setFirstName(String firstName) {
        if (firstName != null) {
            this.firstName = firstName;
            return true;
        } else {
            this.firstName = "none";
            return false;
        }
    }
    
    /**
     * setter for lastName
     * @author ctaks
     * @param lastName to be set
     * @return boolean determining success
     */
    public boolean setLastName(String lastName) {
        if (lastName != null) {
            this.lastName = lastName;
            return true;
        } else {
            this.lastName = "none";
            return false;
        }
    }
    
    /**
     * setter for username
     * @author ctaks
     * @param userName to be set
     * @return boolean determining success
     */
    public boolean setUserName(String userName) {
        if (userName != null) {
            this.userName = userName;
            return true;
        } else {
            this.userName = "none";
            return false;
        }
    }
    
    /**
     * setter for password
     * @author ctaks
     * @param password to be set
     * @return boolean determining success
     */
    public boolean setPassword(String password) {
        if (password != null) {
            this.password = password;
            return true;
        } else {
            this.password = "none";
            return false;
        }
    }

    /**
     * Gets the UUID of the user
     * @author Chris
     * @return the UUID. Type: UUID
     */
    public UUID getID() {
        return this.id;
    }

    /**
     * Gets the first name of the user
     * @author Chris
     * @return String of the first name
     */
    public String getFirstName() {
        return this.firstName;
    }
    
    /**
     * Gets the last name of the user
     * @author Chris
     * @return String of the last name
     */
    public String getLastName() {
        return this.lastName;
    }
    
    /**
     * Gets the username of the user
     * @author Chris
     * @return String of the username
     */
    public String getUserName() {
        return this.userName;
    }
    
    /**
     * Gets the password of the user
     * @author ctaks
     * @return String of the password
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * verifies whether the user has entered the username and password correctly
     * @author theo v
     * @param username
     * @param password
     * @return boolean that states whether the user has successfully logged in or not
     */
    public boolean login(String username, String password) {
        if(username==null || password==null){
            return false;
        }
        return this.userName.equals(username) && verifyPassword(password);
    }
    
    /**
    * verifies whether or not the password entered is correct
    *@author theo v 
    * @param password
    * @return boolean that states whether String input matches the password
    */
    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
}
