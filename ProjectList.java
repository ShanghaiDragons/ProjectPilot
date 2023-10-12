import java.util.ArrayList;

public class ProjectList {
    private static ProjectList projectListInstance;
    private ArrayList<Project> projects;

    private ProjectList() {
       
        projects = new ArrayList<>();
       
    }

    public static ProjectList getInstance() {
        if (projectListInstance == null) {
            projectListInstance = new ProjectList();
        }
        return projectListInstance;
    }
/**
 * 
 * @param projectName
 * @return
 */
    public Project getProject(String projectName) {

        return null; 
    }

}
