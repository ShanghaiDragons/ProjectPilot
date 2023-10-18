import java.util.UUID;

public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String userName;
    private boolean permissionToAddTask;
    private boolean permissionToMoveTask;
    private boolean permissionToEditTask;
    private boolean permissionToEditColumns;

    /**
     * 
     * @author Duayne
     * @param id
     * @param userName
     * @param firstName
     * @param lastName
     */
    public User(UUID id, String userName, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.id = id;
    }
/**
 * 
 * @param firstName
 * @param lastName
 * @param userName
 * @param permissionToAddTask
 * @param permissionToMoveTask
 * @param permissionToEditTask
 * @param permissionToEditColumns
 */
    public User(String firstName, String lastName, String userName, boolean permissionToAddTask,
                boolean permissionToMoveTask, boolean permissionToEditTask, boolean permissionToEditColumns) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.permissionToAddTask = permissionToAddTask;
        this.permissionToMoveTask = permissionToMoveTask;
        this.permissionToEditTask = permissionToEditTask;
        this.permissionToEditColumns = permissionToEditColumns;
    }
/**
 * 
 * @param username
 * @param password
 * @return
 */
    public User login(String username, String password) {
        return null;
    }
/**
 * 
 * @param password
 * @return
 */
    public boolean verifyPassword(String password) {
        return false;
    }
/**
 * 
 * @param task
 * @return
 */
    public boolean addTask(Task task) {
        return false;
    }
/**
 * 
 * @param task
 */
    public void editTask(Task task) {
    }
/**
 * 
 * @param task
 * @return
 */
    public boolean editColumns(Task task) {
        return false;
    }
/**
 * 
 * @param task
 * @return
 */
    public boolean moveTask(Task task) {
        return false;
    }

    public String getUserName() {
        return userName;
    }
    @Override
    public String toString() {
        return "User: " + userName + ", " + firstName + " " + lastName;
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
}
