package Testing;
import sourceCode.*;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.time.LocalDate;

public class ProjectPilotFacadeTest {

    private ProjectPilotFacade projectPilotFacade;
    private User user;
    private Column sourceColumn;
    private Column destinationColumn;
    private Task task;

    @Before
    public void setUp() {
        projectPilotFacade = ProjectPilotFacade.getInstance();
        projectPilotFacade.createAccount("John", "Doe", "johndoe", "password");
        projectPilotFacade.login("johndoe", "password");
        user = projectPilotFacade.getUser();
        projectPilotFacade.addProject("Project1", LocalDate.now(), LocalDate.now().plusDays(7), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        projectPilotFacade.addColumn("Column1", "sortType", new ArrayList<>(), new ArrayList<>());
        projectPilotFacade.addColumn("Column2", "sortType", new ArrayList<>(), new ArrayList<>());
        sourceColumn = projectPilotFacade.getProjects().get(0).getColumns().get(0);
        destinationColumn = projectPilotFacade.getProjects().get(0).getColumns().get(1);
        projectPilotFacade.saveProjects();
    } 
    
    //testing creating a new account and when a duplicate account is created 
    @Test
    public void testCreateAccount() {
        assertTrue(projectPilotFacade.createAccount("John", "Doe", "johndoe", "password"));
        assertFalse(projectPilotFacade.createAccount("John", "Doe", "johndoe", "password")); 
    }

    //testing logging out, and trying to log out again
    @Test
    public void testLogout() {
        assertTrue(projectPilotFacade.logout());
        assertFalse(projectPilotFacade.logout());
    }
    //testing adding project 
    @Test
    public void testAddProject() {
        //correct parameters
        assertTrue(projectPilotFacade.addProject("Project2", LocalDate.now(), LocalDate.now().plusDays(7), new ArrayList<>(), new ArrayList<>(), new ArrayList<>())); 
        //testing adding a project that has a backward start/end sprint dates
        assertFalse(projectPilotFacade.addProject("Project3", LocalDate.now().plusDays(7),LocalDate.now(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        //testing adding a project with empty name value
        assertFalse(projectPilotFacade.addProject("", LocalDate.now(), LocalDate.now().plusDays(7), new ArrayList<>(), new ArrayList<>(), new ArrayList<>())); 
        //testing adding a project with null name value
        assertFalse(projectPilotFacade.addProject(null, LocalDate.now(), LocalDate.now().plusDays(7), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }
    //testing editing project scenarios
    @Test
    public void testEditProject() {
        assertTrue(projectPilotFacade.editProject(projectPilotFacade.getProject("Project1").getID().toString(), "Project2", LocalDate.now(), LocalDate.now().plusDays(14)));
        //editing non-existing project 
        assertFalse(projectPilotFacade.editProject("nonexistentID", "Project2", LocalDate.now(), LocalDate.now().plusDays(14))); 
    }
    //testing removing project scenarios
    @Test
    public void testRemoveProject() {
        //removing existing project 
        assertTrue(projectPilotFacade.removeProject(projectPilotFacade.getProjects().get(0).getID().toString()));
        //removing non existing project
        assertFalse(projectPilotFacade.removeProject("nonexistentID")); // Nonexistent project
    }
    //testing adding column scenarios
    @Test
    public void testAddColumn() {
        //
        assertTrue(projectPilotFacade.addColumn("Column1", "sortType", new ArrayList<>(), new ArrayList<>()));
    }
    //testing removing column scenarios
    @Test
    public void testRemoveColumn() {
        //removing existing column
        assertTrue(projectPilotFacade.removeColumn(sourceColumn.getID().toString()));
        //removing non existing column
        assertFalse(projectPilotFacade.removeColumn("nonexistentID")); 
    }
    //testing editing column  
    @Test
    public void testEditColumn() {
        assertTrue(projectPilotFacade.editColumn(sourceColumn.getID().toString(), "NewName", "newSortType"));
    }

    //testing moving tasks
    @Test
    public void testMoveTask() {
        projectPilotFacade.addTask(sourceColumn, "Task1", user, 1, "New", "Description", new ArrayList<>());
        task= projectPilotFacade.getTasks().get(0);
        assertTrue(projectPilotFacade.moveTask(sourceColumn,destinationColumn,task));
    }

    //testing loading project 
    @Test
    public void testLoadProject(){
        assertTrue(projectPilotFacade.loadProject("Project1"));
    }
    
    //testing loading non existent project 
    @Test
    public void testLoadNonExistingProject(){
        assertFalse(projectPilotFacade.loadProject("NonExistingProject"));
    }
    //testing saving project and checking to see if it gets saved as not null 
    @Test
    public void testSaveProject(){
        projectPilotFacade.addProject("Project2", LocalDate.now(), LocalDate.now().plusDays(7), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertTrue(projectPilotFacade.saveProjects());
        assertNotNull(projectPilotFacade.loadProject("Project2"));
    }
    
    //testing saving users 
    @Test
    public void testSaveUsers() {
        projectPilotFacade.createAccount("Alice", "Smith", "alice123", "password");
        projectPilotFacade.createAccount("Bob", "Johnson", "bob456", "password");
        assertTrue(projectPilotFacade.saveUsers());
        assertTrue(projectPilotFacade.login("alice123", "password"));
        assertTrue(projectPilotFacade.login("bob123", "password"));
    }   
}