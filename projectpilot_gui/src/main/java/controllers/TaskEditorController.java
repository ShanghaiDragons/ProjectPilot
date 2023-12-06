package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import projectpilot.App;
import model.*;

public class TaskEditorController implements Initializable{
    private ProjectPilotFacade ppf;
    private Task currentTask;
    @FXML
    private Button btn_backToHome;

    @FXML
    private Button btn_saveChanges;

    @FXML
    private Label lbl_assigneeSelection;

    @FXML
    private Label lbl_comment1;

    @FXML
    private Label lbl_prioritySelection;

    @FXML
    private ListView<String> list_assignee;

    @FXML
    private ListView<Integer> list_priority;

    @FXML
    private TitledPane titledPane_assignee;

    @FXML
    private TitledPane titledPane_priority;

    @FXML
    private TextField txt_add_comment;

    @FXML
    private TextField txt_taskTitle;

    @FXML
    private TextField txt_task_description;
    @FXML
    private ImageView background_pic;
    private ObservableList<Integer> priorities = FXCollections.observableArrayList();
    private ObservableList<String> users = FXCollections.observableArrayList();

    /**
     * Initializes the facade to populate the data in the scene
     * @author ctaks
     * @param url unused
     * @param rb unused
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.ppf = ProjectPilotFacade.getInstance();
        this.currentTask = ppf.getCurrentTask();
        txt_taskTitle.setText(currentTask.getName());
        txt_task_description.setText(currentTask.getDescription());
        lbl_prioritySelection.setText(String.valueOf(currentTask.getPriority()));
        lbl_assigneeSelection.setText(currentTask.getAssignee().getUserName());
        for ( int i = 1; i < 4; i++ )
            priorities.add(i);
        for (User team : ppf.getUsers() )
            users.add(team.getFirstName() + " " + team.getLastName());
        list_priority.setItems(priorities);
        list_assignee.setItems(users);

        list_priority.setOnMouseClicked(event -> {
            try {
                priorityItemSelected(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        list_assignee.setOnMouseClicked(event -> {
            try {
                assigneeItemSelected(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // save
        /**
         * 
         btn_saveChanges.setOnAction(event -> {
             try {
                 saveChanges();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            */
        Image background = new Image(getClass().getResourceAsStream("/images/background.jpg"));
        background_pic.setImage(background);
    }

    @FXML
    private void saveChanges(ActionEvent event) throws IOException {
        User assignee = null;
        for (User user : ppf.getUsers())
            if (user.getUserName().equals(lbl_assigneeSelection.getText()))
                assignee = user;
        currentTask.setName(txt_taskTitle.getText());
        currentTask.setDescription(txt_task_description.getText());
        currentTask.setPriority(Integer.parseInt(lbl_prioritySelection.getText()));
        currentTask.setAssignee(assignee);
        switchToHome(event);
    }

    /**
     * 
     */
    private void displayTask() {

    }

    @FXML
    private void expandLists(MouseEvent event) throws IOException {
        if (titledPane_priority.isExpanded())
            titledPane_priority.toFront();
        else if (!titledPane_priority.isExpanded()) {
            titledPane_priority.toBack();
            background_pic.toBack();
        }
    }

    @FXML
    private void priorityItemSelected(MouseEvent event) throws IOException {
        String priority = list_priority.getSelectionModel().getSelectedItem().toString();
        lbl_prioritySelection.setText(priority);
    }

    @FXML
    private void assigneeItemSelected(MouseEvent event) throws IOException {
        String assignee = "";
        for (User user : ppf.getUsers())
            if (list_assignee.getSelectionModel().getSelectedItem().toString().equals(user.getFirstName() + " " + user.getLastName()))
                assignee = user.getUserName();
        
        lbl_assigneeSelection.setText(assignee);
    }

    @FXML
    void addCommentToTask(ActionEvent event) throws IOException{

    }

    @FXML
    void setAssignee(ActionEvent event) throws IOException{

    }

    @FXML
    void switchToHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void switchToTaskHistory(ActionEvent event) throws IOException {
        App.setRoot("taskHistory");
    }


}
