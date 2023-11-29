package Testing;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

import projectpilot_gui.src.main.java.model.*;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.UUID;

public class ProjectPilotFacadeTest {

    private ProjectPilotFacade ppf;
    private User user;
    private User user1;
    private Column sourceColumn;
    private Column destinationColumn;
    private Task task;

    @Before
    public void setUp() {
        ppf = ProjectPilotFacade.getInstance();
        ppf.createAccount("John", "Doe", "johndoe", "password");
        ppf.createAccount("Cole", "Lab", "Collab", "p");
        ppf.createAccount("Vi", "Ewer", "viewer", "p");
        ppf.login("johndoe", "password");
        for (User Luser : ppf.getUsers()) {
            if (Luser.getUserName().equals("johndoe")) {
                user1 = Luser;
            }
        }
        //user1 = ppf.getUsers().get(0);
        ppf.addProject("Project1", LocalDate.now(), LocalDate.now().plusDays(7), new ArrayList<User>(), user1, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Column>(), new ArrayList<Comment>());
        ppf.loadProject(ppf.getProjects().get(0).getID());
        ppf.addColumn("Column1", "sortType", new ArrayList<>(), new ArrayList<>());
        ppf.addColumn("Column2", "sortType", new ArrayList<>(), new ArrayList<>());
        user = ppf.getUser();
        sourceColumn = ppf.getProjects().get(0).getColumns().get(0);
        destinationColumn = ppf.getProjects().get(0).getColumns().get(1);
    } 
    
    //testing creating a new account and when a duplicate account is created 
    @Test
    public void testCreateAccount() {
        assertTrue(ppf.createAccount("John", "Doe", "johndoe", "password"));
        assertFalse(ppf.createAccount("John", "Doe", "johndoe", "password")); 
    }

    //testing logging out, and trying to log out again
    @Test
    public void testLogout() {
        assertTrue(ppf.logout());
        assertFalse(ppf.logout());
    }
    //testing adding project 
    @Test
    public void testAddProject() {
        //correct parameters
        assertTrue(ppf.addProject("Project2", LocalDate.now(), LocalDate.now().plusDays(7), new ArrayList<User>(), user, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Column>(), new ArrayList<Comment>())); 
        //testing adding a project that has a backward start/end sprint dates
        assertFalse(ppf.addProject("Project3", LocalDate.now().plusDays(7), LocalDate.now(), new ArrayList<User>(), user, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Column>(), new ArrayList<Comment>()));
        //testing adding a project with empty name value
        assertFalse(ppf.addProject("", LocalDate.now(), LocalDate.now().plusDays(7), new ArrayList<User>(), user, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Column>(), new ArrayList<Comment>())); 
        //testing adding a project with null name value
        assertFalse(ppf.addProject(null, LocalDate.now(), LocalDate.now().plusDays(7), new ArrayList<User>(), user, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Column>(), new ArrayList<Comment>()));
    }

    // new editProject test
    @Test
    public void testCanEditProject() {
        // SCRUM MASTER
        ppf.login("johndoe", "password");
        assertTrue(ppf.canEditProject("setProjectName"));

        // COLLABORATOR
        ppf.login("Collab", "p");
        ppf.loadProject(ppf.getCurrentProject().getID());
        assertFalse(ppf.canEditProject("setProjectName"));

        // VIEWER
        ppf.login("viewer", "p");
        ppf.loadProject(ppf.getCurrentProject().getID());
        assertFalse(ppf.canEditProject("setProjectName"));

    
    }

    //testing removing project scenarios
    @Test
    public void testRemoveProject() {
        //removing existing project 
        assertTrue(ppf.removeProject(ppf.getProjects().get(0).getID().toString()));
        //removing non existing project
        assertFalse(ppf.removeProject("nonexistentID")); // Nonexistent project
    }
    //testing adding column scenarios
    @Test
    public void testAddColumn() {
        //
        assertTrue(ppf.addColumn("Column1", "sortType", new ArrayList<>(), new ArrayList<>()));
    }
    //testing removing column scenarios
    @Test
    public void testRemoveColumn() {
        //removing existing column
        assertTrue(ppf.removeColumn(sourceColumn.getID().toString()));
        //removing non existing column
        assertFalse(ppf.removeColumn("nonexistentID")); 
    }

    //testing moving tasks
    @Test
    public void testMoveTask() {
        ppf.addTask(sourceColumn, "Task1", user, 1, "New", "Description", new ArrayList<>());
        task= ppf.getCurrentProject().getColumns().get(1).getTasks().get(1);
        assertTrue(ppf.moveTask(sourceColumn,destinationColumn,task));
    }

    //testing loading project 
    @Test
    public void testLoadProject(){
        assertTrue(ppf.loadProject(ppf.getCurrentProject().getID()));
    }
    
    //testing loading non existent project 
    @Test
    public void testLoadNonExistingProject(){
        assertFalse(ppf.loadProject(UUID.randomUUID()));
    }
    //testing saving project and checking to see if it gets saved as not null 
    @Test
    public void testSaveProject(){
        ppf.addProject("Project2", LocalDate.now(), LocalDate.now().plusDays(7), new ArrayList<User>(), user, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Column>(), new ArrayList<Comment>());
        assertTrue(ppf.saveProjects());
    }
    
    //testing saving users 
    @Test
    public void testSaveUsers() {
        ppf.createAccount("Alice", "Smith", "alice123", "password");
        ppf.createAccount("Bob", "Johnson", "bob456", "password");
        assertTrue(ppf.saveUsers());
    }   
}