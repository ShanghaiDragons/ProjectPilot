import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.UUID;
/**
 * The DataWriter class. Saves the user/project changes to JSON files.
 */
public class DataWriter extends DataConstants {

 /**
  * Saves the list of users to the user JSON file.
  * @author Chris
  * @param usersList the list of users to save
  * @return boolean determining if the save was successful or not
  */   
    public static boolean saveUsers() {
        /* TODO: commented out for testing. Uncomment for final. -Chris
        UserList users = UserList.getInstance();
        ArrayList<User> userList = users.getUsers();
        */
        JSONArray jsonUsers = new JSONArray();

        // TEST USERS. TODO: remove when testing is done.
        UUID userID1 = UUID.randomUUID();
        User user1 = new User(userID1, "testUsername", "testFirstname", "testLastname", "testPassword", false, false, false, false);
        User user2 = new User(UUID.randomUUID(), "TestUsername2", "testFirstname2", "testLastName2", "testPassword2", false, false, false, false);
        
        jsonUsers.add(getUserJSON(user1));
        jsonUsers.add(getUserJSON(user2));
        /* TODO: commented out for testing. Uncomment for final. -Chris 
        // Creating JSON objects
        for(int i=0; i < userList.size(); i++) {
            jsonUsers.add(getUserJSON(userList.get(i)));
        }
        */

        // Write to JSON file
        // TODO: hardcoded the filename for testing. Change for final version. -Chris
        try (FileWriter file = new FileWriter("json/user_test_write.json")) {
            file.write(jsonUsers.toJSONString());
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Creates a JSONObject for a user.
     * @author Chris
     * @param user The name of the user
     * @return JSONObject of the user's ID, first/last/user name.
     */
    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID, user.getID().toString());
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_USER_NAME, user.getUserName());

        return userDetails;
    }

 /**
  * Saves the projects to the project JSON file.
  * @author Chris
  * @param projectsList the list of projects to save
  * @return boolean determining if the save was successful or not
  */   
    public static boolean saveProjects() {
        /* TODO: commented out for testing. Uncomment for final. -Chris
        ProjectList projects = ProjectList.getInstance();
        ArrayList<Project> projectList = projects.getProjects();
        */
        JSONArray jsonProjects = new JSONArray();
        
        // TEST Project TODO: Remove when testing is done
        User testUser = new User(UUID.randomUUID(), "testUsername", "testFirstname", "testLastname", "testPassword", false, false, false, false);
        Project project1 = new Project(UUID.randomUUID(), "Test Project 1", testUser);
        Project project2 = new Project(UUID.randomUUID(), "Test project 2", testUser);
        jsonProjects.add(getProjectJSON(project1));
        jsonProjects.add(getProjectJSON(project2));

        /* TODO: commented out for testing. Use this version in final. -Chris
        // Creating JSON objects
        for(int i=0; i < projectList.size(); i++) {
            jsonProjects.add(getProjectJSON(projectList.get(i)));
        }
        */

        // Write to JSON file
        // TODO: hardcoded the filename for testing. change file name for final version. -Chris
        try (FileWriter file = new FileWriter("json/project_test_write.json")) {
            file.write(jsonProjects.toJSONString());
            file.flush();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Create a JSONObject a project
     * @author Chris
     * @param project the project name
     * @return JSONObject of the project's ID and name.
     */
    public static JSONObject getProjectJSON(Project project) {
        JSONObject projectDetails = new JSONObject();
        projectDetails.put(PROJECT_ID, project.getID().toString());
        projectDetails.put(PROJECT_NAME, project.getName());

        return projectDetails;
    }

    /**
     * Method for testing.
     * @author Chris
     public static void main(String[] args) {
         if (saveUsers())
         System.out.println("Users SAVED");
         else
         System.out.println("Users FAILED TO SAVE");
         
         if(saveProjects())
         System.out.println("Projects SAVED");
         else
         System.out.println("Projects FAILED TO SAVE");
        }
        */
}
