import java.util.ArrayList;
import java.util.*;
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
       ArrayList<Task> temp = new ArrayList<Task>();
       for(int i=1; i< tasks.size(); i++){
        if(){

       }
    }

    public ArrayList<Task> sortAlphabetical(){
        tasks.sort(Comparator.naturalOrder());
    }

    public ArrayList<Task> sortPriority(){

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
