
public class NewFeature extends Task{
    private String implementation;
    private String updateNumber;

    /**
     * 
     * @param taskName
     * @param asignee
     * @param priority
     * @param description
     */
    public NewFeature(String taskName, User assignee, int priority, String description) {  
        super(taskName, assignee, priority, description);
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
}
