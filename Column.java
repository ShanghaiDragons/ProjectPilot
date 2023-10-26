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
   
        return false;
    }

    private void sortAssignee(){
<<<<<<< HEAD
        Collections.sort(tasks,Task.compareAssignee);
=======
        Collections.sort(tasks, Task.compareAssignee);

>>>>>>> theo_branch
    }
    /**
     * 
     */
    @Override
    public String toString() {
       
        return "Column{" +
                "columnName='" + columnName + '\'' +
                ", tasks=" + tasks +
                ", sortType='" + sortType + '\'' +
                '}';
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
