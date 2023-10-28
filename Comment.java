import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;

/**
 * This comment class represents a comment in  a task, a column, or another comment. 
 */
public class Comment {
    private UUID id;
    private User user;
    private Date date;
    private String comment;
    private ArrayList<Comment> comments;

    /**
     * @param user
     * @param task
     * @param comment
     */
    public Comment(UUID id, User user, String comment) {
        this.user = user;
        this.comment = comment;
    }

    /**
     * @param user
     * @param task
     * @param comment
     */
    public Comment(User user, Task task, String comment) {
        this.user = user;
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
     * @author theo (edited by ctaks)
     * @param comment the comment to be added
     * @return boolean determining if the addition was a success
     */
    public boolean threadComment(Comment comment) {
        if(comment.getID() != null){
            comments.add(comment);
            return true;
            }
        return false; 
    }

    /**
     * Gets the comment's assignee
     * @author ctaks
     * @return User of the user
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Get's the comment's date
     * @author ctaks
     * @return Date of the comment's date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Get's the comment's message
     * @author ctaks
     * @return String of the comment's message
     */
    public String getMessage() {
        return this.comment;
    }

    /**
     * Get's the comment's UUID
     * @author ctaks
     * @return UUID of the comment's UUID
     */
    public UUID getID() {
        return this.id;
    }

    /**
     * Get's the comment's thread
     * @author ctaks
     * @return ArrayList<Comment> of the comment's thread
     */
    public ArrayList<Comment> getThread() {
        return this.comments;
    }
}
