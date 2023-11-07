package Testing;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;

import Column;
import Comment;
import Project;
import Task;
import User;

public class ProjectTest {
    private Project project;
    private ArrayList<User> team;
    private ArrayList<Column> columnlist;
    private ArrayList<Comment> commentlist;
    private ArrayList<Task> tasklist;

    Column ToDo = new Column("To Do", "alphabetical", tasklist, commentlist);
    Column InProgress = new Column("In Progress", "alphabetical", tasklist, commentlist);
    Column Done = new Column("Done", "alphabetical", tasklist, commentlist);
    //Adding a scrum master to the team
    @Test
    public void addUsers(){
        Project testProject = new Project("ProjectTest",LocalDate.now(), LocalDate.now(),team, columnlist, commentlist);
        
        //initializing different types of users
        User scrumMaster = new User("Scrum", "Master", "sMaster","password", true,true,true,true);
        User collaborator = new User ("Collab","Borator", "cBorator", "password", false, true, true, false);
        User viewer = new User ("Vi", "Ewer", "vEwer", "password", false, false, false, false);
        
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
        Project testProject = new Project("ProjectTest",LocalDate.now(), LocalDate.now(),team, columnlist, commentlist);
        User user = new User("John", "Doe", "jdoe", "password", true,true,true,true);
        project.addTeamMember(user, UserType.SCRUM_MASTER);
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

