import java.util.ArrayList;
import java.util.UUID;

/**
 * The atttributes of a task and its functionality
 * @author Duayne
 */
public abstract class Task extends ProductBacklog{
    private UUID id;
    protected String taskName;
    protected User assignee;
    protected int priority;
    protected String description;


/**
 * Constructor for the Task class without UUID
 * @author Duayne
 * @param taskName String of the task's name 
 * @param assignee User that is assigned to the task
 * @param priority Integer representing the task's priority
 * @param description String describing the task
 */
    public Task(String taskName, User assignee, int priority, String description) {
        this.taskName = taskName;
        this.assignee = assignee;
        this.priority = priority;
        this.description = description;
    }
/**
 * Sets the name of the task
 * @author Duayne
 * @param taskName String of the updated task name
 */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
/**
 * Sets the assignee to the task
 * @author Duayne
 * @param assignee User object that is to be assigned to the task
 */
    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }
/**
 * Sets the priority of the task
 * @author Duayne
 * @param priority Integer representation of the priority
 */
    public void setPriority(int priority) {
        if (this.priority <= 5 && this.priority >= 1)
            this.priority = priority;
    }
/**
 * Sets the description of the task
 * @author Duayne
 * @param description String representation of the task description
 */
    public void setDescription(String description) {
        this.description = description;
    }
/**
 * A boolean check to see if the task name was changed
 * @author Duayne
 * @param newTaskName String representation of the new task name
 * @return Returns the comparison of the new and old task name
 */
    public boolean editTaskName(String newTaskName) {
        String oldTaskName = this.taskName;
        setTaskName(newTaskName);
        return oldTaskName != newTaskName;
    }
/**
 * A boolean check to see if the task assignee was changed
 * @author Duayne
 * @param newAssignee User object to be assigned to the task
 * @return Returns the comparison of the new and old task assignee
 */
    public boolean editAssignee(User newAssignee) {
        User oldAssignee = this.assignee;
        setAssignee(newAssignee);
        return oldAssignee != newAssignee;
    }
/**
 * A boolean check to see if the task priority was changed
 * @author Duayne
 * @param newPriority Integer representation of the new priority
 * @return Returns the comparison of the new and old task priority
 */
    public boolean editPriority(int newPriority) {
        int oldPriority = this.priority;
        setPriority(newPriority);
        return oldPriority != newPriority;
    }
/**
 * A boolean check to see if the task description was changed
 * @author Duayne
 * @param newDescription String representation of the new description
 * @return Returns the comparison of the new and old task description
 */
    public boolean editDescription(String newDescription) {
        String oldDescription = this.description;
        setDescription(newDescription);
        return oldDescription != newDescription;
    }
/**
 * A boolean check to see if the task was archived
 * @author Duayne
 * @return Returns the comparison of the new and old archived task list
 */
    public boolean archiveTask() {
        ArrayList<Task> oldArchivedList = getArchivedTasks();
        addArchivedTask(this);
        return oldArchivedList.size() != getArchivedTasks().size();
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
