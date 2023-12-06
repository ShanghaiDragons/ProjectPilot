package model;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * ProjectPilot Facade class. Acts as a buffer between the UI and everything else.
 */
public class ProjectPilotFacade {
    private static ProjectPilotFacade projectPilotFacade;
    private User currentUser;
    private UserType userRole;
    private Project currentProject;
    private Column currentColumn;
    private Task currentTask;
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
        return this.currentUser;
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
     * Sets the current project.
     * @author ctaks
     * @param project to be set
     * @return boolean determining success
     */
    public boolean setCurrentProject(Project project) {
        if (project != null) {
            this.currentProject = project;
            return true;
        }
        return false;
    }

    /**
     * Gets the current Column.
     * @author ctaks
     * @return Column of the current column
     */
    public Column getCurrentColumn() {
        return this.currentColumn;
    }

    /**
     * Sets the current column
     * @author ctaks
     * @param col to be set
     * @return boolean determining success
     */
    public boolean setCurrentColumn(Column col) {
        if (col != null) {
            this.currentColumn = col;
            return true;
        }
        return false;
    }

    /**
     * Gets the current Task
     * @author ctaks
     * @return Task of the current task
     */
    public Task getCurrentTask() {
        return this.currentTask;
    }

    /**
     * sets the current task
     * @param task to be set
     * @return boolean determining success
     */
    public boolean setCurrentTask(Task task) {
        if (task != null) {
            this.currentTask = task;
            return true;
        }
        return false;
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
     * Gets a project based on UUID
     * @author ctaks
     * @param projectID the project's UUID
     * @return the project
     */
    public Project getProject(UUID projectID) {
        for (Project project : projectList.getProjects()) {
            if (project.getID().equals(projectID))
                return project;
        }
        return null;
    }

    /**
     * Gets a project based on name
     * @author ctaks
     * @param projectName the project's name
     * @return the project
     */
    public Project getProject(String projectName) {
        return projectList.getProject(projectName);
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
     * Creates an account for a given user
     * @author Duayne (edit by ctaks - changed return type from User to boolean, and changed literally everything else)
     * @param userName string that represents the user's username
     * @param firstName string that represents the user's first name
     * @param lastName string that represents the user's last name
     * @param password string that represents the user's password
     * @return Returns the user that was created with the given parameters
     */
    public boolean createAccount(String firstName, String lastName, String userName, String password) {
        return userList.addUser(new User(firstName, lastName, userName, password));
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
            currentUser = userList.getUser(userName);
            //loadRole();
            return currentUser.login(userName, password);
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
        if(currentUser != null){
            this.currentUser=null;
            return true;
        }
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
    public boolean addProject(String name, LocalDate startSprint, LocalDate endSprint, ArrayList<User> team, User scrumMaster, ArrayList<User> collaborators, ArrayList<User> viewers, ArrayList<Column> columns, ArrayList<Comment> comments) {
        return projectList.addProject(new Project(name, startSprint, endSprint,team, scrumMaster, collaborators, viewers, columns, comments));
    }

    /**
     * Checks if the current user can edit the project attributes
     * @param edit the attribute to be edited. Pass in "project[variable]" i.e. projectName, or projectStartPrint
     * @return boolean determining if the user has permission
     */
    public boolean canEditProject(String edit) {
        return userRole.getPermission(edit);
    }

    /**
     * removes project and saves new project list using data writer
     * @author theo v
     * @param projectName
     * @return boolean that determines if the project was removed
     */
    public boolean removeProject(String projectName) {
        Project removedProject = projectList.getProject(projectName);
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
     * @param arrayList to be added
     * @param arrayList2 to be added
     * @return boolean determining success
     */
    public boolean addColumn(String name, String sortType, ArrayList<Task> arrayList, ArrayList<Comment> arrayList2) {
        if(currentProject != null){
        return currentProject.addColumn(new Column(name, sortType, arrayList, arrayList2));
        }
        return false;
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
     * Moves the task from one column to the next
     * @author ctaks
     * @param sourceColumn task origin
     * @param destinationColumn task destination
     * @param task to be moved
     * @return boolean determining success
     */
    public boolean moveTask(Column sourceColumn, Column destinationColumn, Task task) {
        return this.currentProject.moveTask(sourceColumn, destinationColumn, task);
    }

    /**
     * Adds a task to the current project's selected column
     * @author ctaks edited by Duayne
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
                return column.addTask(new Task(name, user, priority, status, description, comments));
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
     * Removes the task from the current project
     * @author Duayne
     * @param taskID String of the task UUID to be removed
     * @return boolean representing the task not existing
     */
    public boolean removeTask(String taskID) {
        UUID taskUUID = UUID.fromString(taskID);
        Task task = null;
        for (int i = 0; i < currentProject.getColumns().size(); i++)
            for (int j = 0; j < currentProject.getColumns().get(i).getTasks().size(); j++)
                if (currentProject.getColumns().get(i).getTasks().get(j).getID().equals(taskUUID)) {
                    task = currentProject.getColumns().get(i).getTasks().get(j);
                    currentProject.getColumns().get(i).removeTask(task);
                }
        for (int i = 0; i < currentProject.getColumns().size(); i++)
            for (int j = 0; j < currentProject.getColumns().get(i).getTasks().size(); j++)
                if (!currentProject.getColumns().get(i).getTasks().contains(task))
                    return true;
        return false;
    }

    /**
     * Removes a user from the project
     * @author Duayne
     * @param userID String of the user's UUID to remove from the team
     * @return boolean of the removed user not contained in the project's team
     */
    public boolean removeUser(String userID) {
        User person = null;
        for(User u : getUsers()) {
            if(u.getID().equals(UUID.fromString(userID)))
                person = u;
        }
        currentProject.removeUser(person);
        return !currentProject.getTeam().contains(person);
    }

    /**
     * Adds a comment to the project
     * @author ctaks
     * @param project to add comment to
     * @param message of the comment
     * @return boolean determining success
     */
    public boolean addComment(String message) {
        return currentProject.addComment(this.currentUser, message);
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
                return c.addComment(this.currentUser, message);
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
                    return t.addComment(this.currentUser, message);
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
            return currentProject.getComment(comment.getID()).threadComment(this.currentUser, message);
        for (Column col : currentProject.getColumns()) {
            if(col.getComment(comment.getID()) != null)
                return col.getComment(comment.getID()).threadComment(this.currentUser, message);
            for (Task t : col.getTasks()) {
                if(t.getComment(comment.getID()) != null)
                    return t.getComment(comment.getID()).threadComment(this.currentUser, message);
            }
        }
        return false;
    }

    /**
     * Setter for currentProject. ALso sets the currentRole based on the currentUser's role in the project.
     * @author ctaks
     * @param projectID the project's UUID
     * @return boolean determining if it set the project or not.
     */
    public boolean loadProject(UUID projectID) {
        for (Project project : projectList.getProjects()) {
            if (project.getID().equals(projectID)) {
                currentProject = project;
                loadRole();
                return true;
            }
        }
        return false;
    }

    /**
     * Setter for userRole. Called through currentProject (the project needs to be set first)
     * @author ctaks
     * @return boolean determining success
     */
    public boolean loadRole() {
        // Scrum Master
        if (currentUser.getID().equals(currentProject.getScrumMaster().getID())) {
            userRole = UserType.SCRUM_MASTER;
            return true;
        }
        // Collaborator
        for (User collab : currentProject.getCollaborators()) {
            if (currentUser.getID().equals(collab.getID())) {
                userRole = UserType.COLLABORATOR;
                return true;
            }
        }
        // Viewer
        for (User viewer : currentProject.getViewers()) {
            if (currentUser.getID().equals(viewer.getID())) {
                userRole = UserType.VIEWER;
                return true;
            }
        }
        // No role found
        userRole = UserType.VIEWER;
        return false;
    }

    /**
     * Saves the current projects
     * @author ctaks
     * @return boolean determing if the save was successful.
     */
    public boolean saveProjects() {
        return projectList.saveProjects();
    }

    /**
     * Saves the current user list
     * @author ctaks
     * @return boolean determing if the save was successful.
     */
    public boolean saveUsers() {
        return userList.saveUsers();
    }
}
