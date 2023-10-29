import java.util.ArrayList;
import java.util.UUID;

/**
 * This represents a general type of task. Doesn't really have any specific 
 */
public class GeneralTask extends Task{
    
        /**
     * 
     * @param name
     * @param assignee
     * @param priority
     * @param status
     * @param description
     * @param comments
     */
    public GeneralTask(String name, User assignee, int priority, String status, String description, ArrayList<Comment> comments) {  
        super(name, assignee, priority, status, description, comments);
    }

    /**
     * 
     * @param id
     * @param name
     * @param assignee
     * @param priority
     * @param status
     * @param description
     * @param comments
     */
    public GeneralTask(UUID id, String name, User assignee, int priority, String status, String description, ArrayList<Comment> comments) {
        super(name, assignee, priority, status, description, comments);
    }
}
