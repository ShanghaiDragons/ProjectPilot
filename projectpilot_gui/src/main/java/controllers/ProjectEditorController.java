package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import projectpilot.App;

public class ProjectEditorController {

    @FXML
    private Button ToLogin;

    @FXML
    private Button saveChanges;

    @FXML
    private TextField txt_project_comments;

    @FXML
    private TextField txt_project_description;

    @FXML
    private TextField txt_project_title;

    @FXML
    private TextField txt_project_users;

    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }

}
