import java.util.ArrayList;

public class UserList {
    private static UserList userListInstance;
    private ArrayList<User> users;
    private User user;

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
        return user;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    
    //methods to add, remove, or update users
}

