package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import projectpilot.App;

public class TaskHistoryController {

    //TODO: HBoxes of the types of changes, each with their own VBox of each change.
    // Should all be inside a scroll view, just like home.
    @FXML
    private Button btn_backToTask;

    @FXML
    private Label lbl_taskTitle;

    @FXML
    private Label txt_taskHistory;

    @FXML
    void switchToTaskEditor(ActionEvent event) throws IOException {
        App.setRoot("taskEditor");
    }
}
