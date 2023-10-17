import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 * The DataWriter class. Saves the user/project changes to JSON files.
 */
public class DataWriter {

 /**
  * 
  * @param usersList
  * @return
  */   
    public boolean saveUsers(ArrayList<User> usersList) {
        UserList users = UserList.getInstance();
        ArrayList<User> userList = users.getUsers();
        JSONArray jsonUsers = new JSONArray();
        
        for(int i=0; i < userList.size(); i++) {
            jsonUsers.add();
        }
    }


    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID, user.getID().toString());
    }
 /**
  * 
  * @param projectsList
  * @return
  */   
    public boolean saveProjects(ArrayList<Project> projectsList) {
        
        return false; 
    }
}
