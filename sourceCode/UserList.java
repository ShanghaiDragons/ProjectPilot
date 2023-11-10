package sourceCode;
import java.util.ArrayList;

public class UserList {
    private static UserList userListInstance;
    private ArrayList<User> users;
    private DataLoader dataLoader;

    /**
     * UserList constructor. Uses DataLoader to initialize the users
     * @author ctaks
     */
    private UserList() {
        dataLoader = DataLoader.getInstance();
        users = dataLoader.getUsers();
        if (users == null) {
            users = new ArrayList<User>();
        }
    }

    /**
     * Creates an instance of the UserList
     * @author theo
     * @return an instance of userListInstance
     */
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
            if (users.get(i).getUserName().equals(userName))
                return users.get(i);
        return null;
    }

    /**
     * Gets the current list of users
     * @author theo
     * @return the ArrayList<User> of users
     */
    public ArrayList<User> getUsers() {
        return this.users;
    }
    
    /**
    * @author theo v (edited by ctaks to return boolean instead of "User", also to check for duplicate names)
    * Adds user to the userlist
    * @param user the user to add
    * @return boolean determining success
    */
    public boolean addUser(User user){
        for (User member : users) {
            if (user.getUserName().equals(member.getUserName())) {
                return false;
            }
        }
        return users.add(user);
    }

    /**
     * @author theo v (edited by ctaks to return boolean instead of "User")
     * Removes a user from the user list
     * @param user the user to remove
     * @return boolean determining success
     */
    public boolean removeUser(User user){
        return users.remove(user);
    }

    /**
     * Saves the current users
     * @author ctaks
     * @return boolean determining success
     */
    public boolean saveUsers() {
        DataWriter dw = new DataWriter();
        return dw.saveUsers();
    }
}

