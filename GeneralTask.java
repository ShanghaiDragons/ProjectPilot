/**
 * This represents a general type of task. Doesn't really have any specific 
 */
public class GeneralTask extends Task{
    
    public GeneralTask(String taskName, User assignee, int priority, String description) {
        super(taskName, assignee, priority, description);
    }
}
