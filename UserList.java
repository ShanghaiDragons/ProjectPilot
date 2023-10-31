import java.util.ArrayList;

public class UserList {
    private static UserList userListInstance;
    private ArrayList<User> users;

    /**
     * UserList constructor. Uses DataLoader to initialize the users
     * @author ctaks
     */
    private UserList() {
        users = DataLoader.getUsers();
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
        return users;
    }
    
    /**
    * @author theo v
    * Adds user to the userlist
    * @param userName
    * @return
    */
    public User addUser(User userName){
        if(userName!=null){
            return null;
        }
        users.add(userName);
        return userName;
    }
    /**
     * @author theo v 
     * Removes a user from the user list
     * @param userName
     */
    public User removeUser(User userName){
        if(userName!=null){
            return null;
        }
        users.remove(userName);
        return userName;
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

