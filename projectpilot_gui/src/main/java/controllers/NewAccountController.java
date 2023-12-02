package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.*;
import projectpilot.App;

public class NewAccountController {

    private ProjectPilotFacade ppf = new ProjectPilotFacade();

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_signUp;

    @FXML
    private TextField txt_fName;

    @FXML
    private TextField txt_lName;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_username;

    @FXML
    private void switchToHome() throws IOException {
        if (ppf.createAccount(txt_fName.getText(), txt_lName.getText(), txt_username.getText(), txt_password.getText())){
            ppf.saveUsers();
            App.setRoot("home");
        }
        
        // txt_fName.clear();       if any are invalid/duplicate usernames, clear
        // txt_lName.clear();
        // txt_username.clear();
        // txt_password.clear();
    }

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
}
