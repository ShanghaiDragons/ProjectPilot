package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import projectpilot.App;

public class LoginController {

    @FXML
    private Button NewAccount;

    @FXML
    private Button loginButton;
    
    @FXML
    private void switchToCreateAccount() throws IOException {
        App.setRoot("newaccount");
    }
    
    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
    }

}
