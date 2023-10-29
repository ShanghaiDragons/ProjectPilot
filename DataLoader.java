import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * The DataLoader class. Loads the data and acts as a medium between the database and the rest of the code
 */
public class DataLoader extends DataConstants {

	public static void main(String[] args) {
		Project p = getProjects().get(0);
		System.out.println(p.getTeam().get(1).getFirstName());
	  }
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
				  User user = new User(id, userName, firstName, lastName, password, permissionToAddTask, permissionToMoveTask, permissionToEditTask, permissionToEditColumns);
				  if (!users.contains(user))
				  	users.add(user);
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
				  String projectName = (String)projectJSON.get(PROJECT_NAME);
				  UUID id = UUID.fromString((String)projectJSON.get(PROJECT_ID));
				  ArrayList<User> team = new ArrayList<User>();
				  ArrayList<String> tempTeam = (ArrayList<String>)projectJSON.get(PROJECT_TEAM);
				  for(int j = 0; j < tempTeam.size() - 1; j++)
					for(int k = 0; k < getUsers().size(); k++)
						if (UUID.fromString(tempTeam.get(k)).equals(getUsers().get(k).getID()))
							team.add(getUsers().get(k));
				  ArrayList<Column> columns = new ArrayList<Column>();
				  ArrayList<String> tempColumns = (ArrayList<String>)projectJSON.get(PROJECT_COLUMN_IDS);
				  for(int j = 0; j < tempColumns.size() - 1; j++)
				  	for(int k = 0; k < tempColumns.get(j).length(); k++)
						if (UUID.fromString(tempColumns.get(k)).equals(getColumns().get(k).getID()))
							columns.add(getColumns().get(k));
				  Date startSprint = (Date)projectJSON.get(PROJECT_START_SPRINT);
				  Date endSprint = (Date)projectJSON.get(PROJECT_END_SPRINT);
				  ArrayList<Comment> comments = new ArrayList<Comment>();
				  ArrayList<String> tempComments = (ArrayList<String>)projectJSON.get(PROJECT_COMMENT_IDs);
				  for(int j = 0; j < tempComments.size() - 1; j++)
				  	for(int k = 0; k < tempComments.get(j).length(); k++)
						if (UUID.fromString(tempComments.get(k)).equals(getComments().get(k).getID()))
							comments.add(getComments().get(k));
				  projects.add(new Project(projectName, startSprint, endSprint, team, columns, comments));
			  }
			  
			  return projects;
			  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  
		  return null;
	  }

	  public static ArrayList<Column> getColumns() {
		  ArrayList<Column> columns = new ArrayList<Column>();
  
		try {
			  FileReader reader = new FileReader(PROJECT_FILE_NAME);
			  JSONParser parser = new JSONParser();	
			  JSONArray projectsJSON = (JSONArray)new JSONParser().parse(reader);
			
			  for(int i=0; i < projectsJSON.size(); i++) {
				  JSONObject projectJSON = (JSONObject)projectsJSON.get(i);
				  String projectName = (String)projectJSON.get(PROJECT_NAME);
				  UUID id = UUID.fromString((String)projectJSON.get(PROJECT_ID));
				  String columnName = (String)projectJSON.get(COLUMN_NAME);
				  ArrayList<Task> tasks = new ArrayList<Task>();
				  ArrayList<String> tempTasks = (ArrayList<String>)projectJSON.get(COLUMN_TASK_IDS);
				  for(int j = 0; j < tempTasks.size() - 1; j++)
					w
				  String sortType = (String)projectJSON.get(COLUMN_SORT_TYPE);
				  ArrayList<Comment> comments = new ArrayList<Comment>();
				  ArrayList<String> tempComments = (ArrayList<String>)projectJSON.get(COLUMN_COMMENT_IDS);
				  for(int j = 0; j < tempComments.size() - 1; j++)
					for(int k = 0; k < getComments().size(); k++)
						if (UUID.fromString(tempComments.get(k)).equals(getComments().get(k).getID()))
							comments.add(getComments().get(k));
				  columns.add(new Column(columnName, sortType, tasks, comments));
			  }
			  
			  return columns;
			  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  
		  return null;
	  }

	  public static ArrayList<Comment> getComments() {
		  ArrayList<Comment> comments = new ArrayList<Comment>();
  
		try {
			  FileReader reader = new FileReader(PROJECT_FILE_NAME);
			  JSONParser parser = new JSONParser();	
			  JSONArray projectsJSON = (JSONArray)new JSONParser().parse(reader);
			
			  for(int i=0; i < projectsJSON.size(); i++) {
				  JSONObject projectJSON = (JSONObject)projectsJSON.get(i);
				  String projectName = (String)projectJSON.get(PROJECT_NAME);
				  UUID id = UUID.fromString((String)projectJSON.get(PROJECT_ID));
				 
				  comments.add(new Comment(id, null, projectName));
			  }
			  
			  return comments;
			  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  
		  return null;
	  }

	  public static ArrayList<Task> getTasks() {
		  ArrayList<Task> tasks = new ArrayList<Task>();
  
		try {
			  FileReader reader = new FileReader(PROJECT_FILE_NAME);
			  JSONParser parser = new JSONParser();	
			  JSONArray projectsJSON = (JSONArray)new JSONParser().parse(reader);
			
			  for(int i=0; i < projectsJSON.size(); i++) {
				  JSONObject projectJSON = (JSONObject)projectsJSON.get(i);
				  String projectName = (String)projectJSON.get(PROJECT_NAME);
				  UUID id = UUID.fromString((String)projectJSON.get(PROJECT_ID));
				  
				  tasks.add(new Task(null, null, null, null, null));
			  }
			  
			  return projects;
			  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  
		  return null;
	  }
}