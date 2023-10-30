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
        userList.addUser(user);
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
     * logs out the user 
     * @author theo v 
     * @param user
     * @return
     */
    public boolean logout() {
        if(user != null){
            this.user=null;
            return true;
        }
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
     * @param name to be added
     * @param startSprint to be added
     * @param endSprint to be added
     * @param team to be added
     * @param columns to be added
     * @param comments to be added
     * @return boolean determining success
     */
    public boolean addProject(String name, LocalDate startSprint, LocalDate endSprint, ArrayList<User> team, ArrayList<Column> columns, ArrayList<Comment> comments) {
        return projectList.addProject(new Project(name, startSprint, endSprint,team, columns, comments));
    }

    /**
     * 
     * @param projectID
     * @param newName
     * @param newStartDate
     * @param newEndDate
     * @return
     */
    public boolean editProject(String projectID, String newName, LocalDate newStartDate, LocalDate newEndDate){
        Project editedProject = projectList.getProject(projectID);
        if(editedProject!=null){
            // editedProject.setName //EDITED THIS OUT TO SOLVE ERRORS
        }
        return false;
    }

    /**
     * removes project 
     * @author theo v
     * @param projectID
     * @return
     */
    public boolean removeProject(String projectID) {
        Project removedProject = projectList.getProject(projectID);
        if(removedProject!=null){
            return projectList.getProjects().remove(removedProject);
        }
        return false;
    }

    /**
     * Adds a column to the current project
     * @author ctaks
     * @param name to be added
     * @param sortType to be added
     * @param tasks to be added
     * @param comments to be added
     * @return boolean determining success
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
     * Adds a task to the current project's selected column
     * @author ctaks
     * @param c Column that the task needs to be added in
     * @param name to be added
     * @param user to be added
     * @param priority to be added
     * @param status to be added
     * @param description to be added
     * @param comments to be added
     * @return boolean determining success
     */
    public boolean addTask(Column c, String name, User user, int priority, String status, String description, ArrayList<Comment> comments) {
        for (Column column : currentProject.getColumns())
            if (column.getID() == c.getID())
                return column.addTask(new GeneralTask(name, user, priority, status, description, comments));
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
     * Adds a comment to the project
     * @author ctaks
     * @param project to add comment to
     * @param message of the comment
     * @return boolean determining success
     */
    public boolean addComment(String message) {
        return currentProject.addComment(this.user, message);
    }

    /**
     * Adds a comment to the column
     * @author ctaks
     * @param column to add comment to
     * @param message of the comment
     * @return boolean determining success
     */
    public boolean addComment(Column column, String message) {
         for (Column c : currentProject.getColumns())
            if (c.getID() == column.getID())
                return c.addComment(this.user, message);
        return false;
    }

    /**
     * Adds a comment to the task
     * @author ctaks
     * @param task to add comment to
     * @param message of the comment
     * @return boolean determining success
     */
    public boolean addComment(Task task, String message) {
        for (Column c : currentProject.getColumns())
            for (Task t : c.getTasks())
                if (t.getID() == task.getID())
                    return t.addComment(this.user, message);
        return false;
    }

    /**
     * Adds a comment to a comment (threads it)
     * @author ctaks
     * @param comment to add comment to
     * @param message of the comment
     * @return boolean determining success
     */
    public boolean addComment(Comment comment, String message) {
        if (currentProject.getComment(comment.getID()) != null )
            return currentProject.getComment(comment.getID()).threadComment(this.user, message);
        for (Column col : currentProject.getColumns()) {
            if(col.getComment(comment.getID()) != null)
                return col.getComment(comment.getID()).threadComment(this.user, message);
            for (Task t : col.getTasks()) {
                if(t.getComment(comment.getID()) != null)
                    return t.getComment(comment.getID()).threadComment(this.user, message);
            }
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

    /**
     * Gets all of the tasks in the current project
     * @author Duayne
     * @return Array list of all tasks in the project
     */
    public ArrayList<Task> getTasks() {
        Project p = getCurrentProject();
        ArrayList<Task> allTasks = new ArrayList<Task>();
        for (int i = 0; i < p.getColumns().size(); i++)
            for (int j = 0; j < p.getColumns().get(i).getTasks().size(); j++)
                allTasks.add(p.getColumns().get(i).getTasks().get(j));
        return allTasks;
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

