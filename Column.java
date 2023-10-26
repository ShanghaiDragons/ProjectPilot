import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * This class represents a generic column in the scrum board 
 */
public class Column {
    private static String columnName;
    private ArrayList<Task> tasks;
    private String sortType;

    /**
     * 
     * @param columnName
     * @param sortType
     */
    public Column(String columnName, String sortType) {
        this.columnName = columnName;
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
        String oldName = columnName;
        setColumnName(newColumnName);
        return !oldName.equals(columnName);
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
        tasks.sort(Comparator.comparing(Task::getTaskName));
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
    public static void setColumnName(String newColumnName){
        if(!newColumnName.isEmpty()){
            columnName = newColumnName;
        }
    }
}
