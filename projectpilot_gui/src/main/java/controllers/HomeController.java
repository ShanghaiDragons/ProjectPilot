package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import projectpilot.App;


public class HomeController { //} implements Initializable{

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
    private Button ToLogin;

    @FXML
    void switchToLogin(ActionEvent event) throws IOException{
        App.setRoot("login");
    }

    @FXML
    void switchToProject1(ActionEvent event) throws IOException{
        App.setRoot("login");
    }

    // @Override
    // public void initialize(URL url, ResourceBundle rb) {
    //     column_title.setText("Test");
    // }
}