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
     *adds task to the tasks array 
     * @author theo v 
     * @param task
     * @return
     */
    public boolean addTask(Task task) {
        return tasks.add(task);
    }

    /**
     * 
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
     * sorts the tasks by priority number
     * @author theo 
     * @return sorted tasks by priority
     */
    public ArrayList<Task> sortPriority(){
    tasks.sort(Comparator.comparingInt(Task::getPriority));
    return tasks;
    }

    /**
     * 
     * @param newColumnName
     */
    public static void setColumnName(String newColumnName){
        if(!newColumnName.isEmpty()){
            newColumnName=columnName;
        }
    }
}
