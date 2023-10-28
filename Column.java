import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

/**
 * This class represents a generic column in the scrum board 
 */
public class Column {
    private UUID id;
    private String name;
    private ArrayList<Task> tasks;
    private String sortType;
    private ArrayList<Comment> comments;

    /**
     * 
     * @param name
     * @param sortType
     */
    public Column(String name, String sortType) {
        this.name = name;
        this.sortType = sortType;
        this.tasks = new ArrayList<>();
    }

    /**
     * 
     * @param task
     * @return
     */
    public static boolean moveTask(Task task) {
        // Implement logic to move the task within the column
        // Return true if task moved successfully, false otherwise
        return false; // Placeholder return value
    }

    /**
     * Edits the column's name
     * @author Duayne
     * @param newColumnName String representation of the new column name
     * @return boolean representing the comparison of the old and new column name
     */
    public boolean editColumnName(String newColumnName) {
        if (newColumnName.equals(null))
            return false;
        String oldName = name;
        setColumnName(newColumnName);
        return !oldName.equals(name);
    }

    /**
     * 
     * @param task
     * @return
     */
    public boolean addTask(Task task) {
        return tasks.add(task);
    }

    /**
     * Sorts arraylist task based on what the sort type is (assignee,priority,aphabetical)
     * @param sortType
     * @return
     */
    public boolean sortTasks(String sortType) {
       switch(sortType){
            case "assignee":
            sortAssignee();
            break;
            case "priority":
            sortPriority();
            break;
            case "alphabetical":
            sortAlphabetical();
            break;
            default:
            return false;
       }
       return true;
    }

    /**
     * Gets a specified task
     * @author ctaks
     * @param taskName the name of the task
     * @return Task of the task, if it matches.
     */
    public Task getTask(String taskName) {
        for (Task task : this.tasks) {
            if (task.getName().equalsIgnoreCase(taskName))
                return task;
        }
        return null;
    }

    /**
     * Gets the column's tasks
     * @author ctaks
     * @return ArrayList<Task> of the column's tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * sorts the tasks by assignee
     * @author theo v
     * @return sorted tasks by assignee
     */
    private void sortAssignee(){
        Collections.sort(tasks, Task.compareAssignee);

    }
    /**
     * alphabetically sorts the tasks array by the task name
     * @author theo
     * @return
     */
    public ArrayList<Task> sortAlphabetical(){
        tasks.sort(Comparator.comparing(Task::getName));
        return tasks;
    }
    /**
     * sorts the tasks by priority number and returns that sorted arraylist
     * @author theo 
     * @return sorted tasks by priority
     */
    public ArrayList<Task> sortPriority(){
    tasks.sort(Comparator.comparingInt(Task::getPriority));
    return tasks;
    }

    /**
     * sets column name 
     * @param newColumnName
     */
    public void setColumnName(String newColumnName){
        if(!newColumnName.isEmpty()){
            this.name = newColumnName;
        }
    }

    /**
     * Gets the column name
     * @author ctaks
     * @return String of the column's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the column's UUID
     * @author ctaks
     * @return UUID of the column's UUID
     */
    public UUID getID() {
        return this.id;
    }

    /**
     * Get's the column's sort type
     * @author ctaks
     * @return String of the column's sort type
     */
    public String getSortType() {
        return this.sortType;
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
     * Get's the column's comments
     * @author ctaks
     * @return ArrayList<Comment> of the column's comments
     */
    public ArrayList<Comment> getComments() {
        return this.comments;
    }
}
