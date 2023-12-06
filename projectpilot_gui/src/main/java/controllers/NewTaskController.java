package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import model.ProjectPilotFacade;
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
    private ProjectPilotFacade ppf = ProjectPilotFacade.getInstance();

    @FXML
    void setAssignee(ActionEvent event) {

    }

    @FXML
    void setPriority1(ActionEvent event) {
        lbl_prioritySelection.setText("1");
    }

    @FXML
    void setPriority2(ActionEvent event) {
        lbl_prioritySelection.setText("2");
    }

    @FXML
    void setPriority3(ActionEvent event) {
        lbl_prioritySelection.setText("3");
    }

    @FXML
    void switchToHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void saveTask(ActionEvent event) throws IOException {
        //TODO: set current project to the actual current project. Currently null
        // for (int i = 1; i < ppf.getCurrentProject().getColumns().size() + 1; i++) {

        // }
        ppf.addTask(ppf.getCurrentColumn(), txt_task_name.getText(), ppf.getUser(), Integer.parseInt(lbl_prioritySelection.getText()), null, txt_task_description.getText(), null);
        App.setRoot("home");
    }

}
