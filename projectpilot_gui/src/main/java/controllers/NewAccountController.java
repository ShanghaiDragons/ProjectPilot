package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import projectpilot.App;

public class NewAccountController {
    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
}
