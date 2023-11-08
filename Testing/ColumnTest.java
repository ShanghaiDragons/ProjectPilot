package Testing;
import sourceCode.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class ColumnTest {
    private Column column;
    private String name;
    private String sortType;
    private ArrayList<Task> tasklist;
    private ArrayList<Comment> commentlist;
    private Task task1,task2,nonExistingTask;
    private User scrumMaster;

    @Before
    public void setup(){
        name = "ColumnName";
        sortType="priority";
        scrumMaster = new User("Scrum", "Master", "sMaster", "password", true, true, true, true);
        tasklist = new ArrayList<Task>();
        commentlist = new ArrayList<Comment>();
        column = new Column(name, sortType, tasklist, commentlist);
        task1 = new Task("Task #1", scrumMaster, 1, "Status #1", "Description #1", new ArrayList<>());
        task2 = new Task("Task #2", scrumMaster, 3, "Status #2", "Description #2", new ArrayList<>());
        column.addTask(task1);
        column.addTask(task2);
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
        assertFalse(column.addTask(task1));
    }

    //add non existing task
    @Test
    public void TestAddNonExistingTask(){
        assertFalse(column.addTask(nonExistingTask));
    }

    //s


    






    
}
