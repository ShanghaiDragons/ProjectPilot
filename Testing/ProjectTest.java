package Testing;

import sourceCode.*;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class ProjectTest {
    private Project testProject;
    private ArrayList<User> team;
    private ArrayList<Column> columnlist;
    private ArrayList<Comment> commentlist;
    private ArrayList<Task> tasklist;
    private User scrumMaster, collaborator, viewer;
    private Column ToDo,InProgress,Done;
    private Comment comment1,comment2;

    @Before
    public void setUp() {
        team = new ArrayList<>();
        columnlist = new ArrayList<>();
        commentlist = new ArrayList<>();
        testProject = new Project("ProjectTest", LocalDate.now(), LocalDate.now(), team, columnlist, commentlist);
        scrumMaster = new User("Scrum", "Master", "sMaster", "password", true, true, true, true);
        collaborator = new User("Collab", "Borator", "cBorator", "password", false, true, true, false);
        viewer = new User("Vi", "Ewer", "vEwer", "password", false, false, false, false);
        ToDo = new Column("To Do", "alphabetical", new ArrayList<>(), new ArrayList<>());
        InProgress = new Column("In Progress", "alphabetical", new ArrayList<>(), new ArrayList<>());
        Done = new Column("Done", "alphabetical", new ArrayList<>(), new ArrayList<>());
        testProject.addColumn(ToDo);
        testProject.addColumn(Done);
        testProject.addColumn(InProgress);
    }
    //Adding a scrum master to the team
    @Test
    public void addUsers(){
        //checking to see if they are added to the project user list 
        assertTrue(testProject.addTeamMember(scrumMaster, UserType.SCRUM_MASTER));
        assertTrue(testProject.addTeamMember(collaborator, UserType.COLLABORATOR));
        assertTrue(testProject.addTeamMember(viewer, UserType.VIEWER));
        
        //checks scrum master permissions
        assertTrue(scrumMaster.getPermissionToAddTask() && scrumMaster.getPermissionToEditColumns() && scrumMaster.getPermissionToEditTask() && scrumMaster.getPermissionToMoveTask());

        //checks collaborator permissions
        assertTrue(collaborator.getPermissionToEditTask() && collaborator.getPermissionToMoveTask());
        assertFalse(collaborator.getPermissionToAddTask() && collaborator.getPermissionToEditColumns());

        //checks viewer permissions
        assertFalse(viewer.getPermissionToAddTask() && viewer.getPermissionToEditColumns() && viewer.getPermissionToEditTask() && viewer.getPermissionToMoveTask());        
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
    public void TestMoveTask(){
        assertTrue(testProject.moveTask(TASKKKKKKKK));
    }

    @Test
    public void testDataLoader() {
        assertTrue(true);
    }

}

