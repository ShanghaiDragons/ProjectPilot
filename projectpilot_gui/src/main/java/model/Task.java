package model;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;
import java.time.LocalDateTime;

/**
 * The atttributes of a task and its functionality
 * @author Duayne
 */
public class Task {
    public static Comparator compareAssignee;
    protected UUID id;
    protected String name;
    protected User assignee;
    protected int priority;
    protected String status;
    protected String description;
    protected ArrayList<Comment> comments;
    protected TaskHistory taskHistory;

    /**
     * Constructor for the Task class without UUID
     * @author Duayne (edited by ctaks)
     * @param name String of the task's name 
     * @param assignee User that is assigned to the task
     * @param priority Integer representing the task's priority
     * @param description String describing the task
     * @param comments new comment list
     */
    public Task(String name, User assignee, int priority, String status, String description, ArrayList<Comment> comments) {
        setTaskHistory(taskHistory);
        setID(this.id);
        setName(name);
        setAssignee(assignee);
        setPriority(priority);
        setStatus(status);
        setDescription(description);
        setComments(comments);
    }

    /**
     * Constructor for loading a task
     * @author ctaks
     * @param id from JSON file
     * @param name from JSON file
     * @param assignee from JSON file
     * @param priority from JSON file
     * @param status from JSON file
     * @param description from JSON file
     * @param taskHistory from JSON file
     * @param comments from JSON file
     */
    public Task(UUID id, String name, User assignee, int priority, String status, String description, TaskHistory taskHistory, ArrayList<Comment> comments) {
        setTaskHistory(taskHistory);
        setID(id);
        setName(name);
        setAssignee(assignee);
        setPriority(priority);
        setStatus(status);
        setDescription(description);
        setComments(comments);
        setTaskHistory(taskHistory);
    }

    /**
     * Setter for id
     * @author ctaks
     * @param id to be set
     * @return boolean determining success
     */
    public boolean setID(UUID id) {
        if (id != null) {
            this.id = id;
            taskHistory.setTaskID(this.id);
            return true;
        }
        else {
            this.id = UUID.randomUUID();
            taskHistory.setTaskID(this.id);
            return true;
        }
    }

    /**
     * Sets the name of the task
     * @author Duayne (edited by ctaks)
     * @param name String of the updated task name
     * @return boolean determining success
     */
    public boolean setName(String name) {
        if (name != null) {
            taskHistory.addNameChange(this.name, name);
            this.name = name;
            return true;
        } else {
            taskHistory.addNameChange(this.name, "default");
            this.name = "default";
            return false;
        }
    }

    /**
     * Sets the assignee to the task
     * @author Duayne (edited by ctaks)
     * @param assignee User object that is to be assigned to the task
     * @return boolean determining success
     */
    public boolean setAssignee(User assignee) {
        if (assignee != null) {
            if (this.assignee != null)
                taskHistory.addAssigneeChange(this.assignee.getUserName(), assignee.getUserName());
            else
                taskHistory.addAssigneeChange("empty", assignee.getUserName());
            this.assignee = assignee;
            return true;
        } else {
            taskHistory.addAssigneeChange("null", "null");
            this.assignee = null;
            return false;
        }
    }

    /**
     * Sets the priority of the task
     * @author Duayne (edited by ctaks)
     * @param priority Integer representation of the priority
     * @return boolean determining success
     */
    public boolean setPriority(int priority) {
        if (priority <= 5 && priority >= 1) {
            taskHistory.addPriorityChange(Integer.toString(this.priority), Integer.toString(priority));
            this.priority = priority;
            return true;
        } else {
            taskHistory.addPriorityChange(Integer.toString(this.priority), "default");
            this.priority = 5;
            return false;
        }
    }

    /**
     * setter for status
     * @author ctaks
     * @param status to be set
     * @return boolean determining success
     */
    public boolean setStatus(String status) {
        if (status != null) {
            taskHistory.addStatusChange(this.status, status);
            this.status = status;
            return true;
        } else {
            taskHistory.addStatusChange(this.status, "default");
            this.status = "default";
            return false;
        }
    }

    /**
     * Sets the description of the task
     * @author Duayne (edited by ctaks)
     * @param description String representation of the task description
     * @return boolean determining success
     */
    public boolean setDescription(String description) {
        if (description != null) {
            taskHistory.addDescriptionChange(this.description, description);
            this.description = description;
            return true;
        } else {
            taskHistory.addDescriptionChange(this.description, "default");
            this.description = "default";
            return false;
        }
    }

    public boolean setTaskHistory(TaskHistory taskHistory) {
        if (taskHistory != null) {
            this.taskHistory = taskHistory;
            return true;
        } else {
            ArrayList<String> setter = new ArrayList<String>();
            LocalDateTime creationDate = LocalDateTime.now();
            this.taskHistory = new TaskHistory(this.id, creationDate, setter, setter, setter, setter, setter, setter);
            return false;
        }
    }

     /**
     * Setter for comments
     * @author ctaks
     * @param comments to be set
     * @return boolean determing success
     */
    public boolean setComments(ArrayList<Comment> comments) {
        if (comments == null || comments.isEmpty()) {
            this.comments = new ArrayList<Comment>();
            return true;
        } else {
            this.comments = comments;
            return true;
        }
    }

    
    /**
     * Gets the comment that is searched
     * @author Duayne
     * @param id UUID of the targeted comment
     * @return Comment that matches the UUID of the comment searched for
     */
    public Comment getComment(UUID id) {
        for ( int i = 0; i < comments.size() - 1; i++ ) {
            if ( comments.get(i).getID() == id )
            return comments.get(i);
        }
        return null;
    }
    
    /**
     * Gets all the comments for the task
     * @author Duayne
     * @return Array List of all this task's comments
     */
    public ArrayList<Comment> getComments() {
        return this.comments;
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
    public String getName(){
        return name;
    }
    
    /**
     * Get's the task UUID
     * @author ctaks
     * @return UUID of the task's UUID
     */
    public UUID getID() {
        return this.id;
    }
    
    /**
     * Get's the task status
     * @author ctaks
     * @return UUID of the task's status
     */
    public String getStatus() {
        return this.status;
    }
    
    /**
     * Get's the task description
     * @author ctaks
     * @return String of the task description
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Get's the task's TaskHistory
     * @author ctaks
     * @return TaskHistory of the task history
     */
    public TaskHistory getTaskHistory() {
        return this.taskHistory;
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
        int size = comments.size();
        Comment comment = new Comment(user, message);
        comments.add(comment);
        return size != comments.size();
    }
}
