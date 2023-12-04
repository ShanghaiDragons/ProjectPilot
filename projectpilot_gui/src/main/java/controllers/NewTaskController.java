package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import projectpilot.App;

public class NewTaskController {

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
    private Label lbl_prioritySelection;

    @FXML
    private MenuButton menu_assignee;

    @FXML
    private MenuButton menu_priority;

    @FXML
    private TextField txt_task_description;

    @FXML
    private TextField txt_task_name;

    @FXML
    void setAssignee(ActionEvent event) {

    }

    @FXML
    void setPriority(ActionEvent event) {

    }

    @FXML
    void switchToHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

}
