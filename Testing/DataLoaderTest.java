package Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;
import sourceCode.*;

class DataLoaderTest {
	private UserList users = UserList.getInstance();
	private ProjectList projects = ProjectList.getInstance();
	private DataLoader dataLoader = DataLoader.getInstance();
	private ArrayList<User> userList = users.getUsers();
	private ArrayList<Project> projectList = projects.getProjects();
	private ArrayList<Column> columnlist;
    private ArrayList<Comment> commentlist;
	private ArrayList<Task> tasks;
	private DataWriter dataWriter = new DataWriter();
	
	@BeforeEach
	public void setup() {
		userList.clear();
		projectList.clear();
		columnlist = new ArrayList<Column>();
		commentlist = new ArrayList<Comment>();
		tasks = new ArrayList<Task>();
		userList.add(new User("Amy", "Smith", "aSmith24", "p"));
		userList.add(new User("Bobby", "Smith", "bSmith24", "p"));
		projectList.add(new Project("Project1", LocalDate.parse("2023-11-07"), LocalDate.parse("2023-11-08"), userList, userList.get(0), new ArrayList<User>(), new ArrayList<User>(), columnlist, commentlist));
		tasks.add(new Task("newTask", userList.get(0), 1, "null", "desc", commentlist));
		columnlist.add(new Column("", "null", tasks, commentlist));
		dataWriter.saveUsers();
		dataWriter.saveProjects();
	}
	
	@AfterEach
	public void tearDown() {
		userList.clear();
		projectList.clear();
		dataWriter.saveUsers();
		dataWriter.saveProjects();
	}
	
	@Test
	void testGetUsersSize() {
		assertEquals(2, dataLoader.getUsers().size());
	}

	@Test
	void testGetProjectsSize() {
		assertEquals(1, dataLoader.getProjects().size());
	}

	@Test
	void testSaveandLoadZero() {
		userList.clear();
		projectList.clear();
		assertTrue(dataWriter.saveProjects());
		assertTrue(dataWriter.saveUsers());
		assertEquals(0, dataLoader.getProjects().size());
		assertEquals(0, dataLoader.getUsers().size());
	}

	@Test
	void testSavingNullinConstructors() {
		dataWriter = new DataWriter();
		projectList.clear();
		userList.clear();
		Project testProject = new Project(null, null, null, null, null, null, null, null, null);
		User testUser = new User(null, null, null, null);
		projectList.add(testProject);
		userList.add(testUser);
		assertTrue(dataWriter.saveProjects() && !projectList.get(0).getName().equals(null));
		assertTrue(dataWriter.saveUsers() && !userList.get(0).getUserName().equals(null));
	}

	@Test
	void testSavingNull() {
		dataWriter = new DataWriter();
		userList.clear();
		projectList.clear();
		projectList.add(null);
		userList.add(null);
		assertTrue(dataWriter.saveUsers());
		assertTrue(dataWriter.saveProjects());
	}

	@Test
	void testLoadingNullinConstructors() {
		dataWriter = new DataWriter();
		userList.clear();
		projectList.clear();
		User testUser = new User(null, null, null, null);
		Project testProject = new Project(null, null, null, null, null, null, null, null, null);
		userList.add(testUser);
		projectList.add(testProject);
		dataWriter.saveUsers();
		dataWriter.saveProjects();
		assertTrue(dataLoader.getUsers().contains(testUser));
		assertTrue(dataLoader.getProjects().contains(testProject));
	}
}