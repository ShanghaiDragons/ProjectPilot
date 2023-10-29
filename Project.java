import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * The Project class. Holds data for the project and methods to manipulate it.
 */
public class Project {
    private UUID id;
    private String name;
    private Date startSprint;
    private Date endSprint;
    private ArrayList<User> team;
    private ArrayList<Column> columns;
    private ArrayList<Comment> comments;

    /**
     * 
     * @param id
     * @param name
     * @param user
     */
    public Project(UUID id, String name, User user) {
        this.id = id;
        this.name = name;
        this.team = new ArrayList<>();
        this.columns = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    /**
     * 
     * @param name
     * @param user
     */
    public Project(String name, User user) {
        this.name = name;
        this.team = new ArrayList<>();
        this.columns = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    /**
     * Adds user to the team and assigns permissions
     * @author Duayne
     * @param user User object that represents the current user
     * @param type UserType enumeration that categorizes the user based on permissions
     * @return boolean that represents a change after adding user to the team
     */
    public boolean addUser(User user, UserType type) {
        int size = team.size();
        if (type == UserType.SCRUM_MASTER) {
            user.setPermissionToAddTask(true);
            user.setPermissionToEditColumns(true);
            user.setPermissionToEditTask(true);
            user.setPermissionToMoveTask(true);
            team.add(user);
            return size != team.size();
        } else if (type == UserType.COLLABORATOR) {
            user.setPermissionToAddTask(false);
            user.setPermissionToEditColumns(false);
            user.setPermissionToEditTask(true);
            user.setPermissionToMoveTask(true);
            team.add(user);
            return size != team.size();
        } else if (type == UserType.VIEWER) {
            user.setPermissionToAddTask(false);
            user.setPermissionToEditColumns(false);
            user.setPermissionToEditTask(false);
            user.setPermissionToMoveTask(false);
            team.add(user);
            return size != team.size();
        }
        return false;
    }

    /**
     * Removes the given user from the project team
     * @author Duayne
     * @param user User object of the given user
     * @return boolean that represents a change after removing user from the team 
     */
    public boolean removeUser(User user) {
        if (!team.contains(user))
            return false;
        int size = team.size();
        team.remove(user);
        return size != team.size();
    }

    /**
     * Adds a column to the project
     * @author Duayne
     * @param column Column object of the column to be added to the project
     * @return boolean that represents a change after adding column to the list of columns
     */
    public boolean addColumn(Column column) {
        if (column == null)
            return false;
        int size = columns.size();
        columns.add(column);
        return size != columns.size();
    }

    /**
     * Removes a column from the project
     * @author Duayne
     * @param column Column object of the column to be removed from the project
     * @return boolean that represents a change after removing column from the list of columns
     */
    public boolean removeColumn(Column column) {
        if (!columns.contains(column))
            return false;
        int size = columns.size() - 1;
        columns.remove(column);
        return size == columns.size();
    }

    /**
     * Moves task from one column to another column
     * @author Duayne
     * @param newDestination Column object of the new column the task will be moved into
     * @param task Task object of the task to be moved
     * @return boolean that represents a change after removing from one column and adding to the other
     */
    public boolean moveTask(Column newDestination, Task task) {
        Column oldDestination = null;
        for (int i = 0; i < columns.size() - 1; i++)
            for (int j = 0; j < columns.get(i).getTasks().size() - 1; j++)
                if (task == columns.get(i).getTask(task.taskName))
                    oldDestination = columns.get(i);
        int newColumnSize = newDestination.getTasks().size();
        for (int i = 0; i < columns.size() - 1; i++)
            if (newDestination == columns.get(i))
                columns.get(i).addTask(task);
        if (oldDestination == null)
            return false;
        int oldColumnSize = oldDestination.getTasks().size();
        oldDestination.removeTask(task);
        return (oldColumnSize != oldDestination.getTasks().size() &&
                newColumnSize != newDestination.getTasks().size());
    }

    /**
     * 
     * @param user
     * @param project
     * @param comment
     * @return
     */
    private boolean addComment(User user, Project project, String comment) {
        
        return false;
    }

    /**
     * 
     * @param user
     * @param project
     * @param comment
     * @return
     */
    private boolean threadComment(User user, Project project, String comment) {
        return false;
    }

    /**
     * Gets the name of the project
     * @author Chris
     * @return the project name (this.name)
     */
    public String getName() {
        if (this.name != null)
            return this.name;
        else
            return "no name found";
    }

    /**
     * Gets the id of the project
     * @author Chris
     * @return UUID of the project ID
     */
    public UUID getID() {
        return this.id;
    }

    /**
     * Gets the current columns
     * @author ctaks
     * @return ArrayList<Column> of current columns
     */
    public ArrayList<Column> getColumns() {
        return this.columns;
    }

    /**
     * Gets the sprint start date
     * @author Duayne
     * @return Date object of the sprint start date
     */
    public Date getStartSprint() {
        if (this.startSprint != null)
            return startSprint;
        return null;
    }

    /**
     * Gets the sprint end date
     * @author Duayne
     * @return Date object of the sprint end date
     */
    public Date getEndSprint() {
        if (this.endSprint != null)
            return endSprint;
        return null;
    }

    /**
     * Sets both the sprint start and end dates
     * @author Duayne
     * @param startSprint Date object of the sprint's start date
     * @param endSprint Date object of the sprint's end date
     */
    public void setSprint(Date startSprint, Date endSprint) {
        if (startSprint == null || endSprint == null)
            return;
        if (startSprint.compareTo(endSprint) > 0) {
            Date temp = startSprint;
            startSprint = endSprint;
            endSprint = temp;
        }
        this.startSprint = startSprint;
        this.endSprint = endSprint;
    }

    /**
     * Gets the project's team
     * @author Duayne
     * @return Array List of users in the team
     */
    public ArrayList<User> getTeam() {
        if (this.team != null)
            return team;
        return null;
    }

    /**
     * Gets the project's comments
     * @author Duayne
     * @return Array List of comments in the comment list
     */
    public ArrayList<Comment> getComments() {
        if (this.comments != null)
            return comments;
        return null;
    }
}
