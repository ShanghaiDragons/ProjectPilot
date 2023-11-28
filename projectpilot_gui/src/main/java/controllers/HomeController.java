package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import projectpilot.App;


public class HomeController implements Initializable{

    @FXML
    private TextField column_title;

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        column_title.setText("Test");
    }
}