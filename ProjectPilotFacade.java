import java.util.ArrayList;

public class ProjectPilotFacade {
    private User user;
    private UserList userList;
    private ProjectList projectList;

    public ProjectPilotFacade() {
        userList = UserList.getInstance();
        projectList = ProjectList.getInstance();
    }
/**
 * Creates an account for a given user
 * @author Duayne
 * @param userName string that represents the user's username
 * @param firstName string that represents the user's first name
 * @param lastName string that represents the user's last name
 * @param password string that represents the user's password
 * @return Returns the user that was created with the given parameters
 */
    public User createAccount(String firstName, String lastName, String userName, String password) {
        if (this.user != null)
            return user;
        user = new User(firstName, lastName, userName, password, false,
                        false, false, false);
        return user;
    }
/**
 * 
 * @param userName
 * @param password
 * @return
 */
    public User login(String userName, String password) {
        return null;
    }
/**
 * 
 * @param user
 * @return
 */
    public boolean logout(User user) {
        return false;
    }
/**
 * 
 * @param userID
 * @param action
 * @return
 */
    public boolean manageAccount(String userID, String action) {
        return false;
    }
/**
 * 
 * @param projectID
 * @return
 */
    public boolean addProject(String projectID) {
        return false;
    }
/**
 * 
 * @param projectID
 * @return
 */
    public boolean editProject(String projectID) {
        return false;
    }
/**
 * 
 * @param projectID
 * @return
 */
    public boolean removeProject(String projectID) {
        return false;
    }
/**
 * 
 * @param columnID
 * @return
 */
    public boolean addColumn(String columnID) {
        return false;
    }
/**
 * 
 * @param columnID
 * @return
 */
    public boolean removeColumn(String columnID) {
        return false;
    }
/**
 * 
 * @param columnID
 * @return
 */
    public boolean editColumn(String columnID) {
        return false;
    }
/**
 * 
 * @param taskID
 * @return
 */
    public boolean moveTask(String taskID) {
        return false;
    }
/**
 * 
 * @param taskID
 * @return
 */
    public boolean addTask(String taskID) {
        return false;
    }
/**
 * 
 * @param taskID
 * @return
 */
    public boolean editTask(String taskID) {
        return false;
    }
/**
 * 
 * @param sortType
 * @return
 */
    public boolean sortTasks(String sortType) {
        return false;
    }
/**
 * 
 * @param user
 * @param projectID
 * @param comment
 * @return
 */
    public boolean addComment(User user, String projectID, String comment) {
        return false;
    }

    public ArrayList<User> getUsers() {
        return userList.getUsers();
    }

    public ArrayList<Task> getTasks() {
        return null;
    }

    /**
     * Gets the User
     * @author Chris
     * @return user
     */
    public User getUser() {
        return this.user;
    }
}
