package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import projectpilot.App;

public class TaskEditorController {


    @FXML
    private Button btn_backToHome;

    @FXML
    private Button btn_saveChanges;

    @FXML
    private MenuItem item_assignee1;

    @FXML
    private MenuItem item_assignee2;

    @FXML
    private MenuItem item_priority1;

    @FXML
    private MenuItem item_priority2;

    @FXML
    private MenuItem item_priority3;

    @FXML
    private Label lbl_assigneeSelection;

    @FXML
    private Label lbl_comment1;

    @FXML
    private Label lbl_prioritySelection;

    @FXML
    private Label lbl_taskTitle;

    @FXML
    private MenuButton menu_assignee;

    @FXML
    private MenuButton menu_priority;

    @FXML
    private TextField txt_add_comment;

    @FXML
    private TextField txt_task_description;

    @FXML
    void addCommentToTask(ActionEvent event) throws IOException{

    }

    @FXML
    void setAssignee(ActionEvent event) throws IOException{

    }

    @FXML
    void setPriority(ActionEvent event) throws IOException{

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
