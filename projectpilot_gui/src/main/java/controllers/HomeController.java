package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import projectpilot.App;


public class HomeController {

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
}