import java.util.ArrayList;
/**
 * Bug class is a type of task that shows the user that theres a bug in the system. This class extends Task.
 */
public class Bug extends Task{
    private String bugEnvironment;
    private ArrayList<String> bugReproduction;
    private User user;

  
    public Bug() {
        
    }

    public void setBugEnvironment(String bugEnvironment) {
      
        this.bugEnvironment = bugEnvironment;
    }

    public void setBugReproduction(ArrayList<String> bugReproduction) {
        
        this.bugReproduction = bugReproduction;
    }

    public void setUser(User user) {
        
        this.user = user;
    }
}
