/**
 * This comment class represents a comment in  a task, a column, or another comment. 
 */
public class Comment {
    private User user;
    private Task task;
    private String comment;

    /**
     * 
     * @param user
     * @param task
     * @param comment
     */
    public Comment(User user, Task task, String comment) {
        this.user = user;
        this.task = task;
        this.comment = comment;
    }

    /**
     * 
     * @param user
     * @param task
     * @param comment
     * @return
     */
    public static boolean addComment(User user, Task task, String comment) {
        // Implement logic to add a comment to the task
        // Return true if comment added successfully, false otherwise
        return false; // Placeholder return value
    }

    /**
     * 
     * @param user
     * @param task
     * @param comment
     * @return
     */
    public static boolean threadComment(User user, Task task, String comment) {
      
        return false; 
    }

    /**
     * 
     */
    public String toString() {
        
        return "Comment{" +
                "user=" + user +
                ", task=" + task +
                ", comment='" + comment + '\'' +
                '}';
    }
}
