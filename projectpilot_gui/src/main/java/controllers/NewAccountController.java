package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;
import projectpilot.App;

public class NewAccountController implements Initializable{

    private ProjectPilotFacade ppf = ProjectPilotFacade.getInstance();
    @FXML
    private ImageView background_pic;

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_signUp;

    @FXML
    private ImageView projectpilot_pic;

    @FXML
    private TextField txt_fName;

    @FXML
    private TextField txt_lName;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_username;

    @FXML
    private Label lbl_errorMessage;

    @FXML
    private void switchToHome() throws IOException {
        if (ppf.createAccount(txt_fName.getText(), txt_lName.getText(), txt_username.getText(), txt_password.getText())){
            ppf.saveUsers();
            showAlert("Success!", "You have successfully created a new account!");
            App.setRoot("home");
        } else {
            showERROR("Error!", "Password must be:\n atleast 6 characters\ncontain atleast one letter [a-Z]\ncontain atleast one number [0-9]");
        }
        
        
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showERROR(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Image PPlogo = new Image(getClass().getResourceAsStream("/images/ProjectPilotLoginLogo.png"));
        projectpilot_pic.setImage(PPlogo);
        Image background = new Image(getClass().getResourceAsStream("/images/background.jpg"));
        background_pic.setImage(background);
    }
}
