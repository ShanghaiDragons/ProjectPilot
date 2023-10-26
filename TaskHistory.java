import java.util.ArrayList;
import java.util.Date;

/**
 * 
 */
public class TaskHistory {
  private Task task;
  private Date creationDate;
  private ArrayList<String> nameChanges;
  private ArrayList<String> descriptionChanges;
  private ArrayList<String> moveChanges;
  private ArrayList<String> assigneeChanges;
  private ArrayList<String> priorityChanges;

  /**
   * Constructs the task history for the given task
   * @param task to be kept for ID purposes
   */
  public TaskHistory(Task task) {
    this.task = task;
  }

  /**
   * creation date getter
   * @author ctaks
   * @return ArrayList<String> of creation dates
   */
  public Date getCreationDate() {
    return this.creationDate;
  }

  /**
   * name change getter
   * @author ctaks
   * @return ArrayList<String> of name changes
   */
  public ArrayList<String> getNameChange() {
    return this.nameChanges;
  }

  /**
   * description change getter
   * @author ctaks
   * @return ArrayList<String> of descriptions changes
   */
  public ArrayList<String> getDescriptionChanges() {
    return this.descriptionChanges;
  }

  /**
   * move change getter
   * @author ctaks
   * @return ArrayList<String> of move changes
   */
  public ArrayList<String> getMoveChanges() {
    return this.moveChanges;
  }

  /**
   * Assignee changes getter
   * @author ctaks
   * @return ArrayList<String> of assignee changes
   */
  public ArrayList<String> getAssigneeChanges() {
    return this.assigneeChanges;
  }

  /**
   * priority changes getter
   * @author ctaks
   * @return ArrayList<String> of priority changes
   */
  public ArrayList<String> getPriorityChanges() {
    return this.priorityChanges;
  }
}
