package controllers;

//import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import projectpilot.App;
import model.*;

public class HomeController implements Initializable{
    private ProjectPilotFacade ppf = ProjectPilotFacade.getInstance();
    private Project currentProject;
    @FXML
    private Label TaskTitle1;
    @FXML
    private Button ToLogin;
    @FXML
    private Button addColumn;
    @FXML
    private Button addTask;
    @FXML
    private Button btn_toNewProject;
    @FXML
    private Label columnTitle1;
    @FXML
    private Label lbl_project1;
    @FXML
    private Label projectTitle;
    @FXML
    private Label taskPriority;
    @FXML
    private ListView<String> lst_projects;
    @FXML
    private HBox scrumPane;
    @FXML
    private AnchorPane scrumPaneAnchor;
    @FXML
    private Pane homePane;
    @FXML
    private ImageView background_pic;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TitledPane titledPane_Projects;
    public static ObservableList<String> projectList = FXCollections.observableArrayList();

    /**
     * Initializes the facade to populate the data in the scene
     * @author ctaks
     * @param url for override (idk)
     * @param rb for override (idk)
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayProjects();
        if (ppf.getCurrentProject() != null) {
            this.currentProject = ppf.getCurrentProject();
        }
        buildScrumPane();

        Image background = new Image(getClass().getResourceAsStream("/images/background.jpg"));
        background_pic.setImage(background);
    }

    /**
     * Loads the list of projects
     * @author ctaks
     */
    private void displayProjects() {

        for (Project project : ppf.getProjects()) {
            if (!projectList.contains(project.getName()))
                projectList.add(project.getName());
        }
        lst_projects.setItems(projectList);
    }

    /**
     * When a project is selected from the list on the left
     * @author ctaks
     */
    @FXML
    private void projectSelected(MouseEvent event) throws IOException {
        String projectName = lst_projects.getSelectionModel().getSelectedItem().toString();

        currentProject = ppf.getProject(projectName);
        ppf.setCurrentProject(ppf.getProject(projectName));
        buildScrumPane();
    }

    @FXML
    private void buildScrumPane() {
        if (currentProject != null) {
            projectTitle.setText(currentProject.getName());
            scrumPane.getChildren().clear();
            for (Column col : currentProject.getColumns()) {
                scrumPane.getChildren().add(createScrumColumn(col));
            }

            scrumPaneAnchor.setPrefHeight(381);
            scrumPane.setPrefHeight(381);
        }
    }

    @FXML
    private VBox createScrumColumn(Column col) {
        VBox column = new VBox();
        Label columnTitle = new Label(col.getName());
        column.getChildren().add(columnTitle);
        
        VBox taskPanes = new VBox();
        Button addTaskButton = new Button("+");
        taskPanes.setAlignment(Pos.CENTER);
        for (Task task : col.getTasks()) {
            taskPanes.getChildren().add(createTask(task));
        }
        taskPanes.getChildren().add(addTaskButton);

        addTaskButton.setOnAction(event -> {
            try {
                ppf.setCurrentColumn(col);
                switchToNewTask(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        column.getChildren().add(taskPanes);
        column.setStyle("-fx-border-color: lightgray; -fx-border-width: 1;");
        column.setPadding(new Insets(10, 10, 10, 10));
        column.setPrefWidth(175);
        return column;
    }

    /**
     * Creates a task
     * @author ctaks
     * @param t task to be turned into a VBox of the task
     * @return VBox of the task
     */
    @FXML
    private VBox createTask(Task t) {
        VBox task = new VBox();
        Label taskName = new Label(t.getName());
        taskName.setWrapText(true);
        Label priority = new Label("Priority: "+t.getPriority());
        Label assignee = new Label("Assignee: "+t.getAssignee().getUserName());
        task.getChildren().addAll(taskName, priority, assignee);
        
        task.setOnMouseClicked(event -> {
            try {
                ppf.setCurrentTask(t);
                switchToTaskEditor(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        task.setStyle("-fx-border-color: lightgray; -fx-border-width: 1;");
        task.setPadding(new Insets(10, 10, 10, 10));

        return task;
    }

    @FXML
    void expandProjects(MouseEvent event) throws IOException {
        if (titledPane_Projects.isExpanded())
            titledPane_Projects.toFront();
        else {
            titledPane_Projects.toBack();
            background_pic.toBack();
        }
    }

    @FXML
    void switchToColumnEditor(MouseEvent event) throws IOException {
        App.setRoot("columnEditor");
    }

    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
        App.setRoot("login");
    }

    @FXML
    void switchToNewColumn(ActionEvent event) throws IOException {
        App.setRoot("newColumn");
    }

    @FXML
    void switchToNewProject(ActionEvent event) throws IOException {
        App.setRoot("newProject");
    }

    @FXML
    void switchToNewTask(ActionEvent event) throws IOException {
        App.setRoot("newTask");
    }

    @FXML
    void switchToProject(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void switchToProjectEditor(MouseEvent event) throws IOException {
        App.setRoot("projectEditor");
    }

    @FXML
    void switchToTaskEditor(MouseEvent event) throws IOException {
        App.setRoot("taskEditor");
    }

}
