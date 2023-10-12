import java.util.ArrayList;

public class ProductBacklog {
    private ArrayList<Task> tasks;
    private ArrayList<Task> archivedTasks;
    private Task task;

    public ProductBacklog() {
        tasks = new ArrayList<>();
        archivedTasks = new ArrayList<>();
    }

    public void activateTask(Task task) {
        // Implement logic to activate a task (move it from archivedTasks to tasks)
    }

    public void addTask(Task task) {
        // Implement logic to add a new task to the backlog
    }

    public void addArchivedTask(Task task) {
        // Implement logic to add an archived task to the backlog
    }

    @Override
    public String toString() {
        // Implement logic to convert the object to a string representation
        // Return a string containing product backlog information
        return "ProductBacklog{" +
                "tasks=" + tasks +
                ", archivedTasks=" + archivedTasks +
                ", task=" + task +
                '}';
    }
}
