import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;

/**
 * The atttributes of a task and its functionality
 * @author Duayne
 */
public abstract class Task {
    public static Comparator compareAssignee;
    private UUID id;
    protected String taskName;
    protected User assignee;
    protected int priority;
    protected String description;
    protected ArrayList<Comment> taskComments;


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
     * Adds a comment to the task
     * @author Duayne
     * @param user User object of the current user
     * @param message String of the message in the comment
     * @return boolean representing the comparison of the old and new task comment array list size
     */
    public boolean addComment(User user, String message) {
        if (user == null || message.equals(null))
            return false;
        int size = taskComments.size();
        Comment comment = new Comment(user, this, message);
        taskComments.add(comment);
        return size != taskComments.size();
    }

    /**
     * Gets the comment that is searched
     * @author Duayne
     * @param id UUID of the targeted comment
     * @return Comment that matches the UUID of the comment searched for
     */
    public Comment getComment(UUID id) {
        for ( int i = 0; i < taskComments.size() - 1; i++ ) {
            if ( taskComments.get(i).id == id )
                return taskComments.get(i);
        }
        return null;
    }

    /**
     * Gets all the comments for the task
     * @author Duayne
     * @return Array List of all this task's comments
     */
    public ArrayList<Comment> getComments() {
        if (!taskComments.isEmpty())
            return taskComments;
        return null;
    }

    /**
     * gets the assignee
     * @author theo 
     * @return assignee 
     */
    public User getAssignee(){
        return assignee;
    }

    /**
     * returns the priority int value 
     * @author theo 
     * @return returns the priority number 
     */
    public int getPriority(){
        return priority;
    }

    /**
     * gets the task name
     * @author theo 
     * @return task name
     */
    public String getTaskName(){
        return taskName;
    }

}
