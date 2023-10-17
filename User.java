import java.util.UUID;

public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private boolean permissionToAddTask;
    private boolean permissionToMoveTask;
    private boolean permissionToEditTask;
    private boolean permissionToEditColumns;
    private UUID id;
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
}
