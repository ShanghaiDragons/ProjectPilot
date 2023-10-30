import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * ProjectPilot Facade class. Acts as a buffer between the UI and everything else.
 */
public class ProjectPilotFacade {
    private static ProjectPilotFacade projectPilotFacade;
    private User user;
    private Project currentProject;
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
        // Uncomment code below when the addUser method is added and implemented to the UserList class
        // userList.addUser();
        return user;
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
     * Adds a new project.
     * @author ctaks
     * @param projectID
     * @return boolean determining success
     */
    public boolean addProject(String name, LocalDate startSprint, LocalDate endSprint, ArrayList<User> team, ArrayList<Column> columns, ArrayList<Comment> comments) {
        return projectList.addProject(new Project(name, startSprint, endSprint,team, columns, comments));
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
    public boolean addColumn(String name, String sortType, ArrayList<Task> tasks, ArrayList<Comment> comments) {
        return currentProject.addColumn(new Column(name, sortType, tasks, comments));
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
    public <T> boolean addComment(T scrumject, User user, String message) {
        // PROJECT
        if (scrumject instanceof Project) {
            
            return true;
        }
        
        // COLUMN
        if (scrumject instanceof Column) {

            return true;
        }

        // TASK
        if (scrumject instanceof Task) {

            return true;
        }

        // COMMENT
        if (scrumject instanceof Comment) {

            return true;
        }
        return false;
    }

    /**
     * Gets the list of users in the databse
     * @author ctaks
     * @return ArrayList<User> of the users in the database
     */
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

    /**
     * Gets the Current Projects
     * @author ctaks
     * @return ArrayList<Project> of the current projects
     */
    public ArrayList<Project> getProjects() {
        return projectList.getProjects();
    }

    /**
     * Gets a project
     * @author ctaks
     * @param projectName the project's name
     * @return the project
     */
    public Project getProject(String projectName) {
        for (Project project : projectList.getProjects()) {
            if (project.getName().equalsIgnoreCase(projectName))
                return project;
        }
        return null;
    }

    /**
     * Setter for currentProject.
     * @author ctaks
     * @param projectName the project's name
     * @return boolean determining if it set the project or not.
     */
    public boolean loadProject(String projectName) {
        for (Project project : projectList.getProjects()) {
            if (project.getName().equalsIgnoreCase(projectName)) {
                currentProject = project;
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the current Project.
     * @author ctaks
     * @return Project of the current project
     */
    public Project getCurrentProject() {
        return this.currentProject;
    }

    /**
     * Saves the current projects
     * @author ctaks
     * @return boolean determing if the save was successful.
     */
    public boolean saveProjects() {
        return projectList.saveProjects();
    }
}
