import java.util.ArrayList;

/**
 * The ProjectList class. Gets from the Data Loader which gets from the JSON file.
 */
public class ProjectList {
    private static ProjectList projectListInstance;
    private ArrayList<Project> projects;

    /**
     * 
     */
    private ProjectList() {
       
        // projects = new ArrayList<>();
        projectListInstance.projects = DataLoader.getProjects();
       
    }

    /**
     * 
     * @return
     */
    public static ProjectList getInstance() {
        if (projectListInstance == null) {
            projectListInstance = new ProjectList();
        }
        return projectListInstance;
    }

    /**
    * A method for accessing a project based on its name. Loops through every project and returns the match.
    * @author Chris
    * @param projectName the name of the project
    * @return the project that matches the given project name
    */
    public Project getProject(String projectName) {
        for (Project p : projectListInstance.projects) {
            if (projectName.equalsIgnoreCase(p.getProjectName()))
                return p;
        }
        return null; 
    }

}
