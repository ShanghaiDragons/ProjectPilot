import java.util.ArrayList;

public class ProductBacklog {
    private ArrayList<Task> tasks;
    private ArrayList<Task> archivedTasks;
    private Task task;

    public ProductBacklog() {
        tasks = new ArrayList<>();
        archivedTasks = new ArrayList<>();
    }
/**
 * 
 * @param task
 */
    public void activateTask(Task task) {
    }

    public void addTask(Task task) {
    }
/**
 * 
 * @param task
 */
    public void addArchivedTask(Task task) {
    }

    public String toString() {
        return "ProductBacklog{" +
                "tasks=" + tasks +
                ", archivedTasks=" + archivedTasks +
                ", task=" + task +
                '}';
    }
}

