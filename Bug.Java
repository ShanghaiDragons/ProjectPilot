import java.util.ArrayList;

public class Bug extends Task{
    private String bugEnvironment;
    private ArrayList<String> bugReproduction;
    private User user;

    // Default constructor for Bug class
    public Bug() {
        // Implement logic for the constructor if needed
        // This code will be executed when a new Bug object is created
    }

    public void setBugEnvironment(String bugEnvironment) {
        // Implement logic to set the bug environment
        this.bugEnvironment = bugEnvironment;
    }

    public void setBugReproduction(ArrayList<String> bugReproduction) {
        // Implement logic to set the bug reproduction steps
        this.bugReproduction = bugReproduction;
    }

    public void setUser(User user) {
        // Implement logic to set the user associated with the bug
        this.user = user;
    }
}
