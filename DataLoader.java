import java.io.FileReader;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.text.SimpleDateFormat;

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
					if (projectName != null) {
						UUID id = UUID.fromString((String)projectJSON.get(PROJECT_ID));
						ArrayList<User> team = new ArrayList<User>();
						ArrayList<String> tempTeam = (ArrayList<String>)projectJSON.get(PROJECT_TEAM);
						for(int j = 0; j < tempTeam.size(); j++)
							if (UUID.fromString(tempTeam.get(j)).equals(getUsers().get(j).getID()))
								team.add(getUsers().get(j));
						ArrayList<Column> columns = new ArrayList<Column>();
						ArrayList<String> tempColumns = (ArrayList<String>)projectJSON.get(PROJECT_COLUMN_IDS);
						for(int j = 0; j < getColumns().size(); j++)
							for(int k = 0; k < tempColumns.size(); k++)
								if (UUID.fromString(tempColumns.get(k)).equals(getColumns().get(j).getID()))
									columns.add(getColumns().get(j));
						String start = (String)projectJSON.get(PROJECT_START_SPRINT);
						LocalDate startSprint = LocalDate.parse(start);
						String end = (String)projectJSON.get(PROJECT_END_SPRINT);
						LocalDate endSprint = LocalDate.parse(end);
						ArrayList<Comment> comments = new ArrayList<Comment>();
						ArrayList<String> tempComments = (ArrayList<String>)projectJSON.get(PROJECT_COMMENT_IDs);
						for(int j = 0; j < tempComments.size(); j++)
							if (UUID.fromString(tempComments.get(j)).equals(getComments().get(j).getID()))
								comments.add(getComments().get(j));
						projects.add(new Project(id, projectName, startSprint, endSprint, team, columns, comments));
					}
			  }
			  
			  return projects;
			  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  
		  return null;
	  }

	/**
	 * Accesses and displays all Columns via JSON file reading and loading
	 * @author Duayne
	 * @return ArrayList object containing all project's columns
	 */
	  public static ArrayList<Column> getColumns() {
		  ArrayList<Column> columns = new ArrayList<Column>();
  
		try {
			  FileReader reader = new FileReader(PROJECT_FILE_NAME);
			  JSONParser parser = new JSONParser();	
			  JSONArray projectsJSON = (JSONArray)new JSONParser().parse(reader);
			
			
			  for(int i=0; i < projectsJSON.size(); i++) {
				  JSONObject projectJSON = (JSONObject)projectsJSON.get(i);
				  String name = (String)projectJSON.get(COLUMN_NAME);
					if (name != null) {
						UUID id = UUID.fromString((String)projectJSON.get(COLUMN_ID));
						ArrayList<Task> tasks = new ArrayList<Task>();
						ArrayList<String> tempTasks = (ArrayList<String>)projectJSON.get(COLUMN_TASK_IDS);
						for (int k = 0; k < tempTasks.size(); k++)
							for(int j = 0; j < getTasks().size(); j++)
								if (UUID.fromString(tempTasks.get(k)).equals((getTasks().get(j).getID())))
									tasks.add(getTasks().get(j));
						String sortType = (String)projectJSON.get(COLUMN_SORT_TYPE);
						ArrayList<Comment> comments = new ArrayList<Comment>();
						ArrayList<String> tempComments = (ArrayList<String>)projectJSON.get(COLUMN_COMMENT_IDS);
						for(int j = 0; j < tempComments.size(); j++)
							if (UUID.fromString(tempComments.get(j)).equals(getComments().get(j).getID()))
								comments.add(getComments().get(j));
						columns.add(new Column(id, name, sortType, tasks, comments));
					}
			  }
			  
			  return columns;
			  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  
		  return null;
	  }

	/**
	 * Accesses and displays all comments via JSON file reading and loading
	 * @author Duayne
	 * @return ArrayList object containing all comments
	 */
	  public static ArrayList<Comment> getComments() {
		  ArrayList<Comment> comments = new ArrayList<Comment>();
  
		try {
			  FileReader reader = new FileReader(PROJECT_FILE_NAME);
			  JSONParser parser = new JSONParser();	
			  JSONArray projectsJSON = (JSONArray)new JSONParser().parse(reader);
			
			  for(int i=0; i < projectsJSON.size(); i++) {
				  JSONObject projectJSON = (JSONObject)projectsJSON.get(i);
				  String name = (String)projectJSON.get(COMMENT_MESSAGE);
				  if (name != null) {
					UUID userId = UUID.fromString((String)projectJSON.get(COMMENT_USER_ID));
					User user = null;
					for (User x : getUsers()) {
						if (x.getID().equals(userId))
							user = x;
					}
					LocalDateTime time = LocalDateTime.now();
					String message = (String)projectJSON.get(COMMENT_MESSAGE);
					ArrayList<Comment> commentList = new ArrayList<Comment>();
						ArrayList<String> tempComments = (ArrayList<String>)projectJSON.get(COMMENT_THREAD_IDs);
						for(int j = 0; j < tempComments.size(); j++)
							if (UUID.fromString(tempComments.get(j)).equals(getComments().get(j).getID()))
								comments.add(getComments().get(j));

				  comments.add(new Comment(userId, user, time, message, commentList));
			  }
			}
			  
			  return comments;
			  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  
		  return null;
	  }

	/**
	 * Accesses and displays all tasks via JSON file reading and loading
	 * @author Duayne
	 * @return ArrayList object containing all column's tasks
	 */
	  public static ArrayList<Task> getTasks() {
		  ArrayList<Task> tasks = new ArrayList<Task>();
  
		try {
			  FileReader reader = new FileReader(PROJECT_FILE_NAME);
			  JSONParser parser = new JSONParser();	
			  JSONArray projectsJSON = (JSONArray)new JSONParser().parse(reader);
			
			  for(int i=0; i < projectsJSON.size(); i++) {
				  JSONObject projectJSON = (JSONObject)projectsJSON.get(i);
				  String name = (String)projectJSON.get(TASK_NAME);
					if (name != null) {
						UUID id = UUID.fromString((String)projectJSON.get(TASK_ID));
						UUID assigneeId = UUID.fromString((String)projectJSON.get(TASK_ASSIGNEE));
						User assignee = null;
						for (User user : getUsers()) {
							if (user.getID().equals(assigneeId))
								assignee = user;
						}
						String num = (String)projectJSON.get(TASK_PRIORITY);
						int priority = Integer.parseInt(num);
						String status = (String)projectJSON.get(TASK_STATUS);
						String description = (String)projectJSON.get(TASK_DESCRIPTION);
						ArrayList<Comment> comments = new ArrayList<Comment>();
						ArrayList<String> tempComments = (ArrayList<String>)projectJSON.get(TASK_COMMENT_IDS);
						for(int j = 0; j < tempComments.size(); j++)
							if (UUID.fromString(tempComments.get(j)).equals(getComments().get(j).getID()))
								comments.add(getComments().get(j));
						// boolean isGeneral = (boolean)projectJSON.get(TASK_GENERAL);
						// boolean isNewFeature = (boolean)projectJSON.get(TASK_NEW_FEATURE);
						// boolean isBug = (boolean)projectJSON.get(TASK_BUG);
						Task task = null;
					//   TODO: Bug cannot be resolved to a type for some weird reason...
					//   if(isBug)
					// 	task = new Bug(id, name, assignee, priority, status, description, comments);
						// if(isGeneral)
						task = new GeneralTask(id, name, assignee, priority, status, description, comments);
						// if(isNewFeature)
						// 	task = new NewFeature(id, name, assignee, priority, status, description, comments);
						tasks.add(task);
					}
			  }
			  
			  return tasks;
			  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  
		  return null;
	  }

	/**
	 * Accesses and displays the task history via JSON file reading and loading
	 * @author Duayne
	 * @return ArrayList object containing a task's history
	 */
	  public static ArrayList<String> getTaskHistory() {
		ArrayList<String> taskHistory = new ArrayList<String>();
  
		try {
			  FileReader reader = new FileReader(PROJECT_FILE_NAME);
			  JSONParser parser = new JSONParser();	
			  JSONArray projectsJSON = (JSONArray)new JSONParser().parse(reader);
			
			  for(int i=0; i < projectsJSON.size(); i++) {
				  JSONObject projectJSON = (JSONObject)projectsJSON.get(i);
				  LocalDateTime creationDate = (LocalDateTime)projectJSON.get(TASK_HISTORY_CREATION_DATE);
					if (creationDate != null) {
						UUID id = UUID.fromString((String)projectJSON.get(TASK_HISTORY_ID));
						UUID taskID = UUID.fromString((String)projectJSON.get(TASK_HISTORY_TASK_ID));
						ArrayList<String> nameChanges = new ArrayList<String>();
						ArrayList<String> tempNameChanges = (ArrayList<String>)projectJSON.get(TASK_HISTORY_NAME_CHANGES);
						for(int j = 0; j < tempNameChanges.size() - 1; j++)
						nameChanges.add(tempNameChanges.get(j));
						ArrayList<String> descriptionChanges = new ArrayList<String>();
						ArrayList<String> tempDescriptionChanges = (ArrayList<String>)projectJSON.get(TASK_HISTORY_DESCRIPTION_CHANGES);
						for(int j = 0; j < tempDescriptionChanges.size() - 1; j++)
						descriptionChanges.add(tempDescriptionChanges.get(j));
						ArrayList<String> moveChanges = new ArrayList<String>();
						ArrayList<String> tempMoveChanges = (ArrayList<String>)projectJSON.get(TASK_HISTORY_MOVE_CHANGES);
						for(int j = 0; j < tempMoveChanges.size() - 1; j++)
						moveChanges.add(tempMoveChanges.get(j));
						ArrayList<String> assigneeChanges = new ArrayList<String>();
						ArrayList<String> tempAssigneeChanges = (ArrayList<String>)projectJSON.get(TASK_HISTORY_ASSIGNEE_CHANGES);
						for(int j = 0; j < tempAssigneeChanges.size() - 1; j++)
						assigneeChanges.add(tempAssigneeChanges.get(j));
						ArrayList<String> priorityChanges = new ArrayList<String>();
						ArrayList<String> tempPriorityChanges = (ArrayList<String>)projectJSON.get(TASK_HISTORY_PRIORITY_CHANGES);
						for(int j = 0; j < tempDescriptionChanges.size() - 1; j++)
						priorityChanges.add(tempDescriptionChanges.get(j));
						ArrayList<String> statusChanges = new ArrayList<String>();
						ArrayList<String> tempStatusChanges = (ArrayList<String>)projectJSON.get(TASK_HISTORY_PRIORITY_CHANGES);
						for(int j = 0; j < tempStatusChanges.size() - 1; j++)
						statusChanges.add(tempStatusChanges.get(j));
						TaskHistory taskString = new TaskHistory(id, taskID, creationDate, tempNameChanges, tempDescriptionChanges, tempMoveChanges, tempAssigneeChanges, tempPriorityChanges, tempStatusChanges);
						taskHistory.add(taskString.toString());
					}
			  }
			  
			  return taskHistory;
			  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  
		  return null;
	  }
	}