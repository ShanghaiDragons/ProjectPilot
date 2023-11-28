package Testing;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import projectpilot_gui.src.main.java.model.*;

import java.util.ArrayList;

public class UserListTest {
    private UserList userList;
    private User user1, user2;

    @Before
    public void setUp() {
        userList = UserList.getInstance(); 
        userList.clear();
        user1 = new User("user1", "Lastname1", "username1", "password1");
        user2 = new User("user2", "Lastname2", "username2", "password2");
    }
    //Testing adding user
    @Test
    public void testAddUser() {
        assertTrue(userList.addUser(user1));
        assertEquals(1, userList.getUsers().size());
        assertTrue(userList.getUsers().contains(user1));
    }
    //Testing adding same user. Should not allow it to add duplicate users 
    @Test
    public void testAddSameUser(){
        assertTrue(userList.addUser(user1));
        assertFalse(userList.addUser(user1));
        assertEquals(1, userList.getUsers().size());
    }
    //testing removing a user in the userlist
    @Test
    public void testRemoveUser() {
        userList.addUser(user1);
        assertTrue(userList.removeUser(user1));
        assertFalse(userList.getUsers().contains(user1));
    }
    //testing removing a user not in the userlist. 
    @Test
    public void testRemoveNonExistingUser() {
        assertFalse(userList.removeUser(user1));
        assertEquals(0, userList.getUsers().size());
    }


    
}
