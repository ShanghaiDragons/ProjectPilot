import java.util.ArrayList;
import java.util.UUID;

/**
 * Bug class is a type of task that shows the user that theres a bug in the system. This class extends Task.
 */
public class Bug extends Task{
    private String bugEnvironment;
    private ArrayList<String> bugReproduction;
    private User user;

  
    /**
     * 
     * @param name
     * @param assignee
     * @param priority
     * @param status
     * @param description
     * @param comments
     */
    public Bug(String name, User assignee, int priority, String status, String description, ArrayList<Comment> comments) {
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
    public Bug(UUID id, String name, User assignee, int priority, String status, String description, ArrayList<Comment> comments) {
        super(name, assignee, priority, status, description, comments);
    }

    public void setBugEnvironment(String bugEnvironment) {
        this.bugEnvironment = bugEnvironment;
    }

    public void setBugReproduction(ArrayList<String> bugReproduction) {
        this.bugReproduction = bugReproduction;
    }
    /**
     * sets user
     * @author theo
     * @param user 
     */
    public void setUser(User user) {
        if(user!=null&&!user.isEmpty()){
        this.user = user;
        }
    }
}
