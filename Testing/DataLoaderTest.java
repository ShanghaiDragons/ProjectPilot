package Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sourceCode.*;

class DataLoaderTest {
	private UserList users = UserList.getInstance();
	private ArrayList<User> userList = users.getUsers();
	
	@BeforeEach
	public void setup() {
		userList.clear();
		userList.add(new User("Amy", "Smith", "aSmith24", "p", false, false, false, false));
		userList.add(new User("Bobby", "Smith", "bSmith24", "p", false, false, false, false));
		DataWriter dw = new DataWriter();
		dw.saveUsers();
	}
	
	@AfterEach
	public void tearDown() {
		UserList.getInstance().getUsers().clear();
		DataWriter dw = new DataWriter();
		dw.saveUsers();
		}
	
	
	@Test
	void testGetUsersSize() {
		userList = UserList.getInstance().getUsers();
		assertEquals(2, userList.size());
	}

	@Test
	void testGetUsersSizeZero() {
		UserList.getInstance().getUsers().clear();
		DataWriter dw = new DataWriter();
		dw.saveUsers();
		assertEquals(0, userList.size());
	}
	
	@Test
	void testGetUserName() {
		assertEquals("aSmith24", UserList.getInstance().getUser("aSmith24").getUserName());
	}

	@Test
	void testGetUserFirstName() {
		userList = UserList.getInstance().getUsers();
		assertEquals("aSmith24", userList.get(0).getUserName());
	}
}