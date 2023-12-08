package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

import model.Task;
import model.User;

/**
 * This class represents a generic column in the scrum board 
 */
public class Column {
    private UUID id;
    private String name;
    private String sortType;
    private ArrayList<Task> tasks;
    private ArrayList<Comment> comments;
    
    /**
     * Makes a new, fresh column. Meaning a new list of tasks and comments are created.
     * @author ctaks
     * @param name new name
     * @param sortType new sortType
     * @param tasks new tasks
     * @param comments new comments
     */
    public Column(String name, String sortType, ArrayList<Task> tasks, ArrayList<Comment> comments) {
        setID(id);
        setName(name);
        setSortType(sortType);
        setTasks(tasks);
        setComments(comments);
    }
    
    /**
     * For loading a column. Needs an ID.
     * @param id id from JSON file
     * @param name name from JSON file
     * @param sortType sortType from JSON file
     * @param tasks tasks from JSON file
     * @param comments comments from JSON file
     */
    public Column(UUID id, String name, String sortType, ArrayList<Task> tasks, ArrayList<Comment> comments) {
        setID(id);
        setName(name);
        setSortType(sortType);
        setTasks(tasks);
        setComments(comments);
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
            return true;
        }
        else {
            this.id = UUID.randomUUID();
            return true;
        }
    }
    
    /**
     * Setter for name
     * @author ctaks
     * @param name to be set
     * @return boolean determing success
     */
    public boolean setName(String name) {
        if (name != null) {
            this.name = name;
            return true;
        } else {
            this.name = "default";
            return false;
        }
    }
    
    /**
     * Setter for tasks
     * @author ctaks
     * @param tasks to be set
     * @return boolean determing success
     */
    public boolean setTasks(ArrayList<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            this.tasks = new ArrayList<Task>();
            return true;
        } else {
            this.tasks = tasks;
            return true;
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
     * setter for sortType
     * @author ctaks
     * @param sortType to be set
     * @return boolean determining success
     */
    public boolean setSortType(String sortType) {
        if (sortType != null) {
            this.sortType = sortType;
            return true;
        } else {
            this.sortType = "default";
            return false;
        }
    }
    
    /**
     * sets column name 
     * @param newColumnName
     */
    public void setColumnName(String newColumnName){
        if(!newColumnName.isEmpty()){
            this.name = newColumnName;
        }
    }
    
    /**
     * Gets the column's tasks
     * @author ctaks
     * @return ArrayList<Task> of the column's tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    
    /**
     * Gets the column name
     * @author ctaks
     * @return String of the column's name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Gets the column's UUID
     * @author ctaks
     * @return UUID of the column's UUID
     */
    public UUID getID() {
        return this.id;
    }
    
    /**
     * Get's the column's sort type
     * @author ctaks
     * @return String of the column's sort type
     */
    public String getSortType() {
        return this.sortType;
    }
    
    /**
     * Gets a specified comment
     * @author ctaks
     * @param commentID The specified comment's ID
     * @return Comment of the comment
     */
    public Comment getComment(UUID commentID) {
        for (Comment comment : this.comments) {
            if (comment.getID() == commentID)
            return comment;
        }
        return null;
    }
    
    /**
     * Get's the column's comments
     * @author ctaks
     * @return ArrayList<Comment> of the column's comments
     */
    public ArrayList<Comment> getComments() {
        return this.comments;
    }
    
    /**
     * Gets a specified task
     * @author ctaks
     * @param taskName the name of the task
     * @return Task of the task, if it matches.
     */
    public Task getTask(String taskName) {
        for (Task task : this.tasks) {
            if (task.getName().equalsIgnoreCase(taskName))
            return task;
        }
        return null;
    }
    
    /**
     * Gets a specified task by UUID search
     * @author ctaks
     * @param taskID the UUID of the task
     * @return Task of the task if it finds one.
     */
    public Task getTask(UUID taskID) {
        for (Task task : this.tasks)
            if (task.getID() == taskID)
                return task;
        return null;
    }
    
    /**
     * Edits the column's name
     * @author Duayne
     * @param newColumnName String representation of the new column name
     * @return boolean representing the comparison of the old and new column name
     */
    public boolean editColumnName(String newColumnName) {
        if (newColumnName.equals(null))
        return false;
        String oldName = name;
        setColumnName(newColumnName);
        return !oldName.equals(name);
    }
    
    /**
     * Adds task to the columns list of tasks
     * @author Duayne
     * @param task Task object for the task to be added
     * @return boolean representing the comparison of the old and new tasks list size
     */
    public boolean addTask(Task task) {
        if (task == null)
        return false;
        int size = tasks.size();
        tasks.add(task);
        return size != tasks.size();
    }
    
    /**
     * Removes a task from the task list in the column
     * @author Duayne
     * @param task Task object of task to be removed
     * @return boolean representing the comparison of the old and new tasks list size
     */
    public boolean removeTask(Task task) {
        if (!tasks.contains(task))
            return false;
        int size = tasks.size();
        tasks.remove(task);
        return size != tasks.size();
    }
    
    /**
     * Sorts arraylist task based on what the sort type is (assignee,priority,aphabetical)
     * @author theo 
     * @param sortType
     * @return
     */
    public boolean sortTasks(String sortType) {
       switch(sortType){
            case "assignee":
            sortAssignee();
            break;
            case "priority":
            sortPriority();
            break;
            case "alphabetical":
            sortAlphabetical();
            break;
            default:
            return false;
       }
       return true;
    }


    /**
     * sorts the tasks by assignee
     * @author theo v
     * @return assignee sorted tasks array list
     */
    private void sortAssignee(){
        Collections.sort(tasks, Task.compareAssignee);
        
    }
    /**
     * alphabetically sorts the tasks array by the task name
     * @author theo
     * @return alphabetically sorted tasks array list
     */
    public ArrayList<Task> sortAlphabetical(){
        tasks.sort(Comparator.comparing(Task::getName));
        return tasks;
    }
    /**
     * sorts the tasks by priority number and returns that sorted arraylist
     * @author theo 
     * @return priority sorted tasks arraylist
     */
    public ArrayList<Task> sortPriority(){
    tasks.sort(Comparator.comparingInt(Task::getPriority));
    return tasks;
    }
    /**
     * adds a comment to the column
     * @author duayne (tweaked by theo for column)
     * @param user
     * @param message
     */
    public boolean addComment(User user, String message){
        if(user != null && !message.isEmpty()){
            int size = comments.size();
            Comment comment = new Comment(user, message);
            comments.add(comment);
            return size != comments.size();
        }
        return false;
    }

    /**
     * toString for Column. Used in the GUI
     * @author ctaks
     */
    public String toString() {
        return this.name;
    }
}
