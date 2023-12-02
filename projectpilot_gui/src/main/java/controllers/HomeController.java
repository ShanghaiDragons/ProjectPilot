package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import projectpilot.App;

public class HomeController {

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
