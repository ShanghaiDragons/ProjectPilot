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
    private MenuItem ProjectsItem1;

    @FXML
    private MenuItem ProjectsItem11;

    @FXML
    private MenuItem ProjectsItem110;

    @FXML
    private MenuItem ProjectsItem111;

    @FXML
    private MenuItem ProjectsItem112;

    @FXML
    private MenuItem ProjectsItem113;

    @FXML
    private MenuItem ProjectsItem114;

    @FXML
    private MenuItem ProjectsItem115;

    @FXML
    private MenuItem ProjectsItem12;

    @FXML
    private MenuItem ProjectsItem13;

    @FXML
    private MenuItem ProjectsItem14;

    @FXML
    private MenuItem ProjectsItem15;

    @FXML
    private MenuItem ProjectsItem16;

    @FXML
    private MenuItem ProjectsItem17;

    @FXML
    private MenuItem ProjectsItem18;

    @FXML
    private MenuItem ProjectsItem19;

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
        App.setRoot("login");
    }

    @FXML
    void switchToLogin(ActionEvent event) throws IOException{
        App.setRoot("login");
    }

    @FXML
    void switchToProject1(ActionEvent event) throws IOException{
        App.setRoot("login");
    }

    @FXML
    void switchToProjectEditor(MouseEvent event) throws IOException{
        App.setRoot("projectEditor");
    }

    @FXML
    void switchToTaskEditor(MouseEvent event) throws IOException{
        App.setRoot("login");
    }









    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TaskTitle1.setText("Test");
    }
}