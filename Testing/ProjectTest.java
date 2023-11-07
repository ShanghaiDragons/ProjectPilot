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
    private User scrumMaster;
    private User viewer;
    private User collaborator;

    @Before
    public void setUp() {
        ArrayList<User> team = new ArrayList<>();
        ArrayList<Column> columnlist = new ArrayList<>();
        ArrayList<Comment> commentlist = new ArrayList<>();
        Project testProject = new Project("ProjectTest", LocalDate.now(), LocalDate.now(), team, columnlist, commentlist);
        User scrumMaster = new User("Scrum", "Master", "sMaster", "password", true, true, true, true);
        User collaborator = new User("Collab", "Borator", "cBorator", "password", false, true, true, false);
        User viewer = new User("Vi", "Ewer", "vEwer", "password", false, false, false, false);
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
    public void TestAddColumn(){
        Project testProject = new Project("ProjectTest",LocalDate.now(), LocalDate.now(),team, columnlist, commentlist);
        assertTrue(testProject.addColumn(ToDo));
    }
    //add comment
    public void TestAddComment(){
        Project testProject = new Project("ProjectTest",LocalDate.now(), LocalDate.now(),team, columnlist, commentlist);
        assertTrue(testProject.addComment(scrumMaster, "Test"));
    }
    //move task
    public void TestMoveTask(){
        assertTrue(testProject.moveTask(TASKKKKKKKK));
    }

    @Test
    public void testDataLoader() {
        assertTrue(true);
    }

}

