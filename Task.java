public abstract class Task {
    private String taskName;
    private User assignee;
    private int priority;
    private String description;
/**
 * 
 * @param taskName
 * @param assignee
 * @param priority
 * @param description
 */
    public Task(String taskName, User assignee, int priority, String description) {
        this.taskName = taskName;
        this.assignee = assignee;
        this.priority = priority;
        this.description = description;
    }
/**
 * 
 * @param taskName
 */
    public void setTaskName(String taskName) {
    }
/**
 * 
 * @param assignee
 */
    public void setAssignee(User assignee) {
    }
/**
 * 
 * @param priority
 */
    public void setPriority(int priority) {
    }
/**
 * 
 * @param description
 */
    public void setDescription(String description) {
    }
/**
 * 
 * @param newTaskName
 * @return
 */
    public boolean editTaskName(String newTaskName) {
        return false;
    }
/**
 * 
 * @param newAssignee
 * @return
 */
    public boolean editAssignee(User newAssignee) {
        return false;
    }
/**
 * 
 * @param newPriority
 * @return
 */
    public boolean editPriority(int newPriority) {
        return false;
    }
/**
 * 
 * @param newDescription
 * @return
 */
    public boolean editDescription(String newDescription) {
        return false;
    }
/**
 * 
 * @param taskName
 * @return
 */
    public boolean archiveTask(String taskName) {
        return false;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", assignee=" + assignee +
                ", priority=" + priority +
                ", description='" + description + '\'' +
                '}';
    }
}
