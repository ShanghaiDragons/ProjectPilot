package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
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
import projectpilot.App;
import model.*;

public class LoginController implements Initializable{

    private ProjectPilotFacade ppf = ProjectPilotFacade.getInstance();
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_newAccount;
    @FXML
    private Label lbl_errorMessage;
    @FXML
    private ImageView projectpilot_pic;
    @FXML
    private ImageView background_pic;
    @FXML
    private PasswordField txt_password;
    @FXML
    private TextField txt_username;

    @FXML
    void switchToCreateAccount(ActionEvent event) throws IOException {
        App.setRoot("newAccount");
    }

    @FXML
    void switchToHomePage(ActionEvent event) throws IOException {
        if (ppf.login(txt_username.getText(), txt_password.getText())){
            showAlert("Success!", "Logging you in...");
            App.setRoot("home");
        }
        else if(txt_username.getText().isEmpty() || txt_password.getText().isEmpty()){
            showAlert("Please enter login credentials", null);
        } else {
            showError("Unable to login", "Incorrect password or no matching username.");
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Image PPlogo = new Image(getClass().getResourceAsStream("/images/ProjectPilotLoginLogo.png"));
        projectpilot_pic.setImage(PPlogo);
        Image background = new Image(getClass().getResourceAsStream("/images/background.jpg"));
        background_pic.setImage(background);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
