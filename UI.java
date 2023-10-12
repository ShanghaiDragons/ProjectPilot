import java.util.Scanner;

public class UI {
    private Scanner scanner;
    private ProjectPilotFacade facade;

    public UI() {
        scanner = new Scanner(System.in);
        facade = new ProjectPilotFacade(); // Assuming ProjectPilotFacade is a class in your system
    }

    public void run() {
        // Implement the main logic of your UI here
    }

    public void displayLoginScreen() {
        // Implement the login screen display logic
    }

    public void displayProject() {
        // Implement the display of project details
    }

    public void displaySettingsMenu() {
        // Implement the settings menu display logic
    }

    public void displayAccountInfo() {
        // Implement the display of user account information
    }
}