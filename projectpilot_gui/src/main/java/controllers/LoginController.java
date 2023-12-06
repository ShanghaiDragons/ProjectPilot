package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
            App.setRoot("home");
        } else {
            lbl_errorMessage.setVisible(true);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Image PPlogo = new Image(getClass().getResourceAsStream("/images/ProjectPilotLoginLogo.png"));
        projectpilot_pic.setImage(PPlogo);
        Image background = new Image(getClass().getResourceAsStream("/images/background.jpg"));
        background_pic.setImage(background);
    }

}
