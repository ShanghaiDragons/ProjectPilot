package controllers;

//import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableColumn;
import projectpilot.App;
import model.*;
import javafx.stage.Screen;

public class HomeController implements Initializable{
    private ProjectPilotFacade ppf;

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
    
    private Project currentProject;
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

    /**
     * Initializes the facade to populate the data in the scene
     * @author ctaks
     * @param url for override (idk)
     * @param rb for override (idk)
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ppf = ProjectPilotFacade.getInstance();
        if (ppf.getCurrentProject() != null) {
            this.currentProject = ppf.getCurrentProject();
        }
        displayProjects();
        buildScrumPane();

        Image background = new Image(getClass().getResourceAsStream("/images/background.jpg"));
        background_pic.setImage(background);

        /**
         //middle of screen:
         Rectangle2D psb = Screen.getPrimary().getVisualBounds();
         System.out.println("WIDTH: "+homePane.getPrefWidth());
         System.out.println("HEIGHT: "+homePane.getPrefHeight());
         double x = ((psb.getWidth() - homePane.getPrefWidth()) / 2);
         double y = ((psb.getHeight() - homePane.getPrefHeight()) /2);
         //homePane.relocate(x, y);
         */
    }

    /**
     * Loads the list of projects
     * @author ctaks
     */
    private void displayProjects() {
        ObservableList<String> projectList = FXCollections.observableArrayList();

        for (Project project : ppf.getProjects()) {
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
        // column.setMinWidth(100);
        // column.setMaxWidth(200);
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
                switchToNewTask(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        column.getChildren().add(taskPanes);
        column.setStyle("-fx-border-color: lightgray; -fx-border-width: 1;");
        column.setPadding(new Insets(10, 10, 10, 10));
        //taskPanes.setPadding(new Insets(10, 10, 10, 10));
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
        //System.out.println("TaskName: "+taskName.getMaxWidth());
        //System.out.println("Task: "+task.getMaxWidth());
        
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

    /**
     * 
     * @param col
     * @return
     @FXML
     private ListView<String> createTaskList(Column col) {
         ListView<String> taskListView = new ListView<String>();
         ObservableList<String> taskList = FXCollections.observableArrayList();
         for (Task task : col.getTasks()) {
             taskList.add(task.getName());
            }
            taskListView.setItems(taskList);
            return taskListView;
        }
        */
        
    /**
     * Builds the scrum board bosed on the currently selected project (TABLE)
     * @author ctaks
     @FXML
     private void buildBoard() {
         scrumBoard.getColumns().clear();
         scrumBoard.getItems().clear();
         int columnNum = currentProject.getColumns().size();
         int rowNum = 0;
         // populate rowNum with the highest task number of all the columns
         for (Column col : currentProject.getColumns()) {
             int colRowNum = col.getTasks().size();
            if (colRowNum > rowNum) {
                rowNum = colRowNum;
            }
        }
        // populate columns
        for (Column col : currentProject.getColumns()) {
            TableColumn<Task, String> column = new TableColumn<Task, String>(col.getName());
            column.setCellValueFactory(new PropertyValueFactory<Task, String>("name"));
            scrumBoard.getColumns().add(column);
            // populate tasks
            for (Task task : col.getTasks()) {
                scrumBoard.getItems().add(task);
            }
        }
    }
    */

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
