import java.util.ArrayList;

public class UserList {
    private static UserList userListInstance;
    private ArrayList<User> users;

    private UserList() {
        users = new ArrayList<>();
    }

    public static UserList getInstance() {
        if (userListInstance == null) {
            userListInstance = new UserList();
        }
        return userListInstance;
    }
/**
 * 
 * @param userName
 * @return
 */
    public User getUser(String userName) {
        return null;
    }

    public ArrayList<User> getUsers() {
        return null;
    }

    // Other methods can be added to manipulate the users list if needed
    // For example, methods to add, remove, or update users
}

