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
	private JSONArray userFile = new JSONArray();
	private ArrayList<User> users;
	private JSONArray projectFile = new JSONArray();
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
		setUsers();
		setProjectObjects();
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
						this.userFile.remove(i);
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
			for(int i=0; i < jsonProjects.size(); i++) {
				String projectName = (String)jsonProjects.get(i).get(PROJECT_NAME);
				if (projectName != null) {
					UUID id = UUID.fromString((String)jsonProjects.get(i).get(PROJECT_ID));
					//String start = (String)jsonProjects.get(i).get(PROJECT_START_SPRINT);
					LocalDate startSprint = LocalDate.parse((String)jsonProjects.get(i).get(PROJECT_START_SPRINT));
					//String end = (String)jsonProjects.get(i).get(PROJECT_END_SPRINT);
					LocalDate endSprint = LocalDate.parse((String)jsonProjects.get(i).get(PROJECT_END_SPRINT));
					// Teams
					ArrayList<User> team = new ArrayList<User>();
					ArrayList<String> jsonTeam = (ArrayList<String>)jsonProjects.get(i).get(PROJECT_TEAM);
					for(String jTeamID : jsonTeam) {
						for (User user : this.users) {
							if (user.getID() == UUID.fromString(jTeamID)) {
								team.add(user);
							}
						}
					}
					// Columns
					ArrayList<Column> columns = new ArrayList<Column>();
					ArrayList<String> projectColumnIDs = (ArrayList<String>)jsonProjects.get(i).get(PROJECT_COLUMN_IDS);
					for (String pColID : projectColumnIDs) {
						for (JSONObject jcolumn : this.jsonColumns) {
							if (UUID.fromString((String)jcolumn.get(COLUMN_ID)) == UUID.fromString(pColID)) {
								columns.add(makeColumn(jcolumn));
							}
						}
					}
					// Comments
					ArrayList<Comment> comments = new ArrayList<Comment>();
					ArrayList<String> tempComments = (ArrayList<String>)projectJSON.get(PROJECT_COMMENT_IDs);
					for(int j = 0; j < getComments().size(); j++) {
						for (int k = 0; k < tempComments.size(); k++) {
							if (UUID.fromString(tempComments.get(k)).equals(getComments().get(j).getID())) {
								comments.add(getComments().get(j));
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
					if (UUID.fromString((String)jtask.get(TASK_ID)) == UUID.fromString(cTaskID)) {
						tasks.add(makeTask(jtask));
					}
				}
			}
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
			int priority = (int)jtask.get(TASK_PRIORITY); // TODO: make sure this works
			String status = (String)jtask.get(TASK_STATUS);
			String description = (String)jtask.get(TASK_DESCRIPTION);
			// assignee
			User assignee;
			UUID assigneeID = UUID.fromString((String)jtask.get(TASK_ASSIGNEE));
			for (User user : users) {
				if (user.getID() == assigneeID) {
					assignee = user;
				}
			}
			// taskHistory
			TaskHistory taskHistory;
			UUID taskHistoryID = (UUID.fromString((String)jtask.get(TASK_TASK_HISTORY_ID)));
			for (JSONObject jTaskHistory : jsonTaskHistories) {
				if (UUID.fromString((String)jTaskHistory.get(TASK_HISTORY_ID)) == taskHistoryID) {
					taskHistory = makeTaskHistory(jTaskHistory);
				}
			}
			// comments
			ArrayList<Comment> comments = new ArrayList<Comment>();
			ArrayList<String> taskCommentIDs = (ArrayList<String>)jtask.get(TASK_COMMENT_IDS);
			for (String tComID : taskCommentIDs) {
				for (JSONObject jComment : jsonComments) {
					if (UUID.fromString((String)jtask.get(COLUMN_ID)) == UUID.fromString(tComID)) {
						comments.add(makeComment(jComment));
					}
				}
			}
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
			UUID taskID = UUID.fromString((String)jTaskHistory.get(TASK_TASK_HISTORY_ID));
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

	public Comment makeComment(JSONObject jComment) {
		String message = (String)jComment.get(COMMENT_MESSAGE);
		if (message != null) {
			
		} else {
			// TODO: figure out what should happen
		}
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
						for(int j = 0; j < getComments().size(); j++) {
							for (int k = 0; k < tempComments.size(); k++) {
								if (UUID.fromString(tempComments.get(k)).equals(getComments().get(j).getID())) {
									comments.add(getComments().get(j));
								}
							}
						}
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
					String message = (String)projectJSON.get(COMMENT_MESSAGE);
				  if (message != null) {
						UUID commentID = UUID.fromString((String)projectJSON.get(COMMENT_ID));
						UUID userId = UUID.fromString((String)projectJSON.get(COMMENT_USER_ID));
						User user = null;
						for (User x : getUsers()) {
							if (x.getID().equals(userId))
								user = x;
						}
						String time = (String)projectJSON.get(COMMENT_DATE);
						LocalDateTime commentDate = LocalDateTime.parse(time);
						ArrayList<Comment> commentList = new ArrayList<Comment>();
							ArrayList<String> tempComments = (ArrayList<String>)projectJSON.get(COMMENT_THREAD_IDs);
							//for(int j = 0; j < getComments().size(); j++) {
								//for(int j = 0; i < 3; j++) {  // HARD CODED FOR SCENARIO :) TODO: fix later!!
									//System.out.println("test");
								// for (int k = 0; k < tempComments.size(); k++) {
								for (int k = 0; k < tempComments.size(); k++) {
									if (UUID.fromString(tempComments.get(k)).equals(getComments().get(k).getID())) {
										commentList.add(getComments().get(k));
									}
								}
							//}
						comments.add(new Comment(commentID, user, commentDate, message, commentList));
			  	}
				}
			
			// for (Comment c : comments)
				// System.out.println(c.getMessage());
			
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
						TaskHistory taskHistory = null;
						UUID taskHistoryID = (UUID.fromString((String)projectJSON.get(TASK_TASK_HISTORY_ID)));
						//TODO: fix later
						for (int j = 0; j < getTaskHistory().size(); j++)
							if (taskHistoryID.equals(getTaskHistory().get(j).getID()))
								taskHistory = getTaskHistory().get(j);
						ArrayList<Comment> comments = new ArrayList<Comment>();
						ArrayList<String> tempComments = (ArrayList<String>)projectJSON.get(TASK_COMMENT_IDS);
						for(int j = 0; j < getComments().size(); j++) {
							for (int k = 0; k < tempComments.size(); k++) {
								if (UUID.fromString(tempComments.get(k)).equals(getComments().get(j).getID())) {
									comments.add(getComments().get(j));
								}
							}
						}
						Task task = new Task(id, name, assignee, priority, status, description, taskHistory, comments);
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
	  public static ArrayList<TaskHistory> getTaskHistory() {
		ArrayList<TaskHistory> list = new ArrayList<TaskHistory>();
		try {
			  FileReader reader = new FileReader(PROJECT_FILE_NAME);
			  JSONParser parser = new JSONParser();	
			  JSONArray projectsJSON = (JSONArray)new JSONParser().parse(reader);
			
			  for(int i=0; i < projectsJSON.size(); i++) {
				  JSONObject projectJSON = (JSONObject)projectsJSON.get(i);
				  String historyID = (String)projectJSON.get(TASK_HISTORY_ID);
					if (historyID != null) {
						String date = (String)projectJSON.get(TASK_HISTORY_CREATION_DATE);
						LocalDateTime creationDate = LocalDateTime.parse(date);
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
						TaskHistory taskHistory = new TaskHistory(id, taskID, creationDate, tempNameChanges, tempDescriptionChanges, tempMoveChanges, tempAssigneeChanges, tempPriorityChanges, tempStatusChanges);
						list.add(taskHistory);
					}
			  }
			  
			  return list;
			  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  
		  return null;
	  }
	}