import java.io.FileReader;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * The DataLoader class. Loads the data and acts as a medium between the database and the rest of the code
 */
public class DataLoader extends DataConstants {
	private static DataLoader dataLoaderInstance;
	private JSONArray userFile;
	private ArrayList<User> users;
	private JSONArray projectFile;
	private ArrayList<JSONObject> jsonProjects;
	private ArrayList<JSONObject> jsonColumns;
	private ArrayList<JSONObject> jsonTasks;
	private ArrayList<JSONObject> jsonTaskHistories;
	private ArrayList<JSONObject> jsonComments;

	/**
	 * DataLoader constructor. Populates the class variables once so that the json files are only have to be loaded once.
	 * @author ctaks
	 */
	public DataLoader() {
		userFile = new JSONArray();
		projectFile = new JSONArray();
		this.users = new ArrayList<User>();
		this.jsonProjects = new ArrayList<JSONObject>();
		this.jsonColumns = new ArrayList<JSONObject>();
		this.jsonTasks = new ArrayList<JSONObject>();
		this.jsonTaskHistories = new ArrayList<JSONObject>();
		this.jsonComments = new ArrayList<JSONObject>();
		setUsers();
		setProjectObjects();
	}

	public static DataLoader getInstance() {
		if (dataLoaderInstance == null) {
			dataLoaderInstance = new DataLoader();
		}
		return dataLoaderInstance;
	}

    /**
	 * Accesses and displays all users via JSON file reading and loading
	 * @author Duayne (edited by ctaks)
	 * @return ArrayList<User> of all users from the JSON file
	 */
    public boolean setUsers() {
		try {
			// read the user file
			this.userFile = (JSONArray)new JSONParser().parse(new FileReader(USER_FILE_NAME));
			  
			// Interates over the userFile and adds users to an ArrayList<User>
			if (userFile != null && userFile.size() > 0) {
				for(int i=0; i < userFile.size(); i++) {
					JSONObject userObject = (JSONObject)userFile.get(i);
					UUID id = UUID.fromString((String)userObject.get(USER_ID));
					String userName = (String)userObject.get(USER_USER_NAME);
					String firstName = (String)userObject.get(USER_FIRST_NAME);
					String lastName = (String)userObject.get(USER_LAST_NAME);
					String password = (String)userObject.get(USER_PASSWORD);
					boolean permissionToAddTask = (boolean)userObject.get(USER_ADD_TASK);
					boolean permissionToMoveTask = (boolean)userObject.get(USER_MOVE_TASK);
					boolean permissionToEditTask = (boolean)userObject.get(USER_EDIT_TASK);
					boolean permissionToEditColumns = (boolean)userObject.get(USER_EDIT_COLUMN);
					User user = new User(id, userName, firstName, lastName, password, permissionToAddTask, permissionToMoveTask, permissionToEditTask, permissionToEditColumns);
					if (!this.users.contains(user)) {
						this.users.add(user);
						//this.userFile.remove(i);
					}
				}
				return true;
			}	
			  
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (users == null || users.size() < 1) {
			this.users = new ArrayList<User>();
		}
		return false;
	}
	
	/**
	 * Getter for the list of users
	 * @author ctaks
	 * @return ArrayList<User> of the users loaded from json
	 */
	public ArrayList<User> getUsers() {
		return this.users;
	}

	/**
	 * Read the JSON file and loads all the data from it into respective ArrayList<JSONObject>s
	 * @author ctaks
	 * @return boolean determining success
	 */
	public boolean setProjectObjects() {
		try {
			projectFile = (JSONArray)new JSONParser().parse(new FileReader(PROJECT_FILE_NAME));

			if (projectFile != null && projectFile.size() > 0) {
				for(int i = 0; i < projectFile.size(); i++) {
					JSONObject JObj = (JSONObject)projectFile.get(i);
					// Projects
					String projectName = (String)JObj.get(PROJECT_NAME);
					if (projectName != null) {
						jsonProjects.add(JObj);
					}
					// Columns
					else if(projectName == null) {
						String columnName = (String)JObj.get(COLUMN_NAME);
						if (columnName != null) {
							jsonColumns.add(JObj);
						}
						// Tasks
						else if (columnName == null) {
							String taskName = (String)JObj.get(TASK_NAME);
							if (taskName != null) {
								jsonTasks.add(JObj);
							}
							// TaskHistories
							else if (taskName == null) {
								String taskHistoryID = (String)JObj.get(TASK_HISTORY_ID);
								if (taskHistoryID != null) {
									jsonTaskHistories.add(JObj);
								}
								// Comments
								else if (taskHistoryID == null) {
									String message = (String)JObj.get(COMMENT_MESSAGE);
									if (message != null) {
										jsonComments.add(JObj);
									}
								}
							}
						}
					}
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
  
	/**
	* Accesses and displays all projects via JSON file reading and loading
	* @author Duayne (rewritten by ctaks)
	* @return ArrayList object containing all projects
	*/
	public ArrayList<Project> getProjects() {
		ArrayList<Project> projectList = new ArrayList<Project>();
		try {	
			for(JSONObject jProject : jsonProjects) {
				String projectName = (String)jProject.get(PROJECT_NAME);
				if (projectName != null) {
					UUID id = UUID.fromString((String)jProject.get(PROJECT_ID));
					LocalDate startSprint = LocalDate.parse((String)jProject.get(PROJECT_START_SPRINT));
					LocalDate endSprint = LocalDate.parse((String)jProject.get(PROJECT_END_SPRINT));
					// Teams
					ArrayList<User> team = new ArrayList<User>();
					ArrayList<String> jsonTeam = (ArrayList<String>)jProject.get(PROJECT_TEAM);
					for(String jTeamID : jsonTeam) {
						for (User user : this.users) {
							if (user.getID().equals(UUID.fromString(jTeamID))) {
								team.add(user);
							}
						}
					}
					// Columns
					ArrayList<Column> columns = new ArrayList<Column>();
					ArrayList<String> projectColumnIDs = (ArrayList<String>)jProject.get(PROJECT_COLUMN_IDS);
					for (String pColID : projectColumnIDs) {
						for (JSONObject jcolumn : this.jsonColumns) {
							if (UUID.fromString(pColID).equals(UUID.fromString((String)jcolumn.get(COLUMN_ID)))) {
								columns.add(makeColumn(jcolumn));
							}
						}
					}
					// Comments
					ArrayList<Comment> comments = new ArrayList<Comment>();
					ArrayList<String> projectCommentIDs = (ArrayList<String>)jProject.get(PROJECT_COMMENT_IDs);
					for (String pComID : projectCommentIDs) {
						for (JSONObject jComment : this.jsonComments) {
							if (UUID.fromString(pComID).equals(UUID.fromString((String)jComment.get(COMMENT_ID)))) {
								comments.add(makeComment(jComment));
							}
						}
					}
					projectList.add(new Project(id, projectName, startSprint, endSprint, team, columns, comments));
				}
			}
			return projectList;
			  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Loads a column's data by passing in its json data
	 * @author ctaks
	 * @param jcolumn json data to be turned into a column
	 * @return Column column of the passed in json data
	 */
	public Column makeColumn(JSONObject jcolumn) {
		String name = (String)jcolumn.get(COLUMN_NAME);
		if (name != null) {
			UUID id = UUID.fromString((String)jcolumn.get(COLUMN_ID));
			String sortType = (String)jcolumn.get(COLUMN_SORT_TYPE);
			// tasks
			ArrayList<Task> tasks = new ArrayList<Task>();
			ArrayList<String> columnTaskIDs = (ArrayList<String>)jcolumn.get(COLUMN_TASK_IDS);
			for (String cTaskID : columnTaskIDs) {
				for (JSONObject jtask : jsonTasks) {
					if (UUID.fromString(cTaskID).equals(UUID.fromString((String)jtask.get(TASK_ID)))) {
						tasks.add(makeTask(jtask));
					}
				}
			}
			// comments
			ArrayList<Comment> comments = new ArrayList<Comment>();
			ArrayList<String> columnCommentIDs = (ArrayList<String>)jcolumn.get(COLUMN_COMMENT_IDS);
			for (String cComID : columnCommentIDs) {
				for (JSONObject jComment : jsonComments) {
					if (UUID.fromString(cComID).equals(UUID.fromString((String)jComment.get(COMMENT_ID)))) {
						comments.add(makeComment(jComment));
					}
				}
			}
			return new Column(id, name, sortType, tasks, comments);
		} else {
			// if, for some reason, a column's JSONObject was not passed in:
			return new Column("error", "default", new ArrayList<Task>(), new ArrayList<Comment>());
		}
	}

	/**
	 * 
	 * @param jtask
	 * @return
	 */
	public Task makeTask(JSONObject jtask) {
		String name = (String)jtask.get(TASK_NAME);
		if (name != null) {
			UUID id = UUID.fromString((String)jtask.get(TASK_ID));
			int priority = Integer.parseInt((String)jtask.get(TASK_PRIORITY));
			String status = (String)jtask.get(TASK_STATUS);
			String description = (String)jtask.get(TASK_DESCRIPTION);
			// assignee
			User assignee = null;
			UUID assigneeID = UUID.fromString((String)jtask.get(TASK_ASSIGNEE));
			for (User user : users) {
				if (user.getID().equals(assigneeID)) {
					assignee = user;
				}
			}
			// taskHistory
			TaskHistory taskHistory = null;
			UUID taskHistoryID = (UUID.fromString((String)jtask.get(TASK_TASK_HISTORY_ID)));
			for (JSONObject jTaskHistory : jsonTaskHistories) {
				if (UUID.fromString((String)jTaskHistory.get(TASK_HISTORY_ID)).equals(taskHistoryID)) {
					taskHistory = makeTaskHistory(jTaskHistory);
				}
			}
			// comments
			ArrayList<Comment> comments = new ArrayList<Comment>();
			ArrayList<String> taskCommentIDs = (ArrayList<String>)jtask.get(TASK_COMMENT_IDS);
			for (String tComID : taskCommentIDs) {
				for (JSONObject jComment : jsonComments) {
					if (UUID.fromString(tComID).equals(UUID.fromString((String)jComment.get(COMMENT_ID)))) {
						comments.add(makeComment(jComment));
					}
				}
			}
			return new Task(id, name, assignee, priority, status, description, taskHistory, comments);
		} else {
			// if, for some reason, a task's JSONObject was not passed in:
			return new Task("error", users.get(0), 1, "error", "error", new ArrayList<Comment>());
		}
	}

	/**
	 * 
	 * @param jTaskHistory
	 * @return
	 */
	public TaskHistory makeTaskHistory(JSONObject jTaskHistory) {
		String historyID = (String)jTaskHistory.get(TASK_HISTORY_ID);
		if (historyID != null) {
			UUID id = UUID.fromString(historyID);
			UUID taskID = UUID.fromString((String)jTaskHistory.get(TASK_HISTORY_TASK_ID));
			LocalDateTime creationDate = LocalDateTime.parse((String)jTaskHistory.get(TASK_HISTORY_CREATION_DATE));
			ArrayList<String> nameChanges = (ArrayList<String>)jTaskHistory.get(TASK_HISTORY_NAME_CHANGES);
			ArrayList<String> descriptionChanges = (ArrayList<String>)jTaskHistory.get(TASK_HISTORY_DESCRIPTION_CHANGES);
			ArrayList<String> moveChanges = (ArrayList<String>)jTaskHistory.get(TASK_HISTORY_MOVE_CHANGES);
			ArrayList<String> assigneeChanges = (ArrayList<String>)jTaskHistory.get(TASK_HISTORY_ASSIGNEE_CHANGES);
			ArrayList<String> priorityChanges = (ArrayList<String>)jTaskHistory.get(TASK_HISTORY_PRIORITY_CHANGES);
			ArrayList<String> statusChanges = (ArrayList<String>)jTaskHistory.get(TASK_HISTORY_STATUS_CHANGES);

			return new TaskHistory(id, taskID, creationDate, nameChanges, descriptionChanges, moveChanges, assigneeChanges, priorityChanges, statusChanges);
		} else {
			// if, for some reason, a TaskHistory JSONObject was not passed in, it returns null. A new TaskHistory will be created in the Task constructor
			return null;
		}
	}

	/**
	 * 
	 * @param jComment
	 * @return
	 */
	public Comment makeComment(JSONObject jComment) {
		String message = (String)jComment.get(COMMENT_MESSAGE);
		if (message != null) {
			UUID id = UUID.fromString((String)jComment.get(COMMENT_ID));
			// User
			User commenter = null;
			UUID userID = UUID.fromString((String)jComment.get(COMMENT_USER_ID));
			for (User user : users) {
				if (user.getID().equals(userID)) {
					commenter = user;
				}
			}
			LocalDateTime date = LocalDateTime.parse((String)jComment.get(COMMENT_DATE));
			// Threaded comments (otherwise known as comment comments)
			ArrayList<Comment> thread = new ArrayList<Comment>();
			ArrayList<String> commentIDs = (ArrayList<String>)jComment.get(COMMENT_THREAD_IDs);
			if (commentIDs != null && !commentIDs.isEmpty()) {
				for (String cComID : commentIDs) {
					for (JSONObject jCComment : jsonComments) {
						if (UUID.fromString(cComID).equals(UUID.fromString((String)jCComment.get(COMMENT_ID)))) {
							thread.add(makeComment(jCComment));
						}
					}
				}
			}
			return new Comment(id, commenter, date, message, thread);
		} else {
			// if, for some reason, a comment's JSONObject was not passed in:
			return new Comment(users.get(0), "error");
		}
	}
}