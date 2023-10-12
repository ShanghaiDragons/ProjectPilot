import java.util.ArrayList;

public class Column {
    private String columnName;
    private ArrayList<Task> tasks;
    private String sortType;

    public Column(String columnName, String sortType) {
        this.columnName = columnName;
        this.sortType = sortType;
        this.tasks = new ArrayList<>();
    }

    public boolean moveTask(Task task) {
        // Implement logic to move the task within the column
        // Return true if task moved successfully, false otherwise
        return false; // Placeholder return value
    }

    public boolean addTask(Task task) {
        // Implement logic to add a task to the column with specified type
        // Return true if task added successfully, false otherwise
        return false; // Placeholder return value
    }

    public boolean sortTasks(String sortType) {
        // Implement logic to sort tasks within the column based on sortType
        // Return true if tasks sorted successfully, false otherwise
        return false; // Placeholder return value
    }

    @Override
    public String toString() {
        // Implement logic to convert the object to a string representation
        // Return a string containing column information
        return "Column{" +
                "columnName='" + columnName + '\'' +
                ", tasks=" + tasks +
                ", sortType='" + sortType + '\'' +
                '}';
    }
}
