package sourceCode;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The Project class. Holds data for the project and methods to manipulate it.
 */
public class Project {
    private UUID id;
    private String name;
    private LocalDate startSprint;
    private LocalDate endSprint;
    private ArrayList<User> team;
    private ArrayList<Column> columns;
    private ArrayList<Comment> comments;

    /**
     * Constructor for making a new project
     * @param name new
     * @param startSprint new
     * @param endSprint new
     * @param team new
     * @param columns new
     * @param comments new
     */
    public Project(String name, LocalDate startSprint, LocalDate endSprint, ArrayList<User> team, ArrayList<Column> columns, ArrayList<Comment> comments) {
        setID(id);
        setName(name);
        setStartSprint(startSprint);
        setEndSprint(endSprint);
        setTeam(team);
        setColumns(columns);
        setComments(comments);
    }

    /**
     * Constructor for loading a project from JSON
     * @param id from JSON file
     * @param name from JSON file
     * @param startSprint from JSON file
     * @param endSprint from JSON file
     * @param team from JSON file
     * @param columns from JSON file
     * @param comments from JSON file
     */    
    public Project(UUID id, String name, LocalDate startSprint, LocalDate endSprint, ArrayList<User> team, ArrayList<Column> columns, ArrayList<Comment> comments) {
        setID(id);
        setName(name);
        setStartSprint(startSprint);
        setEndSprint(endSprint);
        setTeam(team);
        setColumns(columns);
        setComments(comments);
    }

    /**
     * Setter for id
     * @author ctaks
     * @param id to be set
     * @return boolean determining success
     */
    public boolean setID(UUID id) {
        if (id != null) {
            this.id = id;
            return true;
        }
        else {
            this.id = UUID.randomUUID();
            return true;
        }
    }

    /**
     * Setter for name
     * @author ctaks
     * @param name to be set
     * @return boolean determing success
     */
    public boolean setName(String name) {
        if (name != null) {
            this.name = name;
            return true;
        } else {
            this.name = "default";
            return false;
        }
    }

    /**
     * setter for startSprint
     * @author ctaks
     * @param start to be set
     * @return boolean determing success
     */
    public boolean setStartSprint(LocalDate start) {
        if (start != null) {
            this.startSprint = start;
            return true;
        } else {
            this.startSprint = LocalDate.now();
            return false;
        }
    }

     /**
     * setter for endSprint
     * @author ctaks
     * @param start to be set
     * @return boolean determing success
     */
    public boolean setEndSprint(LocalDate end) {
        if (end != null) {
            this.endSprint = end;
            return true;
        } else {
            this.endSprint = LocalDate.now();
            return false;
        }
    }

    /**
     * Setter for team
     * @author ctaks
     * @param team to be set
     * @return boolean determing success
     */
    public boolean setTeam(ArrayList<User> team) {
        if (team == null || team.isEmpty()) {
            this.team = new ArrayList<User>();
            return true;
        } else {
            this.team = team;
            return true;
        }
    }

    /**
     * setter for column
     * @author ctaks
     * @param column to be set
     * @return boolean determing success
     */
    public boolean setColumns(ArrayList<Column> columns) {
        if (columns == null || columns.isEmpty()) {
            this.columns = new ArrayList<Column>();
            return true;
        } else {
            this.columns = columns;
            return true;
        }
    }

    /**
     * Setter for comments
     * @author ctaks
     * @param comments to be set
     * @return boolean determing success
     */
    public boolean setComments(ArrayList<Comment> comments) {
        if (comments == null || comments.isEmpty()) {
            this.comments = new ArrayList<Comment>();
            return true;
        } else {
            this.comments = comments;
            return true;
        }
    }
    /**
     * Sets both the sprint start and end dates
     * @author Duayne
     * @param startSprint LocalDate object of the sprint's start date
     * @param endSprint LocalDate object of the sprint's end date
     */
    public void setSprint(LocalDate startSprint, LocalDate endSprint) {
        if (startSprint == null || endSprint == null)
        return;
        if (startSprint.compareTo(endSprint) > 0) {
            LocalDate temp = startSprint;
            startSprint = endSprint;
            endSprint = temp;
        }
        this.startSprint = startSprint;
        this.endSprint = endSprint;
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
     * Gets the sprint start date
     * @author Duayne
     * @return LocalDate object of the sprint start date
     */
    public LocalDate getStartSprint() {
        if (this.startSprint != null)
        return startSprint;
        return null;
    }
    
    /**
     * Gets the sprint end date
     * @author Duayne
     * @return LocalDate object of the sprint end date
     */
    public LocalDate getEndSprint() {
        if (this.endSprint != null)
        return endSprint;
        return null;
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
     * Gets a specified comment
     * @author ctaks
     * @param commentID The specified comment's ID
     * @return Comment of the comment
     */
    public Comment getComment(UUID commentID) {
        for (Comment comment : this.comments) {
            if (comment.getID() == commentID)
            return comment;
        }
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
    /**
     * gets column based on columnID 
     * @author theo v 
     * @param columnID
     * @return the column that has the column ID 
     */
    public Column getColumn(UUID columnID){
        for (Column column : columns){
            if(column.getID()==columnID)
            return column;
        }
        return null;
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
     * Adds user to the team and assigns permissions
     * @author Duayne
     * @param user User object that represents the current user
     * @param type UserType enumeration that categorizes the user based on permissions
     * @return boolean that represents a change after adding user to the team
     */
    public boolean addTeamMember(User user, UserType type) {
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
     * Moves a task from one column to another column
     * @author ctaks
     * @param sourceColumn task origin
     * @param destinationColumn task destination
     * @param task to be moved
     * @return boolean determining success of both move and deletion of task from source column
     */
    public boolean moveTask(Column sourceColumn, Column destinationColumn, Task task) {
        if (sourceColumn.getID().equals(destinationColumn.getID())) {
            return true; // If the source/destination columns are the same, the task is already "moved".
        }
        if (getColumn(destinationColumn.getID()).addTask(getColumn(sourceColumn.getID()).getTask(task.getID())))
            return getColumn(sourceColumn.getID()).getTasks().remove(task);
        else
            return false;
    }
    
    /**
     * Adds a comment to the project
     * @author ctaks
     * @param user to be added
     * @param message to be added
     * @return boolean determining success
     */
    public boolean addComment(User user, String message) {
        if(user != null && !message.isEmpty())
            return comments.add(new Comment(user, message));
        else
            return false;
    }
}
