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
 * Retrieves the user with a search by username
 * @author Duayne
 * @param userName string that represents the user's username
 * @return User object that represents the user
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

    
    //methods to add, remove, or update users
}

