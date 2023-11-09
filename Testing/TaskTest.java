package Testing;
import sourceCode.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

// actually for testing TaskHistory
public class TaskTest {
  private Task task;
  private String name;
  private User assignee;
  private int priority1, priority2, priority3;
  private String description;
  private TaskHistory taskHistory;
  private ArrayList<Comment> comments;
  private String oldStringNull, newStringNull, oldString, newString;

  @Before
  public void setup() {
    name = "test name";
    assignee = new User("test", "test", "tester", "p", true, true, true, true);
    priority1 = 1;
    priority2 = 2;
    priority3 = 3;
    description = "test description";
    comments = new ArrayList<Comment>();
    oldStringNull = null;
    oldString = "test old";
    newStringNull = null;
    newString = "test new";
  }

  @Test
  public void TestAddNameChangesWithOldNull() {
    assertFalse(taskHistory.addNameChange(oldStringNull, newString));
  }

  @Test
  public void TestAddDescriptionChangesWithOldNull() {
    assertFalse(taskHistory.addDescriptionChange(oldStringNull, newString));
  }
  
  @Test
  public void TestAddMoveChangesWithOldNull() {
    assertFalse(taskHistory.addMoveChange(oldStringNull, newString));
  }

  @Test
  public void TestAddAssigneeChangesWithOldNull() {
    assertFalse(taskHistory.addAssigneeChange(oldStringNull, newString));
  }

  @Test
  public void TestAddPriorityChangesWithOldNull() {
    assertFalse(taskHistory.addPriorityChange(oldStringNull, newString));
  }

  @Test
  public void TestAddStatusChangesWithOldNull() {
    assertFalse(taskHistory.addStatusChange(oldStringNull, newString));
  }
  
}
