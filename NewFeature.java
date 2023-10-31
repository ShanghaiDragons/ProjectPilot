import java.util.ArrayList;
import java.util.UUID;

public class NewFeature extends Task{
    private String implementation;
    private String updateNumber;

    /**
     * 
     * @param name
     * @param assignee
     * @param priority
     * @param status
     * @param description
     * @param comments
     */
    public NewFeature(String name, User assignee, int priority, String status, String description, ArrayList<Comment> comments) {  
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
    public NewFeature(UUID id, String name, User assignee, int priority, String status, String description, ArrayList<Comment> comments) {
        super(name, assignee, priority, status, description, comments);
    }

    /**
     * 
     * @param implementation
     */
    public void setImplementation(String implementation) {
       
        this.implementation = implementation;
    }
    
    /**
     * 
     * @param updateNumber
     */
    public void setUpdateNumber(String updateNumber) {
        
        this.updateNumber = updateNumber;
    }
    
    public String getImplementation() {
        return implementation;
    }

    public String getUpdateNumber() {
        return updateNumber;
    }
}
