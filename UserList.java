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
        for (int i = 0; i < users.size(); i++)
            if (users.get(i).getUserName() == userName)
                return users.get(i);
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    // Other methods can be added to manipulate the users list if needed
    // For example, methods to add, remove, or update users
}

