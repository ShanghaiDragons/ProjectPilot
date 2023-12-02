package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import projectpilot.App;

public class InviteNewUsersController {

    @FXML
    private Button btn_backToProjectEditor;

    @FXML
    private Button btn_inviteUsers;

    @FXML
    private MenuItem item_collaborator;

    @FXML
    private MenuItem item_scrumMaster;

    @FXML
    private MenuItem item_viewer;

    @FXML
    private Label lbl_taskTitle;

    @FXML
    void setUserType(ActionEvent event) {

    }

    @FXML
    void switchToProjectEditor(ActionEvent event) throws IOException {
        App.setRoot("projectEditor");
    }

}
