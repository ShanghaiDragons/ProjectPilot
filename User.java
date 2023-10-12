public abstract class User {
    private String firstName;
    private String lastName;
    private String userName;
    private boolean permissionToAddTask;
    private boolean permissionToMoveTask;
    private boolean permissionToEditTask;
    private boolean permissionToEditColumns;

    public User(String firstName, String lastName, String userName, boolean permissionToAddTask,
                boolean permissionToMoveTask, boolean permissionToEditTask, boolean permissionToEditColumns) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.permissionToAddTask = permissionToAddTask;
        this.permissionToMoveTask = permissionToMoveTask;
        this.permissionToEditTask = permissionToEditTask;
        this.permissionToEditColumns = permissionToEditColumns;
    }

    public User login(String username, String password) {
        // Implement logic to validate the username and password
        // Return the user object if login is successful, null otherwise
        return null; // Placeholder return value
    }

    public boolean verifyPassword(String password) {
        // Implement logic to verify the provided password
        // Return true if the password is correct, false otherwise
        return false; // Placeholder return value
    }

    public boolean addTask(Task task) {
        // Implement logic to add a task
        // Return true if the task is added successfully, false otherwise
        return false; // Placeholder return value
    }

    public void editTask(Task task) {
        // Implement logic to edit a task
        // Modify the task object directly or update it in the data source
    }

    public boolean editColumns(Task task) {
        // Implement logic to edit columns of a task
        // Return true if columns are edited successfully, false otherwise
        return false; // Placeholder return value
    }

    public boolean moveTask(Task task) {
        // Implement logic to move a task to a different column
        // Return true if the task is moved successfully, false otherwise
        return false; // Placeholder return value
    }

    @Override
    public String toString() {
        // Implement logic to convert the object to a string representation
        // Return a string containing user information
        return "User: " + userName + ", " + firstName + " " + lastName;
    }
}
