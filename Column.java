import java.util.ArrayList;
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
 * 
 * @param task
 * @return
 */

    
    public boolean addTask(Task task) {
        // Implement logic to add a task to the column with specified type
        // Return true if task added successfully, false otherwise
        return false; // Placeholder return value
    }
/**
 * 
 * @param sortType
 * @return
 */

//assignee, priority, alphabetical. Instead of just filtering, just sort. For example split the tasks into different priorities, alphabetical, user

// MAKE SEPARATE METHODS FOR EACH SORT TASKS IN HERE


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

    public ArrayList<Task> sortAssignee(){

    }

    public ArrayList<Task> sortAlphabetical(){

    }

    public ArrayList<Task> sortPriority(){

    }

    private 
    public String toString() {
       
        return "Column{" +
                "columnName='" + columnName + '\'' +
                ", tasks=" + tasks +
                ", sortType='" + sortType + '\'' +
                '}';
    }

    public static void setColumnName(String newColumnName){
        if(!newColumnName.isEmpty()){
            newColumnName=columnName;
        }
    }
}
