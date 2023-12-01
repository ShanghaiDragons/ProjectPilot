package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import projectpilot.App;

public class TaskEditorController {

    @FXML
    private Button btn_backToHome;

    @FXML
    private Button btn_saveChanges;

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
    void switchToHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

}
