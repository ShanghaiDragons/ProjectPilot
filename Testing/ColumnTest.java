package Testing;
import sourceCode.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class ColumnTest {
    private Column column,emptyColumn;
    private String name;
    private String sortType;
    private ArrayList<Task> tasklist;
    private ArrayList<Comment> commentlist;
    private Task task1,task2,task3,nonExistingTask;
    private User scrumMaster, user1;

    @Before
    public void setup(){
        name = "ColumnName";
        sortType="alphabetical";
        scrumMaster = new User("Scrum", "Master", "sMaster", "password", true, true, true, true);
        user1 = new User("user1", "Lastname", "user1", "password", true, true, true, true);
        tasklist = new ArrayList<Task>();
        commentlist = new ArrayList<Comment>();
        column = new Column(name, sortType, tasklist, commentlist);
        task1 = new Task("A Task", scrumMaster, 3, "Status #1", "Description #1", new ArrayList<>());
        task2 = new Task("C Task", scrumMaster, 2, "Status #2", "Description #2", new ArrayList<>());
        task3 = new Task("B Task", user1, 1, "Status #3", "Description #3", new ArrayList<>());
        column.addTask(task1);
        column.addTask(task2);
        column.addTask(task3);
    }

    //Testing editing column name with a new name
    @Test
    public void TestEditColumnName(){
        assertTrue(column.editColumnName("New Name"));
    }

    //testing editing column name with the same name
    @Test
    public void TestEditColumnNameWithSameName(){
        assertFalse(column.editColumnName("ColumnName"));
    }
    
    //testing editing column name with empty characters
    @Test
    public void TestEditColumnNameWithEmptyCharacters(){
        assertFalse(column.editColumnName(""));
    }

    //testing editing column name with null value
    @Test
    public void TestEditColumnNameWithNullValue(){
        assertFalse(column.editColumnName(null));
    }

    //testing removing an existing task from column
    @Test
    public void TestRemoveExistingTask(){
        assertTrue(column.removeTask(task1));
    }

    //testing removing task not in column
    @Test
    public void TestRemoveNonExistingTask(){
        assertFalse(column.removeTask(nonExistingTask));
    }

    //testing adding duplicate task in column
    @Test
    public void TestAddDupeTask(){
        assertTrue(column.addTask(task1));
    }

    //testing add non existing task to column 
    @Test
    public void TestAddNonExistingTask(){
        assertFalse(column.addTask(nonExistingTask));
    }

    //testing sorting tasks by alphabetical order
    @Test
    public void TestAlphabetical(){
        column.sortAlphabetical();
        ArrayList<Task> sortedTasks = column.getTasks();
        Assert.assertEquals("A Task", sortedTasks.get(0).getName());
        Assert.assertEquals("B Task", sortedTasks.get(1).getName());
        Assert.assertEquals("C Task", sortedTasks.get(2).getName());
    }
    //testing sort by priority levels from highest (1) to lowest (3)
    @Test
    public void testSortTasksByPriority() {
        column.sortPriority();
        ArrayList<Task> sortedTasks = column.getTasks();
        Assert.assertEquals("B Task", sortedTasks.get(0).getName());
        Assert.assertEquals("C Task", sortedTasks.get(1).getName());
        Assert.assertEquals("A Task", sortedTasks.get(2).getName());
    }
    // Testing sorting tasks by assignee's username in alphabetical order. Making sure it actually sorts it properly so not using the sortTask method 
    @Test
    public void TestSortTasksByAssignee() {
        column.sortTasks("assignee");
        ArrayList<Task> sortedTasks = column.getTasks();
        Assert.assertEquals("C Task", sortedTasks.get(0).getName()); // User1's task comes first alphabetically
        Assert.assertEquals("A Task", sortedTasks.get(1).getName()); // ScrumMaster's task comes next alphabetically
        Assert.assertEquals("B Task", sortedTasks.get(2).getName()); // ScrumMaster's another task comes last alphabetically
    }
    // Test sorting an empty list of tasks.
    @Test
    public void testSortEmptyTasks() {
        emptyColumn = new Column("Empty Column", sortType, tasklist, commentlist);
        assertFalse(emptyColumn.sortTasks(sortType));
    }   
    // Testing Sorting with Tasks Having the Same Name
    @Test
    public void testSortTasksWithSameName() {
        Task task4 = new Task("A Task", user1, 4, "Status #4", "Description #4", new ArrayList<>());
        column.addTask(task4);
        column.sortAlphabetical();
        ArrayList<Task> sortedTasks = column.getTasks();
        Assert.assertEquals("A Task", sortedTasks.get(0).getName());
        Assert.assertEquals("A Task", sortedTasks.get(1).getName());
        Assert.assertEquals("B Task", sortedTasks.get(2).getName());
        Assert.assertEquals("C Task", sortedTasks.get(3).getName());
    }
    // Testing Sorting with Tasks Having Null or Empty Names
    @Test
    public void testSortTasksWithNullAndEmptyNames() {
        Task task4 = new Task(null, user1, 4, "Status #4", "Description #4", new ArrayList<>());
        Task task5 = new Task("", user1, 5, "Status #5", "Description #5", new ArrayList<>());
        column.addTask(task4);
        column.addTask(task5);
        assertFalse(column.sortTasks(sortType));
    }
    // Testing adding a comment to the column
    @Test
    public void TestAddComment() {
        assertTrue(column.addComment(scrumMaster, "Test Comment"));
        Assert.assertEquals(1, commentlist.size());
    }

    // Testing adding a comment with a null user
    @Test
    public void TestAddCommentWithNullUser() {
        assertFalse(column.addComment(null, "Test Comment"));
    }

    // Testing adding a comment with an empty message
    @Test
    public void TestAddCommentWithEmptyMessage() {
        assertFalse(column.addComment(scrumMaster,"Test Comment"));
    }
}