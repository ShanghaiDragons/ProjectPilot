package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import projectpilot.App;


public class HomeController implements Initializable{

    @FXML
    private Label TaskTitle1;

    @FXML
    private Button ToLogin;

    @FXML
    private Button addColumn;

    @FXML
    private Button addTask;

    @FXML
    private Label columnTitle1;

    @FXML
    private MenuItem item_project1;

    @FXML
    private MenuItem item_project10;

    @FXML
    private MenuItem item_project11;

    @FXML
    private MenuItem item_project12;

    @FXML
    private MenuItem item_project13;

    @FXML
    private MenuItem item_project14;

    @FXML
    private MenuItem item_project15;

    @FXML
    private MenuItem item_project16;

    @FXML
    private MenuItem item_project2;

    @FXML
    private MenuItem item_project3;

    @FXML
    private MenuItem item_project4;

    @FXML
    private MenuItem item_project5;

    @FXML
    private MenuItem item_project6;

    @FXML
    private MenuItem item_project7;

    @FXML
    private MenuItem item_project8;

    @FXML
    private MenuItem item_project9;

    @FXML
    private Label projectTitle;

    @FXML
    private Label taskPriority;

    @FXML
    void addColumnToProject(ActionEvent event) throws IOException{
        App.setRoot("login");
    }

    @FXML
    void addTaskToColumn(ActionEvent event) throws IOException{
        App.setRoot("login");
    }

    @FXML
    void switchToColumnEditor(MouseEvent event) throws IOException{
        App.setRoot("columnEditor");
    }

    @FXML
    void switchToLogin(ActionEvent event) throws IOException{
        App.setRoot("login");
    }

    @FXML
    void switchToProject1(ActionEvent event) throws IOException{
        App.setRoot("home");
    }

    @FXML
    void switchToProjectEditor(MouseEvent event) throws IOException{
        App.setRoot("projectEditor");
    }

    @FXML
    void switchToTaskEditor(MouseEvent event) throws IOException{
        App.setRoot("taskEditor");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TaskTitle1.setText("Test");
    }

}