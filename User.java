import java.util.ArrayList;
import java.util.UUID;

public abstract class User {
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
        if(this.userName==username && verifyPassword(password)){
            return this;
        }
        return null;
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
 * 
 * @param task
 * @return
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
 * 
 * @param task
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
 * 
 * @param task
 * @return
 */


 //WHAT DO YOU EDIT IN COLUMNS? NAME? DELETE TASKS? COLOR?
    public boolean editColumns(Task task, String newColumnName) {
        if(!permissionToEditColumns){
        return false;
        }
        if(!newColumnName.isEmpty()){
            Column.setColumnName(newColumnName);
        }
        return true;
    }
/**
 * 
 * @param task
 * @return
 */
    public boolean moveTask(Task task) {
       if(!permissionToMoveTask){
        return false;
       }
       Column.moveTask(task);
       return true;
    }

    public UUID getID(){
        if(id!=null){
            return id;
        }
        return null;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }
    
    public String toString() {
        return "User: " + userName + ", First Name:" + firstName + " Last Name:" + lastName+" UUID: "+id;
    }
}
