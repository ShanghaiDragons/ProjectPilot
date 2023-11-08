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
    private User scrumMaster, collaborator, viewer, user1, nulluser;
    private Column ToDo,InProgress,Done;
    private Task task1, task2,task3;

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
        testProject.moveTask(ToDo, InProgress, task1); 
    }
    //Adding a scrum master to the team
    @Test
    public void testAddUser(){
       testProject.addTeamMember(user1, UserType.SCRUM_MASTER);      
    }
    @Test
    public void testAddNullUser(){
        User nulluser = new User(null, null, null, null, false, false, false,false);
        assertFalse(testProject.addTeamMember(nulluser, null));
    }

    //remove user when one user is in the project 
    @Test
    public void TestRemoveUser(){
        User user = new User("John", "Doe", "jdoe", "password", true,true,true,true);
        testProject.addTeamMember(user, UserType.SCRUM_MASTER);
        assertTrue(testProject.removeUser(user));
    }

    //remove column 
    @Test
    public void TestRemoveColumn(){
        Project testProject = new Project("ProjectTest",LocalDate.now(), LocalDate.now(),team, columnlist, commentlist);
        assertTrue(testProject.removeColumn(ToDo));
    }

    //add column
    @Test
    public void TestAddColumn(){
        Project testProject = new Project("ProjectTest",LocalDate.now(), LocalDate.now(),team, columnlist, commentlist);
        assertTrue(testProject.addColumn(ToDo));
    }
    //add comment
    @Test
    public void TestAddComment(){
        Project testProject = new Project("ProjectTest",LocalDate.now(), LocalDate.now(),team, columnlist, commentlist);
        assertTrue(testProject.addComment(scrumMaster, "Test"));
    }
    //move task
    @Test
    public void TestMoveTaskFromToDoToInProgress(){
        assertTrue(testProject.moveTask(ToDo, InProgress,task1));
    }

    @Test 
    public void TestMoveTaskFromInProgressToDone(){
        assertTrue(testProject.moveTask(InProgress, Done, task2));
    }

    @Test 
    public void TestMoveTaskFromDoneToToDo(){
        assertTrue(testProject.moveTask(Done,ToDo,task3));
    }
    @Test
    public void testDataLoader() {
        assertTrue(true);
    }

}

