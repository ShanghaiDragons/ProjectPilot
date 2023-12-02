package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import projectpilot.App;

public class NewProjectController {

    @FXML
    private Button btn_addColumn;

    @FXML
    private Button btn_addUserToProject;

    @FXML
    private Button btn_backToHome;

    @FXML
    private Button btn_saveChanges;

    @FXML
    private TextField txt_column;

    @FXML
    private TextField txt_column2;

    @FXML
    private TextField txt_column3;

    @FXML
    private TextField txt_project_description;

    @FXML
    private TextField txt_project_title;

    @FXML
    void addColumn(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void switchToHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void switchToInviteUsers(ActionEvent event) throws IOException {
        App.setRoot("inviteNewUsers");
    }

}
