package Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;

import projectpilot_gui.src.main.java.model.*;

class DataLoaderTest {
	private DataLoader dataLoader = DataLoader.getInstance();
	private UserList userList = UserList.getInstance();
	private ProjectList projectList = ProjectList.getInstance();
	private ArrayList<Column> columnlist;
    private ArrayList<Comment> commentlist;
	private ArrayList<Task> tasks;
	private User user1, user2;
	private Project project1;
	private DataWriter dataWriter;
	
	@BeforeEach
	public void setup() {
		dataWriter = new DataWriter();
		userList.clear();
		projectList.clear();
		columnlist = new ArrayList<Column>();
		commentlist = new ArrayList<Comment>();
		tasks = new ArrayList<Task>();
		user1 = new User("Amy", "Smith", "aSmith24", "p");
		user2 = new User("Bobby", "Smith", "bSmith24", "p");
		project1 = new Project("project1", LocalDate.now(), LocalDate.now(), new ArrayList<User>(), user1, new ArrayList<User>(), new ArrayList<User>(), columnlist, commentlist);
		tasks.add(new Task("newTask", userList.getUsers().get(0), 1, "null", "desc", commentlist));
		columnlist.add(new Column("", "null", tasks, commentlist));
		dataWriter.saveUsers();
		dataWriter.saveProjects();
	}
	
	@Test
	void testGetUsersSize() {
		assertEquals(2, dataLoader.getUsers().size());
	}

	// @Test
	// void testGetProjectsSize() {
	// 	assertEquals(1, dataLoader.getProjects().size());
	// }

	// @Test
	// void testSaveandLoadZero() {
	// 	userList.clear();
	// 	projectList.clear();
	// 	assertTrue(dataWriter.saveProjects());
	// 	assertTrue(dataWriter.saveUsers());
	// 	assertEquals(0, dataLoader.getProjects().size());
	// 	assertEquals(0, dataLoader.getUsers().size());
	// }

	// @Test
	// void testSavingNullinConstructors() {
	// 	dataWriter = new DataWriter();
	// 	projectList.clear();
	// 	userList.clear();
	// 	Project testProject = new Project(null, null, null, null, null, null, null, null, null);
	// 	User testUser = new User(null, null, null, null);
	// 	projectList.addProject(testProject);
	// 	userList.addUser(testUser);
	// 	assertTrue(dataWriter.saveProjects() && !projectList.getProjects().get(0).getName().equals(null));
	// 	assertTrue(dataWriter.saveUsers() && !userList.getUsers().get(0).getUserName().equals(null));
	// }

	// @Test
	// void testSavingNull() {
	// 	dataWriter = new DataWriter();
	// 	userList.clear();
	// 	projectList.clear();
	// 	projectList.addProject(null);
	// 	userList.addUser(null);
	// 	assertTrue(dataWriter.saveUsers());
	// 	assertTrue(dataWriter.saveProjects());
	// }

	// @Test
	// void testLoadingNullinConstructors() {
	// 	dataWriter = new DataWriter();
	// 	userList.clear();
	// 	projectList.clear();
	// 	User testUser = new User(null, null, null, null);
	// 	Project testProject = new Project(null, null, null, null, null, null, null, null, null);
	// 	userList.addUser(testUser);
	// 	projectList.addProject(testProject);
	// 	dataWriter.saveUsers();
	// 	dataWriter.saveProjects();
	// 	assertTrue(dataLoader.getUsers().contains(testUser));
	// 	assertTrue(dataLoader.getProjects().contains(testProject));
	// }
}