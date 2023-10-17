import java.util.ArrayList;
import java.util.Date;

/**
 * The Project class. Holds data for the project and methods to manipulate it.
 */
public class Project {
    private ArrayList<User> team;
    private ArrayList<Column> columns;
    private Date startSprint;
    private Date endSprint;
    private User user;
    private String projectName;
    private ArrayList<Comment> comments;
    private ProductBacklog productBacklog;
/**
 * 
 * @param projectName
 * @param user
 */
    public Project(String projectName, User user) {
        this.projectName = projectName;
        this.user = user;
        this.team = new ArrayList<>();
        this.columns = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.productBacklog = new ProductBacklog();
    }
/**
 * 
 * @param user
 * @param type
 * @return
 */
    public boolean addUser(User user, UserType type) {
        return false;
    }
/**
 * 
 * @param user
 * @return
 */
    public boolean removeUser(User user) {
        return false;
    }
/**
 * 
 * @param column
 * @return
 */
    public boolean addColumn(Column column) {
        return false;
    }
/**
 * 
 * @param column
 * @return
 */
    public boolean removeColumn(Column column) {
        return false;
    }
/**
 * 
 * @param column
 * @return
 */
    public boolean editColumn(Column column) {
        return false;
    }

    public void startSprint() {
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectName='" + projectName + '\'' +
                ", user=" + user +
                ", team=" + team +
                ", columns=" + columns +
                ", startSprint=" + startSprint +
                ", endSprint=" + endSprint +
                ", comments=" + comments +
                ", productBacklog=" + productBacklog +
                '}';
    }

    public void save() {
    }
/**
 * 
 * @param user
 * @param project
 * @param comment
 * @return
 */
    private boolean addComment(User user, Project project, String comment) {
        return false;
    }
/**
 * 
 * @param user
 * @param project
 * @param comment
 * @return
 */
    private boolean threadComment(User user, Project project, String comment) {
        return false;
    }

    /**
     * Gets the name of the project
     * @author Chris
     * @return the project name (this.projectName)
     */
    public String getProjectName() {
        if (this.projectName != null)
            return this.projectName;
        else
            return "no name found";
    }
}
