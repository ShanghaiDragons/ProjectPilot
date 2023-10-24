import java.util.ArrayList;

public class ProjectPilotFacade {
    private static ProjectPilotFacade projectPilotFacade;
    private User user;
    private UserList userList;
    private ProjectList projectList;

    /**
     * ProjectPilotFacade constructor. Initializes userList, projectList, and user.
     * @author ctaks
     */
    public ProjectPilotFacade() {
        userList = UserList.getInstance();
        projectList = ProjectList.getInstance();
    }

    /**
     * Creates an instance (singleton) of ProjectPilotFacade if there isn't one
     * @author ctaks
     * @return an instance of ProjectPilotFacade
     */
    public static ProjectPilotFacade getInstance() {
        if (projectPilotFacade == null)
            projectPilotFacade = new ProjectPilotFacade();
        return projectPilotFacade;
    }

/**
 * 
 * @param firstName
 * @param lastName
 * @param userName
 * @param password
 * @return
 */
    public boolean createAccount(String firstName, String lastName, String userName, String password) {
        // User newUser = new User(firstName, lastName, userName, password)
        return true;
    }
/**
 * Logs the user in
 * @author ctaks
 * @param userName String of the username
 * @param password String of the password
 * @return boolean determining if the login was successful
 */
    public boolean login(String userName, String password) {
        if (userList.getUser(userName) != null) {
            user = userList.getUser(userName);
            return user.login(userName, password);
        } else {
            return false;
        }
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
