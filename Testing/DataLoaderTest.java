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
		userList.add(new User("Amy", "Smith", "aSmith24", "p", false, false, false, false));
		userList.add(new User("Bobby", "Smith", "bSmith24", "p", false, false, false, false));
		projectList.add(new Project("Project1", LocalDate.parse("2023-11-07"), LocalDate.parse("2023-11-08"), userList, columnlist, commentlist));
		tasks.add(new Task("newTask", userList.get(0), 1, "null", "desc", commentlist));
		columnlist.add(new Column("", "null", tasks, commentlist));
		dataWriter.saveUsers();
		dataWriter.saveProjects();
	}
	
	@After
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
	void testGetUsersSizeZero() {
		userList.clear();
		dataWriter.saveUsers();
		assertEquals(0, dataLoader.getUsers().size());
	}

	@Test
	void testGetProjectsSize() {
		assertEquals(1, dataLoader.getProjects().size());
	}

	@Test
	void testGetProjectsSizeZero() {
		projectList.clear();
		dataWriter.saveProjects();
		assertEquals(0, dataLoader.getProjects().size());
	}
	
}

// add a check for no file name given
// saving an empty string