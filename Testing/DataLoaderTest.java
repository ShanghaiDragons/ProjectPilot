import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		Users.getInstance().getUsers().clear();
		DataWriter.saveUsers();
	}
	
	
	@Test
	void testGetUsersSize() {
		userList = DataLoader.getUsers();
		assertEquals(2, userList.size());
	}

	@Test
	void testGetUsersSizeZero() {
		Users.getInstance().getUsers().clear();
		DataWriter.saveUsers();
		assertEquals(0, userList.size());
	}
	
	@Test
	void testGetUserFirstUserName() {
		userList = DataLoader.getUsers();
		assertEquals("asmith", userList.get(0).getUserName());
	}
}