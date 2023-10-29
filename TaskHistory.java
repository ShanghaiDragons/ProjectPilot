import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * TaskHistory class. Keeps a list of all the changes to each task.
 */
public class TaskHistory {
  private UUID id;
  private UUID taskID;
  private Date creationDate;
  private ArrayList<String> nameChanges;
  private ArrayList<String> descriptionChanges;
  private ArrayList<String> moveChanges;
  private ArrayList<String> assigneeChanges;
  private ArrayList<String> priorityChanges;

  /**
   * Constructor for a new TaskHistory
   * @author ctaks
   * @param taskID new
   * @param creationDate new
   * @param nameChanges new
   * @param descriptionChanges new
   * @param moveChanges new
   * @param assigneeChanges new
   * @param priorityChanges new
   */
  public TaskHistory(UUID taskID, Date creationDate, ArrayList<String> nameChanges, ArrayList<String> descriptionChanges, ArrayList<String> moveChanges, ArrayList<String> assigneeChanges, ArrayList<String> priorityChanges) {
    setID(this.id);
    setTaskID(taskID);
    setCreationDate(creationDate);
    setNameChanges(nameChanges);
    setDescriptionChanges(descriptionChanges);
    setMoveChanges(moveChanges);
    setAssigneeChanges(assigneeChanges);
    setPriorityChanges(priorityChanges);
  }

  /**
   * Constructor for loading a TaskHistory
   * @author ctaks
   * @param id from JSON file
   * @param taskID from JSON file
   * @param creationDate from JSON file
   * @param nameChanges from JSON file
   * @param descriptionChanges from JSON file
   * @param moveChanges from JSON file
   * @param assigneeChanges from JSON file
   * @param priorityChanges from JSON file
   */
  public TaskHistory(UUID id, UUID taskID, Date creationDate, ArrayList<String> nameChanges, ArrayList<String> descriptionChanges, ArrayList<String> moveChanges, ArrayList<String> assigneeChanges, ArrayList<String> priorityChanges) {
    setID(id);
    setTaskID(taskID);
    setCreationDate(creationDate);
    setNameChanges(nameChanges);
    setDescriptionChanges(descriptionChanges);
    setMoveChanges(moveChanges);
    setAssigneeChanges(assigneeChanges);
    setPriorityChanges(priorityChanges);
  }

  /**
     * Setter for id
     * @author ctaks
     * @param id to be set
     * @return boolean determining success
     */
    public boolean setID(UUID id) {
      if (id != null) {
          this.id = id;
          return true;
      }
      else {
          this.id = UUID.randomUUID();
          return true;
      }
  }

  /**
     * Setter for taskID
     * @author ctaks
     * @param id to be set
     * @return boolean determining success
     */
    public boolean setTaskID(UUID id) {
      if (id != null) {
          this.taskID = id;
          return true;
      }
      else {
          this.taskID = null;
          return false;
      }
  }

  /**
   * setter for creationDate
   * @author ctaks
   * @param date to be set
   * @return boolean determining success
   */
  public boolean setCreationDate(Date date) {
    if (date != null) {
      this.creationDate = date;
      return true;
    } else {
      this.creationDate = new Date();
      return true;
    }
  }

  /**
   * setter for nameChanges
   * @author ctaks
   * @param nameChanges to be set
   * @return boolean determining success
   */
  public boolean setNameChanges(ArrayList<String> nameChanges) {
    if (nameChanges == null || nameChanges.isEmpty()) {
      this.nameChanges = new ArrayList<String>();
      return true;
    } else {
      this.nameChanges = nameChanges;
      return true;
    }
  }
/**
 * setter for descriptionChanges
 * @author ctaks
 * @param descriptionChanges to be set
 * @return boolean determining success
 */
public boolean setDescriptionChanges(ArrayList<String> descriptionChanges) {
  if (descriptionChanges == null || descriptionChanges.isEmpty()) {
    this.descriptionChanges = new ArrayList<String>();
    return true;
  } else {
    this.descriptionChanges = descriptionChanges;
    return true;
  }
}
/**
 * setter for moveChanges
 * @author ctaks
 * @param moveChanges to be set
 * @return boolean determining success
 */
public boolean setMoveChanges(ArrayList<String> moveChanges) {
  if (moveChanges == null || moveChanges.isEmpty()) {
    this.moveChanges = new ArrayList<String>();
    return true;
  } else {
    this.moveChanges = moveChanges;
    return true;
  }
}

/**
 * setters for assignee changes
 * @author ctaks
 * @param assigneeChanges to be set
 * @return boolean determining success
 */
public boolean setAssigneeChanges(ArrayList<String> assigneeChanges) {
  if (assigneeChanges == null || assigneeChanges.isEmpty()) {
    this.assigneeChanges = new ArrayList<String>();
    return true;
  } else {
    this.assigneeChanges = assigneeChanges;
    return true;
  }
}

/**
 * setters for priority changes
 * @author ctaks
 * @param priorityChanges to be set
 * @return boolean determining success
 */
public boolean setPriorityChanges(ArrayList<String> priorityChanges) {
  if (priorityChanges == null || priorityChanges.isEmpty()) {
    this.priorityChanges = new ArrayList<String>();
    return true;
  } else {
    this.priorityChanges = priorityChanges;
    return true;
  }
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
  public ArrayList<String> getNameChanges() {
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

  /**
   * Gets the UUID
   * @author ctaks
   * @return UUID of the id
   */
  public UUID getID() {
    return this.id;
  }

  /**
   * Gets the task
   * @author ctaks
   * @return Task of the task
   */
  public UUID getTaskID() {
    return this.taskID;
  }
}
