public abstract class Task {
    private String taskName;
    private User assignee;
    private int priority;
    private String description;

    public Task(String taskName, User assignee, int priority, String description) {
        this.taskName = taskName;
        this.assignee = assignee;
        this.priority = priority;
        this.description = description;
    }

    public void setTaskName(String taskName) {
        // Implement logic to set the task name
    }

    public void setAssignee(User assignee) {
        // Implement logic to set the assignee of the task
    }

    public void setPriority(int priority) {
        // Implement logic to set the priority of the task
    }

    public void setDescription(String description) {
        // Implement logic to set the description of the task
    }

    public boolean editTaskName(String newTaskName) {
        // Implement logic to edit the task name
        // Return true if task name edited successfully, false otherwise
        return false; // Placeholder return value
    }

    public boolean editAssignee(User newAssignee) {
        // Implement logic to edit the assignee of the task
        // Return true if assignee edited successfully, false otherwise
        return false; // Placeholder return value
    }

    public boolean editPriority(int newPriority) {
        // Implement logic to edit the priority of the task
        // Return true if priority edited successfully, false otherwise
        return false; // Placeholder return value
    }

    public boolean editDescription(String newDescription) {
        // Implement logic to edit the description of the task
        // Return true if description edited successfully, false otherwise
        return false; // Placeholder return value
    }

    public boolean archiveTask(String taskName) {
        // Implement logic to archive the task with the specified name
        // Return true if task archived successfully, false otherwise
        return false; // Placeholder return value
    }

    @Override
    public String toString() {
        // Implement logic to convert the object to a string representation
        // Return a string containing task information
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", assignee=" + assignee +
                ", priority=" + priority +
                ", description='" + description + '\'' +
                '}';
    }
}
