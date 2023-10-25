import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * The DataLoader class. Loads the data and acts as a medium between the database and the rest of the code
 */
public class DataLoader extends DataConstants {

    /**
	 * Accesses and displays all users via JSON file reading and loading
	 * @author Duayne
	 * @return ArrayList object containing all registered users
	 */
    public static ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
  
		try {
			  FileReader reader = new FileReader(USER_FILE_NAME);
			  JSONParser parser = new JSONParser();	
			  JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
			  
			  for(int i=0; i < peopleJSON.size(); i++) {
				  JSONObject personJSON = (JSONObject)peopleJSON.get(i);
				  UUID id = UUID.fromString((String)personJSON.get(USER_ID));
				  String userName = (String)personJSON.get(USER_USER_NAME);
				  String firstName = (String)personJSON.get(USER_FIRST_NAME);
				  String lastName = (String)personJSON.get(USER_LAST_NAME);
				  String password = (String)personJSON.get(USER_PASSWORD);
				  boolean permissionToAddTask = (boolean)personJSON.get(USER_ADD_TASK);
				  boolean permissionToMoveTask = (boolean)personJSON.get(USER_MOVE_TASK);
				  boolean permissionToEditTask = (boolean)personJSON.get(USER_EDIT_TASK);
				  boolean permissionToEditColumns = (boolean)personJSON.get(USER_EDIT_COLUMN);
				  users.add(new User(id, userName, firstName, lastName, password, permissionToAddTask, permissionToMoveTask, permissionToEditTask, permissionToEditColumns));
			  }
			  
			  return users;
			  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  
		  return null;
	  }
  
	  /**
	   * Accesses and displays all projects via JSON file reading and loading
	   * @author Duayne
	   * @return ArrayList object containing all projects
	   */
	  public static ArrayList<Project> getProjects() {
		  ArrayList<Project> projects = new ArrayList<Project>();
  
		try {
			  FileReader reader = new FileReader(PROJECT_FILE_NAME);
			  JSONParser parser = new JSONParser();	
			  JSONArray projectsJSON = (JSONArray)new JSONParser().parse(reader);
			  
			  for(int i=0; i < projectsJSON.size(); i++) {
				  JSONObject projectJSON = (JSONObject)projectsJSON.get(i);
				  UUID id = UUID.fromString((String)projectJSON.get(PROJECT_ID));
				  String projectName = (String)projectJSON.get(PROJECT_NAME);
				  User user = (User)projectJSON.get(USER_USER_NAME);
  
				  projects.add(new Project(id, projectName, user));
			  }
			  
			  return projects;
			  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  
		  return null;
	  }
}