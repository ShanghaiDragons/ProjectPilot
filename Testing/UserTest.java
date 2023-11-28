package Testing;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import projectpilot_gui.src.main.java.model.*;

public class UserTest {
    User user = new User("First", "Name", "username", "password");
    
    // Test verifying correct login 
    @Test
    public void testCorrectLoginCredentials() {
        assertTrue(user.login("username", "password"));
    }

    //Test verifying wrong login
    @Test
    public void testWrongLoginCredentials(){
        assertFalse(user.login("username", "wrongpassword"));
        assertFalse(user.login("username", "wrongpassword"));
    }

    // Test Logging in with null or empty values
    @Test
    public void testLoginWithNullOrEmptyCredentials() {
        assertFalse(user.login(null, "password"));
        assertFalse(user.login("username", null));
        assertFalse(user.login(null, null));
        assertFalse(user.login("", "password"));
        assertFalse(user.login("username", ""));
        assertFalse(user.login("", ""));
    }
    // Test verifying correct password
    @Test
    public void testCorrectPasswordVerification() {
        assertTrue(user.verifyPassword("password"));
    }

    // Test verifying incorrect password
    @Test
    public void testIncorrectPasswordVerification() {
        assertFalse(user.verifyPassword("wrongpassword"));
    }

    // Test verifying null or empty password
    @Test
    public void testPasswordVerificationWithNullOrEmptyPassword() {
        assertFalse(user.verifyPassword(null));
        assertFalse(user.verifyPassword(""));
    }

}
