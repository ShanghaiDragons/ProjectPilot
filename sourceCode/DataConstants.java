package sourceCode;
public abstract class DataConstants {
	// User
	protected static final String USER = "user";
	protected static final String USER_FILE_NAME = "json/UsersScenario1.json"; //TODO: change to correct
	protected static final String USER_ID = "id";
	protected static final String USER_USER_NAME = "userName";
	protected static final String USER_FIRST_NAME = "firstName";
	protected static final String USER_LAST_NAME = "lastName";
	protected static final String USER_PASSWORD = "password";
	protected static final String USER_ADD_TASK = "permissionToAddTask";
	protected static final String USER_MOVE_TASK = "permissionToMoveTask";
	protected static final String USER_EDIT_TASK = "permissionToEditTask";
	protected static final String USER_EDIT_COLUMN = "permissionToEditColumns";

	// Project
	protected static final String PROJECT = "project";
	protected static final String PROJECT_FILE_NAME = "json/ProjectsScenario1.json"; //TODO: change back to correct filename.
	protected static final String PROJECT_NAME = "projectName";
	protected static final String PROJECT_ID = "projectID";
	protected static final String PROJECT_TEAM = "team";
	protected static final String PROJECT_START_SPRINT = "startSprint";
	protected static final String PROJECT_END_SPRINT = "endSprint";
	protected static final String PROJECT_COLUMN_IDS = "projectColumnIDs";
	protected static final String PROJECT_COMMENT_IDs = "projectCommentIDs";

	// Column
	protected static final String COLUMN = "column";
	protected static final String COLUMN_NAME = "columnName";
	protected static final String COLUMN_ID = "columnID";
	protected static final String COLUMN_SORT_TYPE = "sortType";
	protected static final String COLUMN_TASK_IDS = "columnTaskIDs";
	// protected static final String COLUMN_PROJECT_ID = "projectID";
	protected static final String COLUMN_COMMENT_IDS = "columnCommentIDs";

	// TASK
	protected static final String TASK = "task";
	protected static final String TASK_NAME = "taskName";
	protected static final String TASK_ID = "taskID";
	protected static final String TASK_ASSIGNEE = "assignee";
	protected static final String TASK_PRIORITY = "priority";
	protected static final String TASK_STATUS = "status";
	protected static final String TASK_DESCRIPTION = "description";
	protected static final String TASK_TASK_HISTORY_ID = "taskTaskHistoryID";
	protected static final String TASK_COMMENT_IDS = "taskCommentIDs";
	protected static final String TASK_GENERAL = "isGeneral"; // boolean
	protected static final String TASK_NEW_FEATURE = "isNewFeature"; // boolean
	protected static final String TASK_BUG = "isBug"; // boolean

	// TaskHistory
	protected static final String TASK_HISTORY_LOG = "taskHistoryLog";
	protected static final String TASK_HISTORY = "taskHistory";
	protected static final String TASK_HISTORY_ID = "taskHistoryID";
	protected static final String TASK_HISTORY_TASK_ID = "taskHistoryTaskID";
	protected static final String TASK_HISTORY_CREATION_DATE = "creationDate";
	protected static final String TASK_HISTORY_NAME_CHANGES = "nameChanges";
	protected static final String TASK_HISTORY_DESCRIPTION_CHANGES = "descriptionChanges";
	protected static final String TASK_HISTORY_MOVE_CHANGES = "moveChanges";
	protected static final String TASK_HISTORY_ASSIGNEE_CHANGES = "assigneeChanges";
	protected static final String TASK_HISTORY_PRIORITY_CHANGES = "priorityChanges";
	protected static final String TASK_HISTORY_STATUS_CHANGES = "statusChanges";

	// Comment
	protected static final String COMMENT = "comment";
	// protected static final String COMMENT_PROJECT_ID = "projectID";
	// protected static final String COMMENT_COLUMN_ID = "columnID";
	// protected static final String COMMENT_TASK_ID = "taskID";
	protected static final String COMMENT_ID = "commentID";
	protected static final String COMMENT_USER_ID = "commentUserID";
	protected static final String COMMENT_DATE = "commentDate";
	protected static final String COMMENT_MESSAGE = "commentMessage";
	protected static final String COMMENT_THREAD_IDs = "commentThreadIDs";
}