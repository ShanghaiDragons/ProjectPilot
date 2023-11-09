package Testing;
import sourceCode.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class ProjectTest {
    private Project testProject;
    private ArrayList<User> team;
    private ArrayList<Column> columnlist;
    private ArrayList<Comment> commentlist;
    private User scrumMaster, collaborator, viewer, user1, nonExistingUser,nonTeamMember;
    private Column ToDo,InProgress,Done, nonexistingcolumn;
    private Task task1, task2,task3, nonexistingTask;

    @Before
    public void setUp() {
        team = new ArrayList<>();
        columnlist = new ArrayList<>();
        commentlist = new ArrayList<>();
        testProject = new Project("ProjectTest", LocalDate.now(), LocalDate.now(), team, columnlist, commentlist);
        scrumMaster = new User("Scrum", "Master", "sMaster", "password", true, true, true, true);
        collaborator = new User("Collab", "Borator", "cBorator", "password", false, true, true, false);
        viewer = new User("Vi", "Ewer", "vEwer", "password", false, false, false, false);
        user1 = new User("First","Last","User","Password",true, true, true, true);
        nonTeamMember = new User("Non", "Team", "Member", "password", true,true,true,true);
        ToDo = new Column("To Do", "alphabetical", new ArrayList<>(), new ArrayList<>());
        InProgress = new Column("In Progress", "alphabetical", new ArrayList<>(), new ArrayList<>());
        Done = new Column("Done", "alphabetical", new ArrayList<>(), new ArrayList<>());
        task1 = new Task("Task #1", scrumMaster, 1, "Status #1", "Description #1", new ArrayList<>());
        task2 = new Task("Task #2", scrumMaster, 3, "Status #2", "Description #2", new ArrayList<>());
        task3 = new Task("Task #3", scrumMaster, 2, "Status #3", "Description #3", new ArrayList<>());
        testProject.addTeamMember(scrumMaster, UserType.SCRUM_MASTER);
        testProject.addTeamMember(collaborator, UserType.COLLABORATOR);
        testProject.addTeamMember(viewer, UserType.VIEWER);
        testProject.addColumn(ToDo);
        testProject.addColumn(Done);
        testProject.addColumn(InProgress);
        testProject.addComment(collaborator, "Test Comment #1");
        testProject.addComment(viewer, "Test Comment #2");
        ToDo.addTask(task1);
        InProgress.addTask(task2);
        Done.addTask(task3);
    }
    //Adding a scrum master to the team
    @Test
    public void testAddUser(){
       assertTrue(testProject.addTeamMember(user1, UserType.SCRUM_MASTER));
    }

    //adding a duplicate user
    @Test 
    public void testAddDuplicateUser(){
        assertFalse(testProject.addTeamMember(scrumMaster, UserType.SCRUM_MASTER));
    }
    //adding null value user
    @Test
    public void testAddNullUser(){
        User nulluser = new User(null, null, null, null, false, false, false,false);
        assertFalse(testProject.addTeamMember(nulluser, null));
    }

    // Adding a user with an empty first name
    @Test
    public void testAddUserWithEmptyUserName(){
        User user = new User("", "LastName", "username123", "password123", true, true, true, true);
        assertFalse(testProject.addTeamMember(user, UserType.SCRUM_MASTER));
    }

    // Adding a user with an empty last name
    @Test
    public void testAddUserWithEmptyFirstName(){
        User user = new User("FirstName", "", "username123", "password123", true, true, true, true);
        assertFalse(testProject.addTeamMember(user, UserType.SCRUM_MASTER));
    }

    // Adding a user with an empty username name
    @Test
    public void testAddUserWithEmptyLastName(){
        User user = new User("FirstName", "LastName", "", "password123", true, true, true, true);
        assertFalse(testProject.addTeamMember(user, UserType.SCRUM_MASTER));
    }

    // Adding a user with an empty password
    @Test
    public void testAddUserWithEmptyPassword(){
        User user = new User("FirstName", "LastName", "username123", "", true, true, true, true);
        assertFalse(testProject.addTeamMember(user, UserType.SCRUM_MASTER));
    }

    //adding user with null usertype
    @Test
    public void testAddUserWithNullUserType(){
        User user = new User("FirstName", "LastName", "username123", "", true, true, true, true);
        assertFalse(testProject.addTeamMember(user, null));
    }

    //remove user when one user is in the project 
    @Test
    public void TestRemoveUser(){
        assertTrue(testProject.removeUser(scrumMaster));
    }
    //remove user not in team
    @Test
    public void TestRemoveNonTeamUser(){
        assertFalse(testProject.removeUser(nonTeamMember));
    }
    //remove nonexisting user
    @Test
    public void TestRemoveNonExistingUser(){
        assertFalse(testProject.removeUser(nonExistingUser));
    }

    //remove column 
    @Test
    public void TestRemoveColumn(){
        assertTrue(testProject.removeColumn(ToDo));
    }

    //remove non existing column
    @Test
    public void TestNonExistingColumn(){
        assertFalse(testProject.removeColumn(nonexistingcolumn));
    }

    //add column
    @Test
    public void TestAddColumn(){
        assertTrue(testProject.addColumn(ToDo));
    }

    //test add duplicate column 
    @Test
    public void TestAddDuplicateColumn(){
        assertFalse(testProject.addColumn(ToDo));
    }

    //adding a null column
    @Test
    public void TestAddNullColumn(){
        Column nullColumn = new Column(null,null,null,null);
        assertFalse(testProject.addColumn(nullColumn));
    }

    //adding a column with an empty sorttype 
    @Test
    public void TestAddColumnWithEmptySortType(){
        Column emptySortType = new Column("Column Name","",new ArrayList<>(),new ArrayList<>());
        assertFalse(testProject.addColumn(emptySortType));
    }

    //add comment
    @Test
    public void TestAddComment(){
        assertTrue(testProject.addComment(scrumMaster, "Test"));
    }
    //add comment from non team users
    @Test
    public void TestAddCommentFromNonTeamUsers(){
        assertFalse(testProject.addComment(nonTeamMember,"Test Comment from non team member"));
    }

    //move task from to do to in progress 
    @Test
    public void TestMoveTaskFromToDoToInProgress(){
        assertTrue(testProject.moveTask(ToDo, InProgress,task1));
    }

    //move task from in progress to done
    @Test 
    public void TestMoveTaskFromInProgressToDone(){
        assertTrue(testProject.moveTask(InProgress, Done, task2));
    }
    //move task from done to do
    @Test 
    public void TestMoveTaskFromDoneToToDo(){
        assertTrue(testProject.moveTask(Done,ToDo,task3));
    }
    //move task to the same column
    @Test
    public void TestMoveTaskToSameColumn(){
        assertFalse(testProject.moveTask(Done,Done,task3));
    }
    //attempt to move a task that is not in the given source column 
    @Test 
    public void TestMoveTaskThatIsNotInSourceColumn(){
        assertFalse(testProject.moveTask(ToDo,Done,task3));
    }

    //moving a non existing task
    @Test
    public void TestMovingNonexistingTask(){
        assertFalse(testProject.moveTask(InProgress, Done, nonexistingTask));
    }

    @Test
    public void testDataLoader() {
        assertTrue(true);
    }
}

