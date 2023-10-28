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
     * Adds task to the columns list of tasks
     * @author Duayne
     * @param task Task object for the task to be added
     * @return boolean representing the comparison of the old and new tasks list size
     */
    public boolean addTask(Task task) {
        if (task == null)
            return false;
        int size = tasks.size();
        tasks.add(task);
        return size != tasks.size();
    }

    /**
     * 
     * @param sortType
     * @return
     */
    public boolean sortTasks(String sortType) {
   
        return false;
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
     * 
     * @param newColumnName
     */
    public static void setColumnName(String newColumnName){
        if(!newColumnName.isEmpty()){
            columnName = newColumnName;
        }
    }
}
