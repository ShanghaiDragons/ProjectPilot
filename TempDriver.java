
import java.util.Scanner;
/**
 * Temporary Driver class. To be removed when the actual UI is made.
 */
public class TempDriver {

  public static final Scanner keyboard = new Scanner(System.in);
  private ProjectPilotFacade ppf;

  /**
   * This method runs the program.
   * @author ctaks
   */
  public void run() {
    ppf = ProjectPilotFacade.getInstance();
    System.out.println("Welcome to Project Pilot");

    System.out.println("Enter [1] to log in or [2] to make an account");
    int choice = keyboard.nextInt();
    keyboard.nextLine();
    switch (choice) {
      case 1:
        login();
        break;
      case 2:
        if(!makeNewAccount())
          return;
        break;
      default:
        System.out.println("fail");
    }
    System.out.println("You are logged in.");

    System.out.println("Loading projects...");
    loadAProject();
    System.out.println("The project that has been loaded is:");
    System.out.println(ppf.getCurrentProject().getName());

    System.out.println("Menu:");
    
  }
  
  /**
   * Logs the user in
   * @author ctaks
   */
  public void login() {
    boolean notLoggedIn = true;
    while (notLoggedIn) {
      System.out.println("Please enter your username");
      String username = keyboard.nextLine();
      System.out.println("Please enter your password");
      String password = keyboard.nextLine();
      if (ppf.login(username, password))
        notLoggedIn = false;
      else
        System.out.println("Login failed. Try again.");
    }
  }

  /**
   * Makes a new account
   * @author ctaks
   */
  public boolean makeNewAccount() {
    System.out.println("What is your first name?");
    String firstName = keyboard.nextLine();
    System.out.println("What is your last name?");
    String lastName = keyboard.nextLine();
    System.out.println("Please enter a username");
    String username = keyboard.nextLine();
    
    String password1 = "password";
    boolean passwordCheck = false;
    while(!passwordCheck) {
      System.out.println("Please enter a password");
      password1 = keyboard.nextLine();
      System.out.println("Please reenter the password");
      String password2 = keyboard.nextLine();
      if (password1.equals(password2))
        passwordCheck = true;
      else
        System.out.println("passwords do not match. Retry.");
    }
    if (ppf.createAccount(firstName, lastName, username, password1))
      System.out.println("Account created successfully");
    else {
      System.out.println("Account creation failed.\nExiting the program!");
      return false;
    }
    return true;
  }

  /**
   * Loads a project
   * @author ctaks
   */
  public void loadAProject() {
    System.out.println("Which project would you like to select?");
    int i = 0;
    for (Project project : ppf.getProjects()) {
      System.out.println("["+(i+1)+"] "+project.getName());
      i++;
    }
    int choice = 0;
    boolean hasSelected = false;
    while (!hasSelected) {
      choice = keyboard.nextInt();
      keyboard.nextLine();
      if(choice <= ppf.getProjects().size())
        hasSelected = true;
      else
        System.out.println("Please try again.");
    }
    choice--;
    ppf.loadProject(ppf.getProject(ppf.getProjects().get(choice).getName()).getName());
  }

  /**
   * project menu
   * @author ctaks
   * TODO: finish
   */
  public void projectMenu() {
    System.out.println(ppf.getCurrentProject().getName()+" menu:"
    +"\n[1] Columns menu"
    +"\n[2] Change sprint start date"
    +"\n[3] Change sprint end date"
    +"\n[4] Change project name"
    +"\n[5] Add comment"
    +"\n[6] Thread comment"
    +"\n[7] save project");
    int choice = keyboard.nextInt();
    keyboard.nextLine();
    switch (choice) {
      case 1:
        System.out.println("");
        break;
      case 2:
        System.out.println("");
        break;
      case 3:
        System.out.println("");
        break;
      case 4:
        System.out.println("");
        break;
      case 5:
        System.out.println("");
        break;
      case 6:
        System.out.println("");
        break;
      case 7:
        System.out.println("saving project...");
        // WILL NOT WORK YET: ppf.saveProjects();
      default:
        System.out.println("invalid choice");
        break;
    }
  }

  public void columnMenu() {

  }

  /**
   * The main method.
   * @author ctaks
   */
  public static void main(String[] args) {
    TempDriver tempDriver = new TempDriver();
    tempDriver.run();
  }
}
