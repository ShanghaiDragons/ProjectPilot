import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


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
     * Edits the project attributes (name, Start date, end date) and saves it using dataWriter
     * @author theo v
     * @param projectID 
     * @param newName
     * @param newStartDate
     * @param newEndDate
     * @return boolean that determines whether the project was edited successfully
     */
    public boolean editProject(String projectID, String newName, LocalDate newStartDate, LocalDate newEndDate){
        Project editedProject = projectList.getProject(projectID);
        if(editedProject!=null){
            editedProject.setName(newName);
            editedProject.setSprint(newStartDate, newEndDate);
            return projectList.saveProjects();
        }
        return false;
    }

    /**
     * removes project and saves new project list using data writer
     * @author theo v
     * @param projectID
     * @return boolean that determines if the project was removed
     */
    public boolean removeProject(String projectID) {
        Project removedProject = projectList.getProject(projectID);
        if(removedProject!=null && projectList.getProjects().remove(removedProject)){
            return projectList.saveProjects();
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
     * removes column based on column ID 
     * @author theo v 
     * @param columnID
     * @return boolean that states whether it has successfully removed column 
     */
    public boolean removeColumn(String columnID) {
        UUID columnUUID = UUID.fromString(columnID);
        Column removedColumn = currentProject.getColumn(columnUUID);
        if(removedColumn!=null){
            return currentProject.removeColumn(removedColumn);
        }
        return false;
    }

    /**
     * Edits the specified column's attributes.
     * @author theo 
     * @param columnID ID of the column to be edited
     * @param newName   New name for the column
     * @param sortType  New sort type for the column
     * @return boolean that states whether the column has been successfully edited
     */
    public boolean editColumn(String columnID, String newName, String newsortType) {
        UUID columnUUID = UUID.fromString(columnID);
        Column editedColumn = currentProject.getColumn(columnUUID);
        if (editedColumn != null) {
            if (newName != null && !newName.isEmpty()) {
                editedColumn.setName(newName);
            }
            if (newsortType != null && !newsortType.isEmpty()) {
                editedColumn.setSortType(newsortType);
            }
            return true;
        }
        return false;
    }


    /**
     * moves tasks from the source column to the destination column using their respective IDs
     * @author theo v
     * @param taskID String that represents the UUID of the task that is being moved
     * @param sourcecolumnID String that represents the UUID of the column where the task resides
     * @param destinationcolumnID String that represents the UUID of the column where the task is going to be moved in 
     * @return whether or not moving the task was executed properly 
     */
    public boolean moveTask(String sourcecolumnID, String destinationcolumnID, String taskID) {
        UUID destinationcolumnUUID = UUID.fromString(destinationcolumnID);
        UUID taskUUID = UUID.fromString(taskID);
        UUID sourcecolumnUUID = UUID.fromString(sourcecolumnID);
        Column destinationColumn = currentProject.getColumn(destinationcolumnUUID);
        Column sourceColumn = currentProject.getColumn(sourcecolumnUUID);
        if(sourceColumn==null || destinationColumn==null){
            return false;
        }
        Task movedTask = sourceColumn.getTask(taskID);
        currentProject.moveTask(destinationColumn, movedTask);
        return true;
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
     * edits the specified task's attributes in the specified column
     * @author theo
     * @param taskID
     * @param newName
     * @param newAssignee
     * @param newPriority
     * @param newStatus
     * @param newDescription
     * @param comments
     * @return
     */  
    public boolean editTask(String columnID, String taskID, String newName, User newAssignee, int newPriority, String newStatus, String newDescription, ArrayList<Comment> comments) {
        UUID columnUUID = UUID.fromString(columnID);
        Column chosentaskColumn = currentProject.getColumn(columnUUID);
        Task editedTask = chosentaskColumn.getTask(taskID);
        if (editedTask != null && newName != null && !newName.isEmpty()) {
            editedTask.setName(newName);
            if (newAssignee != null) {
                editedTask.setAssignee(newAssignee);
            }
            editedTask.setPriority(newPriority);
            if (newStatus != null && !newStatus.isEmpty()) {
                editedTask.setStatus(newStatus);
            }
            if (newDescription != null) {
                editedTask.setDescription(newDescription);
            }
            if (comments != null) {
                editedTask.setComments(comments);
            }
            return true;
        }
        return false;
    }
    

    /**
     * sorts tasks based on the sort type (alphabetical, user, priority)
     * @author theo 
     * @param sortType
     * @return
     */
    public boolean sortTasks(String columnID, String sortType) {
        UUID columnUUID = UUID.fromString(columnID);
        Column sortedColumn = currentProject.getColumn(columnUUID);
        if (sortedColumn != null && sortedColumn.sortTasks(sortType)) {
            return true; 
        }
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

