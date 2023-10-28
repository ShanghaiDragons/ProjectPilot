import java.util.ArrayList;
import java.util.UUID;

/**
 * 
 */
public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private boolean permissionToAddTask;
    private boolean permissionToMoveTask;
    private boolean permissionToEditTask;
    private boolean permissionToEditColumns;
    private UUID id;

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
        this.id = UUID.randomUUID();
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
     * Gets the current permission to add a task
     * @author Duayne
     * @return boolean of the permission to add a task
     */
    public boolean isPermissionToAddTask() {
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
    public boolean isPermissionToMoveTask() {
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
    public boolean isPermissionToEditTask() {
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
    public boolean isPermissionToEditColumns() {
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
