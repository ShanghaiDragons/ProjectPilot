import java.util.ArrayList;

public class ProductBacklog {
    private static ArrayList<Task> tasks;
    private static ArrayList<Task> archivedTasks;
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

    public static ArrayList<Task> getTasks(){
        return tasks;
    }

    public static ArrayList<Task> getArchivedTasks(){
        return archivedTasks;
    }
}

