import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

/**
 * This comment class represents a comment in  a task, a column, or another comment. 
 */
public class Comment {
    protected UUID id;
    private User user;
    private Task task;
    private String comment;
    private ArrayList<String> comments;

    /**
     * @param user
     * @param task
     * @param comment
     */
    public Comment(UUID id, User user, Task task, String comment) {
        this.user = user;
        this.task = task;
        this.comment = comment;
    }

    /**
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
     * adds a comment
     * @author theo v
     * @param comment string that is the comment
     */
    public boolean addComment(String comment) {
        if(!comment.isEmpty()){
        this.comment=comment;
        return true;
        }
        return false; 
    }

    /**
     * adding a comment on another comment, hence threading the comments
     * @author theo 
     * @param user
     * @param task
     * @param comment
     * @return
     */
    public boolean threadComment(String comment) {
        if(!comment.isEmpty()){
            comments.add(comment);
            return true;
            }
            return false; 
    }

    /**
     * @return returns the comment with the author user name
     */
    public String toString() {
        
        return "Comment{" +
                "user=" + user+
                ", comment='" + comment + '\'' +
                '}';
    }
}
