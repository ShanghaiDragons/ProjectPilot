public class Comment {
    private User user;
    private Task task;
    private String comment;

    public Comment(User user, Task task, String comment) {
        this.user = user;
        this.task = task;
        this.comment = comment;
    }

    public static boolean addComment(User user, Task task, String comment) {
        // Implement logic to add a comment to the task
        // Return true if comment added successfully, false otherwise
        return false; // Placeholder return value
    }

    public static boolean threadComment(User user, Task task, String comment) {
        // Implement logic to thread a comment in the task
        // Return true if comment threaded successfully, false otherwise
        return false; // Placeholder return value
    }

    @Override
    public String toString() {
        // Implement logic to convert the object to a string representation
        // Return a string containing comment information
        return "Comment{" +
                "user=" + user +
                ", task=" + task +
                ", comment='" + comment + '\'' +
                '}';
    }
}
