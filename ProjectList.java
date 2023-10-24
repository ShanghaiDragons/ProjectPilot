import java.util.ArrayList;

/**
 * The ProjectList class. Gets from the Data Loader which gets from the JSON file.
 */
public class ProjectList {
    private static ProjectList projectListInstance;
    private ArrayList<Project> projects;

    /**
     * ProjectList constructor. Uses DataLoader to initialize the projects
     * @author ctaks
     */
    private ProjectList() {
        projects = DataLoader.getProjects();
       
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
            if (projectName.equalsIgnoreCase(p.getName()))
                return p;
        }
        return null; 
    }

    /**
     * Gets the current list of projects
     * @author Chris
     * @return the ArrayList<Project> of projects.
     */
    public ArrayList<Project> getProjects() {
        return this.projects;
    }

}
