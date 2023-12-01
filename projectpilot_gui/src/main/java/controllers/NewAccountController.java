package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import projectpilot.App;

public class NewAccountController {

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_signUp;

    @FXML
    private TextField txt_fName;

    @FXML
    private PasswordField txt_lName;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_username;

    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
}
