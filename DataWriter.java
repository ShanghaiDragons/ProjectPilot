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
    private JSONArray jsonProjects = new JSONArray();
    /**
     * Saves the list of users to the user JSON file.
    * @author Chris
    * @param usersList the list of users to save
    * @return boolean determining if the save was successful or not
    */   
    public boolean saveUsers() {
        UserList users = UserList.getInstance();
        ArrayList<User> userList = users.getUsers();

        // JSONArray jsonUsers = new JSONArray();
        JSONObject jsonUsers = new JSONObject();

        /*
        // TEST USERS. TODO: remove when testing is done.
        UUID userID1 = UUID.randomUUID();
        User user1 = new User(userID1, "testUsername", "testFirstname", "testLastname", "testPassword", false, false, false, false);
        User user2 = new User(UUID.randomUUID(), "TestUsername2", "testFirstname2", "testLastName2", "testPassword2", false, false, false, false);
        jsonUsers.add(getUserJSON(user1));
        jsonUsers.add(getUserJSON(user2));
        ArrayList<String> things = new ArrayList();
        things.add("test1");
        things.add("test2");
        things.add("test3");
        JSONObject thingobj = new JSONObject();
        thingobj.put("things", things);
        jsonUsers.add(thingobj);
        */

        // Creating JSON objects
        for(int i=0; i < userList.size(); i++) {
            jsonUsers.put(USER, getUserJSON(userList.get(i)));
        }

        // Write to JSON file
        // TODO: hardcoded the filename for testing. Change for final version. -Chris
        try (FileWriter file = new FileWriter("json/Users_test_write1.json")) {
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
    public JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID, user.getID().toString());
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_USER_NAME, user.getUserName());
        userDetails.put(USER_PASSWORD, user.getPassword());
        userDetails.put(USER_ADD_TASK, user.getPermissionToAddTask());
        userDetails.put(USER_MOVE_TASK, user.getPermissionToMoveTask());
        userDetails.put(USER_EDIT_TASK, user.getPermissionToEditTask());
        userDetails.put(USER_EDIT_COLUMN, user.getPermissionToEditColumns());

        return userDetails;
    }

    /**
     * Saves the projects to the project JSON file.
    * @author Chris
    * @param projectsList the list of projects to save
    * @return boolean determining if the save was successful or not
    */   
    public boolean saveProjects() {
        ProjectList projects = ProjectList.getInstance();
        ArrayList<Project> projectList = projects.getProjects();
        
        // JSONArray jsonProjectsArray = new JSONArray();
        // JSONArray jsonProjects = new JSONArray();
        
        // TEST Project TODO: Remove when testing is done
        // User testUser = new User(UUID.randomUUID(), "testUsername", "testFirstname", "testLastname", "testPassword", false, false, false, false);
        // Project project1 = new Project(UUID.randomUUID(), "Test Project 1", testUser);
        // Project project2 = new Project(UUID.randomUUID(), "Test project 2", testUser);
        // jsonProjects.add(getProjectJSON(project1));
        // jsonProjects.add(getProjectJSON(project2));

        // Creating JSON objects
        // PROJECT
        for(int i=0; i < projectList.size(); i++) {
            jsonProjects.add(getProjectJSON(projectList.get(i)));
            // COLUMN
            for (int j=0; j < projectList.get(i).getColumns().size(); j++) {
                jsonProjects.add(getColumnJSON(projectList.get(i).getColumns().get(j)));
                // TASK
                for (int k=0; k < projectList.get(i).getColumns().get(j).getTasks().size(); k++) {
                    jsonProjects.add(getTaskJSON(projectList.get(i).getColumns().get(j).getTasks().get(k)));
                    // TASK HISTORY
                    jsonProjects.add(getTaskHistoryJSON(projectList.get(i).getColumns().get(j).getTasks().get(k).getTaskHistory()));
                }
            }
        }

        // jsonProjectsArray.add(jsonProjects);
        // Write to JSON file
        // TODO: hardcoded the filename for testing. change file name for final version. -Chris
        try (FileWriter file = new FileWriter("json/Projects_test2.json")) {
            file.write(jsonProjects.toJSONString());
            file.flush();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Create a JSONObject for a project
     * @author Chris edited by Duayne
     * @param project the project name
     * @return JSONObject of the project's data
     */
    public JSONObject getProjectJSON(Project project) {
        JSONObject projectData = new JSONObject();

        projectData.put(PROJECT_ID, project.getID().toString());
        projectData.put(PROJECT_NAME, project.getName());

        JSONArray team = new JSONArray();
        for (User user : project.getTeam())
            team.add(user.getID().toString());

        projectData.put(PROJECT_TEAM, team);
        projectData.put(PROJECT_START_SPRINT, project.getStartSprint().toString());
        projectData.put(PROJECT_END_SPRINT, project.getEndSprint().toString());

        JSONArray columnIDs = new JSONArray();
        for (Column column : project.getColumns())
            columnIDs.add(column.getID().toString());

        projectData.put(PROJECT_COLUMN_IDS, columnIDs);

        JSONArray commentIDs = new JSONArray();
        for (Comment comment : project.getComments()) {
            commentIDs.add(comment.getID().toString());
            jsonProjects.add(getCommentJSON(comment));
        }

        projectData.put(PROJECT_COMMENT_IDs, commentIDs);

        return projectData;
    }

    /**
     * Create a JSONObject for a column
     * @author Chris
     * @param column the column
     * @return JSONObject of the column's data
     */
    public JSONObject getColumnJSON(Column column) {
        JSONObject columnData = new JSONObject();

        columnData.put(COLUMN_ID, column.getID().toString());
        columnData.put(COLUMN_NAME, column.getName());
        columnData.put(COLUMN_SORT_TYPE, column.getSortType());

        JSONArray taskIDs = new JSONArray();
        for(Task task : column.getTasks())
            taskIDs.add(task.getID().toString());

        columnData.put(COLUMN_TASK_IDS, taskIDs);

        JSONArray commentIDs = new JSONArray();
        for (Comment comment : column.getComments()) {
            commentIDs.add(comment.getID().toString());
            jsonProjects.add(getCommentJSON(comment));
        }

        columnData.put(COLUMN_COMMENT_IDS, commentIDs);

        return columnData;
    }

    /**
     * Creates a JSONObject for a task
     * @param task the task
     * @return JSONObject of a task's data
     */
    public JSONObject getTaskJSON(Task task) {
        JSONObject taskData = new JSONObject();

        int num = task.getPriority();
        String priority = Integer.toString(num);
        taskData.put(TASK_ID, task.getID().toString());
        taskData.put(TASK_NAME, task.getName());
        taskData.put(TASK_ASSIGNEE, task.getAssignee().getID().toString());
        taskData.put(TASK_PRIORITY, priority);
        taskData.put(TASK_STATUS, task.getStatus());
        taskData.put(TASK_DESCRIPTION, task.getDescription());
        taskData.put(TASK_TASK_HISTORY_ID, task.getTaskHistory().getID().toString());
        
        JSONArray commentIDs = new JSONArray();
        for (Comment comment : task.getComments()) {
            commentIDs.add(comment.getID().toString());
            jsonProjects.add(getCommentJSON(comment));
        }

        taskData.put(TASK_COMMENT_IDS, commentIDs);

        return taskData;
    }

    /**
     * Create a JSONOjbect for a TaskHistory
     * @author ctaks
     * @param taskH the TaskHistory
     * @return JSONObject of the taskHistory data
     */
    public static JSONObject getTaskHistoryJSON(TaskHistory taskH) {
        JSONObject taskHData = new JSONObject();

        taskHData.put(TASK_HISTORY_ID, taskH.getID().toString());
        taskHData.put(TASK_HISTORY_TASK_ID, taskH.getTaskID().toString());
        taskHData.put(TASK_HISTORY_CREATION_DATE, taskH.getCreationDate().toString());
        taskHData.put(TASK_HISTORY_NAME_CHANGES, taskH.getNameChanges());
        taskHData.put(TASK_HISTORY_DESCRIPTION_CHANGES, taskH.getDescriptionChanges());
        taskHData.put(TASK_HISTORY_MOVE_CHANGES, taskH.getMoveChanges());
        taskHData.put(TASK_HISTORY_ASSIGNEE_CHANGES, taskH.getAssigneeChanges());
        taskHData.put(TASK_HISTORY_PRIORITY_CHANGES, taskH.getPriorityChanges());
        taskHData.put(TASK_HISTORY_STATUS_CHANGES, taskH.getStatusChanges());

        return taskHData;
    }

    /**
     * Create a JSONObject of a comment
     * @author ctaks
     * @param comment the comment
     * @return JSONObject of the comment data
     */
    public JSONObject getCommentJSON(Comment comment) {
        JSONObject commentData = new JSONObject();

        commentData.put(COMMENT_USER_ID, comment.getUser().getID().toString());
        commentData.put(COMMENT_DATE, comment.getDate().toString());
        commentData.put(COMMENT_MESSAGE, comment.getMessage());

        JSONArray commentIDs = new JSONArray();
        for (Comment threadcomment : comment.getThread()) {
            commentIDs.add(threadcomment.getID().toString());
            jsonProjects.add(getCommentJSON(comment));
        }

        commentData.put(COMMENT_THREAD_IDs, commentIDs);

        return commentData;
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
