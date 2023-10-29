import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * TaskHistory class. Keeps a list of all the changes to each task.
 */
public class TaskHistory {
  private UUID id;
  private UUID taskID;
  private LocalDateTime creationDate;
  private ArrayList<String> nameChanges;
  private ArrayList<String> descriptionChanges;
  private ArrayList<String> moveChanges;
  private ArrayList<String> assigneeChanges;
  private ArrayList<String> priorityChanges;
  private ArrayList<String> statusChanges;

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
   * @param statusChanges new
   */
  public TaskHistory(UUID taskID, LocalDateTime creationDate, ArrayList<String> nameChanges, ArrayList<String> descriptionChanges, ArrayList<String> moveChanges, ArrayList<String> assigneeChanges, ArrayList<String> priorityChanges, ArrayList<String> statusChanges) {
    setID(this.id);
    setTaskID(taskID);
    setCreationDate(creationDate);
    setNameChanges(nameChanges);
    setDescriptionChanges(descriptionChanges);
    setMoveChanges(moveChanges);
    setAssigneeChanges(assigneeChanges);
    setPriorityChanges(priorityChanges);
    setStatusChanges(statusChanges);
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
   * @param statusChanges from JSON file
   */
  public TaskHistory(UUID id, UUID taskID, LocalDateTime creationDate, ArrayList<String> nameChanges, ArrayList<String> descriptionChanges, ArrayList<String> moveChanges, ArrayList<String> assigneeChanges, ArrayList<String> priorityChanges, ArrayList<String> statusChanges) {
    setID(id);
    setTaskID(taskID);
    setCreationDate(creationDate);
    setNameChanges(nameChanges);
    setDescriptionChanges(descriptionChanges);
    setMoveChanges(moveChanges);
    setAssigneeChanges(assigneeChanges);
    setPriorityChanges(priorityChanges);
    setStatusChanges(statusChanges);
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
  public boolean setCreationDate(LocalDateTime date) {
    if (date != null) {
      this.creationDate = date;
      return true;
    } else {
      this.creationDate = LocalDateTime.now();
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

public boolean setStatusChanges(ArrayList<String> statusChanges) {
  if (statusChanges == null || statusChanges.isEmpty()) {
    this.statusChanges = new ArrayList<String>();
    return true;
  } else {
    this.statusChanges = statusChanges;
    return true;
  }
}

  /**
   * creation date getter
   * @author ctaks
   * @return ArrayList<String> of creation dates
   */
  public LocalDateTime getCreationDate() {
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
   * status changes getter
   * @author ctaks
   * @return ArrayList<String> of status changes
   */
  public ArrayList<String> getStatusChanges() {
    return this.statusChanges;
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

  /**
   * records a name change
   * @author ctaks
   * @param oldS the thing that is being replaced
   * @param newS the thing that will replace
   * @return boolean determining success
   */
  public boolean addNameChange(String oldS, String newS) {
    LocalDateTime time = LocalDateTime.now();
    if (nameChanges.isEmpty()) {
      if(newS != null) {
        nameChanges.add("["+time.toString()+"]: \""+newS+"\" was set");
        return true;
      } else {
        nameChanges.add("["+time.toString()+"]: \"ERROR\" was set");
        return false;
      }
    }

    if (oldS != null && newS != null) {
      nameChanges.add("["+time.toString()+"]: \""+oldS+"\" changed to: \""+newS+"\"");
      return true;
    } else {
      nameChanges.add("["+time.toString()+"]: \"ERROR\" changed to: \"ERROR\"");
      return false;
    }
  }

  /**
   * records a description change
   * @author ctaks
   * @param oldS the thing that is being replaced
   * @param newS the thing that will replace
   * @return boolean determining success
   */
  public boolean addDescriptionChange(String oldS, String newS) {
    LocalDateTime time = LocalDateTime.now();
    if (descriptionChanges.isEmpty()) {
      if(newS != null) {
        descriptionChanges.add("["+time.toString()+"]: \""+newS+"\" was set");
        return true;
      } else {
        descriptionChanges.add("["+time.toString()+"]: \"ERROR\" was set");
        return false;
      }
    } 

    if (oldS != null && newS != null) {
      descriptionChanges.add("["+time.toString()+"]: \""+oldS+"\" changed to: \""+newS+"\"");
      return true;
    } else {
      descriptionChanges.add("["+time.toString()+"]: \"ERROR\" changed to: \"ERROR\"");
      return false;
    }
  }

  /**
   * records a move change
   * @author ctaks
   * @param oldS the thing that is being replaced
   * @param newS the thing that will replace
   * @return boolean determining success
   */
  public boolean addMoveChange(String oldS, String newS) {
    LocalDateTime time = LocalDateTime.now();
    if (moveChanges.isEmpty()) {
      if(newS != null) {
        moveChanges.add("["+time.toString()+"]: \""+newS+"\" was set");
        return true;
      } else {
        moveChanges.add("["+time.toString()+"]: \"ERROR\" was set");
        return false;
      }
    }

    if (oldS != null && newS != null) {
      moveChanges.add("["+time.toString()+"]: \""+oldS+"\" changed to: \""+newS+"\"");
      return true;
    } else {
      moveChanges.add("["+time.toString()+"]: \"ERROR\" changed to: \"ERROR\"");
      return false;
    }
  }

  /**
   * records an assignee change
   * @author ctaks
   * @param oldS the thing that is being replaced
   * @param newS the thing that will replace
   * @return boolean determining success
   */
  public boolean addAssigneeChange(String oldS, String newS) {
    LocalDateTime time = LocalDateTime.now();
    if (assigneeChanges.isEmpty()) {
      if(newS != null) {
        assigneeChanges.add("["+time.toString()+"]: \""+newS+"\" was set");
        return true;
      } else {
        assigneeChanges.add("["+time.toString()+"]: \"ERROR\" was set");
        return false;
      }
    }

    if (oldS != null && newS != null) {
      assigneeChanges.add("["+time.toString()+"]: \""+oldS+"\" changed to: \""+newS+"\"");
      return true;
    } else {
      assigneeChanges.add("["+time.toString()+"]: \"ERROR\" changed to: \"ERROR\"");
      return false;
    }
  }

  /**
   * records a priority change
   * @author ctaks
   * @param oldS the thing that is being replaced
   * @param newS the thing that will replace
   * @return boolean determining success
   */
  public boolean addPriorityChange(String oldS, String newS) {
    LocalDateTime time = LocalDateTime.now();
    if (priorityChanges.isEmpty()) {
      if(newS != null) {
        priorityChanges.add("["+time.toString()+"]: \""+newS+"\" was set");
        return true;
      } else {
        priorityChanges.add("["+time.toString()+"]: \"ERROR\" was set");
        return false;
      }
    }

    if (oldS != null && newS != null) {
      priorityChanges.add("["+time.toString()+"]: \""+oldS+"\" changed to: \""+newS+"\"");
      return true;
    } else {
      priorityChanges.add("["+time.toString()+"]: \"ERROR\" changed to: \"ERROR\"");
      return false;
    }
  }

  /**
   * records a status change
   * @author ctaks
   * @param oldS the thing that will be replaced
   * @param newS the thing that will replace
   * @return boolean determining success
   */
  public boolean addStatusChange(String oldS, String newS) {
    LocalDateTime time = LocalDateTime.now();
    if (statusChanges.isEmpty()) {
      if(newS != null) {
        statusChanges.add("["+time.toString()+"]: \""+newS+"\" was set");
        return true;
      } else {
        statusChanges.add("["+time.toString()+"]: \"ERROR\" was set");
        return false;
      }
    }

    if (oldS != null && newS != null) {
      statusChanges.add("["+time.toString()+"]: \""+oldS+"\" changed to: \""+newS+"\"");
      return true;
    } else {
      statusChanges.add("["+time.toString()+"]: \"ERROR\" changed to: \"ERROR\"");
      return false;
    }
  }
}
