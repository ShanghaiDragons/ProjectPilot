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
        this.taskName = taskName;
    }
/**
 * 
 * @param assignee
 */
    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }
/**
 * 
 * @param priority
 */
    public void setPriority(int priority) {
        if (this.priority <= 5 && this.priority >= 1)
            this.priority = priority;
    }
/**
 * 
 * @param description
 */
    public void setDescription(String description) {
        this.description = description;
    }
/**
 * 
 * @param newTaskName
 * @return
 */
    public boolean editTaskName(String newTaskName) {
        String oldTaskName = this.taskName;
        setTaskName(newTaskName);
        return (oldTaskName != newTaskName);
    }
/**
 * 
 * @param newAssignee
 * @return
 */
    public boolean editAssignee(User newAssignee) {
        User oldAssignee = this.assignee;
        setAssignee(newAssignee);
        return (oldAssignee != newAssignee);
    }
/**
 * 
 * @param newPriority
 * @return
 */
    public boolean editPriority(int newPriority) {
        int oldPriority = this.priority;
        setPriority(newPriority);
        return (oldPriority != newPriority);
    }
/**
 * 
 * @param newDescription
 * @return
 */
    public boolean editDescription(String newDescription) {
        String oldDescription = this.description;
        setDescription(newDescription);
        return (oldDescription != newDescription);
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
