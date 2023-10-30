
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * Temporary Driver class. To be removed when the actual UI is made.
 */
public class TempDriver {

  public static final Scanner keyboard = new Scanner(System.in);
  private ProjectPilotFacade ppf;
  private boolean quit;

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
    // if(!loadAProject()) {
      // System.out.println("No projects found.");
      // createProject();
    // }
    loadAProject();
    System.out.println("The project that has been loaded is:");
    System.out.println(ppf.getCurrentProject().getName());
    
    quit = false;
    while(!quit) {
    projectMenu();
    }
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
    if (ppf.createAccount(firstName, lastName, username, password1) != null)
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
  public boolean loadAProject() {
    if (ppf.getProjects() == null) {
      return false;
    }

    System.out.println("Which project would you like to select?");
    int i = 0;
    System.out.println("[0] create a new project");
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
    if (choice == -1) {
      createProject();
      loadAProject();
    } else {
      ppf.loadProject(ppf.getProject(ppf.getProjects().get(choice).getName()).getName());
    }
    return true;
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
    +"\n[6] Add team member"
    +"\n[7] Remove team member"
    +"\n[8] save project"
    +"\n[9] quit ProjectPilot");
    int choice = keyboard.nextInt();
    keyboard.nextLine();
    switch (choice) {
      case 1:
        columnMenu();
        break;
      case 2:
        System.out.println("Enter start sprint INTEGER: month");
        int startMonth = keyboard.nextInt();
        keyboard.nextLine();
        System.out.println("Enter start sprint INTEGER: day");
        int startDay = keyboard.nextInt();
        keyboard.nextLine();
        LocalDate startSprint = LocalDate.of(2023, startMonth, startDay);
        ppf.getCurrentProject().setStartSprint(startSprint);
        break;
      case 3:
        System.out.println("Enter end sprint INTEGER: month");
        int endMonth = keyboard.nextInt();
        keyboard.nextLine();
        System.out.println("Enter end sprint INTEGER: day");
        int endDay = keyboard.nextInt();
        keyboard.nextLine();
        LocalDate endSprint = LocalDate.of(2023, endMonth, endDay);
        ppf.getCurrentProject().setEndSprint(endSprint);
        break;
      case 4:
        System.out.println("Enter a new project name");
        String newname = keyboard.nextLine();
        ppf.getCurrentProject().setName(newname);
        break;
      case 5:
        System.out.println("adding comment...\nEnter comment message");
        String message = keyboard.nextLine();
        ppf.addComment(message);
        break;
      case 6:
        int u = 0;
        System.out.println("List of users:");
        for (User user : ppf.getUsers()) {
          System.out.println("["+(u+1)+"]: "+user.getUserName());
        }
        int uchoice = 0;
        boolean hasSelected = false;
        System.out.println("Select a username");
        while (!hasSelected) {
          uchoice = keyboard.nextInt();
          keyboard.nextLine();
          if (uchoice <= ppf.getUsers().size())
            hasSelected = true;
          else
            System.out.println("Please try again.");
        }
        uchoice--;
        hasSelected = false;
        System.out.println("Selected the user's role:\n[1] Scrum Master\n[2] Collaborator\n[3] Viewer");
        int tchoice = keyboard.nextInt();
        keyboard.nextLine();
        UserType type = UserType.SCRUM_MASTER;
        if (tchoice == 2)
          type = UserType.COLLABORATOR;
        if (tchoice == 3)
          type = UserType.VIEWER;
        System.out.println("Adding user...");
        ppf.getCurrentProject().addUser(ppf.getUsers().get(choice), type);
        break;
      case 7:
        int r = 0;
        for (User user : ppf.getCurrentProject().getTeam()) {
          System.out.println("["+(r+1)+"]: "+user.getUserName());
        }
        int rchoice = 0;
        boolean hasSelectedr = false;
        System.out.println("Select a username");
        while (!hasSelectedr) {
          rchoice = keyboard.nextInt();
          keyboard.nextLine();
          if (rchoice <= ppf.getCurrentProject().getTeam().size())
            hasSelectedr = true;
          else
            System.out.println("Please try again.");
        }
        rchoice--;
        System.out.println("Removing user...");
        ppf.getCurrentProject().removeUser(ppf.getCurrentProject().getTeam().get(rchoice));
        break;
      case 8:
        System.out.println("saving project...");
        ppf.saveProjects();
        break;
      case 9:
        quit = true;
        break;
      default:
        System.out.println("invalid choice");
        break;
    }
  }
  
  public void columnMenu() {
    System.out.println("Select a column:");
    int i = 0;
    System.out.println("[0] create a new column");
    for (Column c : ppf.getCurrentProject().getColumns()) {
      System.out.println("["+(i+1)+"]: "+c.getName());
      i++;
    }
    int choice= 0;
    boolean hasSelected = false;
    while (!hasSelected) {
      choice = keyboard.nextInt();
      keyboard.nextLine();
      if (choice <= ppf.getCurrentProject().getColumns().size())
        hasSelected = true;
      else
        System.out.println("Please try again.");
    }
    choice --;
    if (choice == -1) {
        createColumn();
        columnMenu();
      } else {
        int choice2 = 0;
        while(choice2 != 5 || choice != 1) {
          System.out.println(ppf.getCurrentProject().getColumns().get(choice).getName()+" menu:"
          +"\n[1] Task menu"
          +"\n[2] Change name"
          +"\n[3] Change sort type"
          +"\n[4] Comment menu"
          +"\n[5] Go Back to Project Menu"
          );
          choice2 = keyboard.nextInt();
          keyboard.nextLine();
          switch(choice2) {
            case 1:
              taskMenu(ppf.getCurrentProject().getColumns().get(choice));
              break;
            case 2:
               System.out.println("Enter a new column name");
               String newname = keyboard.nextLine();
               ppf.getCurrentProject().getColumns().get(choice).setName(newname);
            break;
            case 3:
              System.out.println("Enter a new sortType");
               String newSortType = keyboard.nextLine();
               ppf.getCurrentProject().getColumns().get(choice).setSortType(newSortType);
            break;
            case 4:
            break;
            case 5:
            projectMenu();
            break;
            default:
            break;
          }
        }
      }
    }
    
    public void taskMenu(Column c) {
     System.out.println("Select a task:");
      int i = 0;
      System.out.println("[0] create a new task");
      for (Task t : c.getTasks()) {
        System.out.println("["+(i+1)+"]: "+t.getName());
        i++;
      }
      int choice= 0;
      boolean hasSelected = false;
      while (!hasSelected) {
        choice = keyboard.nextInt();
        keyboard.nextLine();
        if (choice <= c.getTasks().size())
          hasSelected = true;
          else
          System.out.println("Please try again.");
        }
      choice --;
      if (choice == -1) {
        createTask(c);
        taskMenu(c);
      } else {
        int choice2 = 0;
        while(choice2 != 6) {
          System.out.println(c.getTasks().get(choice).getName()+ " menu:"
          +"\n[1] Change name"
          +"\n[2] Change assignee"
          +"\n[3] Change priority"
          +"\n[4] Change status"
          +"\n[5] Change description"
          +"\n[6] Go back to Column Menu"
          +"\n[7] Go back to Project Menu"
          );
          choice2 = keyboard.nextInt();
          keyboard.nextLine();
          switch(choice2) {
            case 1:
              break;
            case 2:
              break;
            case 3:
              break;
            case 4:
              break;
            case 5:
              break;
            case 6:
              columnMenu();
              break;
            case 7:
              projectMenu();
            default:
              break;
          }
        }
      }
    }

    public void commentProjectMenu() {
      System.out.println("select a comment");
      int i = 0;
      System.out.println("[0] create a new comment");
      for (Comment c : ppf.getCurrentProject().getComments()) {
        System.out.println("["+(i+1)+"]: "+c.getUser().getUserName()+": "+c.getMessage());
        i++;
      }
      int choice = 0;
      boolean hasSelected = false;
      while(!hasSelected) {
        choice = keyboard.nextInt();
        keyboard.nextLine();
        if (choice <= ppf.getCurrentProject().getComments().size())
          hasSelected = true;
        else
          System.out.println("Please try again.");
      }
      choice--;
      if (choice == -1) {
        ppf.addComment(createComment());
      } else {
        int choice2 = 0;
        while(choice2 != 1) {
          System.out.println(ppf.getCurrentProject().getComments().get(choice)+" menu:"
          +"\n[1] comment thread menu"
          +"\n[2] change message"
          +"\n[3] Go back to projects menu"
          );
          choice2 = keyboard.nextInt();
          keyboard.nextLine();
          switch(choice2) {
            case 1:
              commentCommentMenu(ppf.getCurrentProject().getComments().get(choice));
              break;
            case 2:
              System.out.println("Enter a new message");
              String newmessage = keyboard.nextLine();
              ppf.getCurrentProject().getComments().get(choice).setMessage(newmessage);
              break;
            case 3:
              projectMenu();
              break;
          }
        }
      }
    }

    public void commentColumnMenu() {

    }

    public void commentTaskMenu() {

    }

    public void commentCommentMenu(Comment c) {

    }
    
    public void createProject() {
      System.out.println("Creating a project...");
  
      System.out.println("Enter project name");
      String name = keyboard.nextLine();
  
      // creating temp variables to get the project constructed. These can be filled out later in the menus.
      LocalDate startDate = LocalDate.now();
      LocalDate endDate = LocalDate.now();
      ArrayList<User> team = new ArrayList<User>();
      ArrayList<Column> columns = new ArrayList<Column>();
      ArrayList<Comment> comments = new ArrayList<Comment>();
  
      ppf.addProject(name, startDate, endDate, team, columns, comments);
      /*
      System.out.println("Enter start sprint INTEGER: month");
      int startMonth = keyboard.nextInt();
      keyboard.nextLine();
      System.out.println("Enter start sprint INTEGER: day");
      int startDay = keyboard.nextInt();
      keyboard.nextLine();
      System.out.println("Enter end sprint INTEGER: month");
      int endMonth = keyboard.nextInt();
      keyboard.nextLine();
      System.out.println("Enter end sprint INTEGER: day");
      int endDay = keyboard.nextInt();
      keyboard.nextLine();
      LocalDate startSprint = LocalDate.of(2023, startMonth, startDay);
      LocalDate endSprint = LocalDate.of(2023, endMonth, endDay);
      
      // Adding the list of users to the project. Yeah it's a lot.
      System.out.println("Pick from the list of users to add to the project.");
      int i = 1;
      for (User user : ppf.getUsers()) {
        System.out.println("["+i+"]: "+user.getUserName());
        i++;
      }
      System.out.println("Enter the number of the user, then press Enter. When you're done, enter 0.");
      ArrayList<Integer> usersToSelect = new ArrayList<Integer>();
      int choice = keyboard.nextInt();
      keyboard.nextLine();
      while (choice != 0) {
        usersToSelect.add(choice);
        choice = keyboard.nextInt();
      }
      ArrayList<User> team = new ArrayList<User>();
      for (int user : usersToSelect) {
        team.add(ppf.getUsers().get(user-1));
      }
      */
    }

  public void createColumn() {
    System.out.println("Creating a column...");
    System.out.println("Enter column name");
    String name = keyboard.nextLine();
    ArrayList<Task> tasks = new ArrayList<Task>();
    ArrayList<Comment> comments = new ArrayList<Comment>();
    ppf.addColumn(name, "default", tasks, comments);
  }

  public void createTask(Column c) {
    System.out.println("Creating a task...");
    System.out.println("Enter task name");
    String name = keyboard.nextLine();
    User temp = ppf.getUser();
    ArrayList<Comment> comments = new ArrayList<Comment>();
    ppf.addTask(c, name, temp, 1, "active", "empty", comments);
  }

  public String createComment() {
    System.out.println("Enter a comment message");
    String message = keyboard.nextLine();
    return message;
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
