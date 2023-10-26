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
    * Adds a task to the product backlog if the user has permission to add tasks
    *@author theo 
    * @param task
     */
    public boolean addTask(Task task) {
        if(!permissionToAddTask){
        return false;
        }
        ArrayList<Task> tasks = ProductBacklog.getTasks(); 
        tasks.add(task);
        return true;
        
    }

    /**
    * Edits the task based on the parameters
    * @author theo v
    * @param task
    * @param newtaskName
    * @param newAssignee
    * @param newPriority 
    * @param newDescription 
    */
    public boolean editTask(Task task, String newtaskName, User newAssignee, int newPriority, String newDescription) {        // return the different edits in the task
        if(!permissionToEditTask){
            return false;
        }
        if(newtaskName!=null && !newtaskName.isEmpty()){
            task.setTaskName(newtaskName);
        }
        if(newAssignee!=null){
            task.setAssignee(newAssignee);
        }
        if(newPriority>0&&newPriority<4){
            task.setPriority(newPriority);
        }
        if(newDescription!=null){
            task.setDescription(newDescription);
        }
        return true;
    }

/**
 * Edits the column name 
 * @author theo 
 * @param newColumnName new column name
* @return
*/
//WHAT DO YOU EDIT IN COLUMNS? NAME? DELETE TASKS? COLOR? MAKE SURE TO ADD MORE
    public boolean editColumns(String newColumnName) {
        if(!permissionToEditColumns){
        return false;
        }
        if(!newColumnName.isEmpty()){
            Column.setColumnName(newColumnName);
        }
        return true;
    }
    
    /**
    * moves the task from one column to another
    * @author theo 
    * @param task task that is getting moved
    * @return
    */
    public boolean moveTask(Task task) {
       if(!permissionToMoveTask){
        return false;
       }
       Column.moveTask(task);
       return true;
    }
    
    public String toString() {
        return "User: " + userName + ", First Name:" + firstName + " Last Name:" + lastName+" UUID: "+id;
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
