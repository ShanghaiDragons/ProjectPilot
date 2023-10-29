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
    private boolean permissionToAddTask;
    private boolean permissionToMoveTask;
    private boolean permissionToEditTask;
    private boolean permissionToEditColumns;

    /**
     * Overloaded constructor of user to include UUID
     * @author Duayne
     * @param id UUID that represents the id
     * @param userName string that represents the user's username
     * @param firstName string that represents the user's first name
     * @param lastName string that represents the user's last name
     * @param password string that represents the user's password
     * @param permissionToAddTask boolean that represents the user's permission to add tasks
     * @param permissionToMoveTask boolean that represents the user's permission to move tasks
     * @param permissionToEditTask boolean that represents the user's permission to edit tasks
     * @param permissionToEditColumns boolean that represents the user's permission to edit columns
     */
    public User(UUID id, String userName, String firstName, String lastName, String password, boolean permissionToAddTask,
                boolean permissionToMoveTask, boolean permissionToEditTask, boolean permissionToEditColumns) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.permissionToAddTask = permissionToAddTask;
        this.permissionToMoveTask = permissionToMoveTask;
        this.permissionToEditTask = permissionToEditTask;
        this.permissionToEditColumns = permissionToEditColumns;
    }

    /**
    * 
    * @param userName string that represents the user's username
    * @param firstName string that represents the user's first name
    * @param lastName string that represents the user's last name
    * @param password string that represents the user's password
    * @param permissionToAddTask boolean that represents the user's permission to add tasks
    * @param permissionToMoveTask boolean that represents the user's permission to move tasks
    * @param permissionToEditTask boolean that represents the user's permission to edit tasks
    * @param permissionToEditColumns boolean that represents the user's permission to edit columns
    */
    public User(String firstName, String lastName, String userName, String password, boolean permissionToAddTask,
                boolean permissionToMoveTask, boolean permissionToEditTask, boolean permissionToEditColumns) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.permissionToAddTask = permissionToAddTask;
        this.permissionToMoveTask = permissionToMoveTask;
        this.permissionToEditTask = permissionToEditTask;
        this.permissionToEditColumns = permissionToEditColumns;
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
    * verifies whether the user has entered the username and password correctly
    * @author theo v
    * @param username
    * @param password
    * @return 
    */
    public boolean login(String username, String password) {
        if(username==null || password==null){
            return false;
        }
        return this.userName.equals(username) && verifyPassword(password);
    }

    /**
    * 
    * @param password
    * @return
    */
    public boolean verifyPassword(String password) {
        return this.password.equals(password);
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
     * Gets the current permission to add a task
     * @author Duayne
     * @return boolean of the permission to add a task
     */
    public boolean getPermissionToAddTask() {
        return permissionToAddTask;
    }

    /**
     * Sets the permission to add a task
     * @author Duayne
     * @param permissionToEditColumns boolean to which to set the permission
     */
    public void setPermissionToAddTask(boolean permissionToAddTask) {
        this.permissionToAddTask = permissionToAddTask;
    }

    /**
     * Gets the current permission to move a task
     * @author Duayne
     * @return boolean of the permission to move a task
     */
    public boolean getPermissionToMoveTask() {
        return permissionToMoveTask;
    }

    /**
     * Sets the permission to move a task
     * @author Duayne
     * @param permissionToEditColumns boolean to which to set the permission
     */
    public void setPermissionToMoveTask(boolean permissionToMoveTask) {
        this.permissionToMoveTask = permissionToMoveTask;
    }

    /**
     * Gets the current permission to edit a task
     * @author Duayne
     * @return boolean of the permission to edit a task
     */
    public boolean getPermissionToEditTask() {
        return permissionToEditTask;
    }

    /**
     * Sets the permission to edit a task
     * @author Duayne
     * @param permissionToEditColumns boolean to which to set the permission
     */
    public void setPermissionToEditTask(boolean permissionToEditTask) {
        this.permissionToEditTask = permissionToEditTask;
    }

    /**
     * Gets the current permission to edit a column
     * @author Duayne
     * @return boolean of the permission to edit a column
     */
    public boolean getPermissionToEditColumns() {
        return permissionToEditColumns;
    }

    /**
     * Sets the permission to edit a column
     * @author Duayne
     * @param permissionToEditColumns boolean to which to set the permission
     */
    public void setPermissionToEditColumns(boolean permissionToEditColumns) {
        this.permissionToEditColumns = permissionToEditColumns;
    }
}
