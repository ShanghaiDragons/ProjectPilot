package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import projectpilot.App;

public class LoginController {

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_newAccount;

    @FXML
    private ImageView projectpilot_pic;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_username;

    @FXML
    void switchToCreateAccount(ActionEvent event) {
        try {
            App.setRoot("newaccount");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void switchToHomePage(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

}
/*

package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import projectpilot.App;

public class LoginController implements Initializable{
    
    @FXML
    private Button loginButton;

    @FXML
    private Button NewAccount;

    @FXML
    private ImageView projectpilot_pic;

    // @FXML
    // private Image ImageView​(String url) {
    //     Image pic = new Image(url);
    //     return pic;
    // }
    
    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void switchToCreateAccount() throws IOException {
        App.setRoot("newaccount");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Image pic = new Image("../images/abstract-white-background-dynamic_29865-2544.jpg");
        // arg0 = new URL("../images/abstract-white-background-dynamic_29865-2544.jpg");
        // ImageView​("images/Screenshot 2023-11-27 135552.png");
        // projectpilot_pic.setImage(new Image("images/Screenshot 2023-11-27 135552.png"));
        // projectpilot_pic.getImage();
    }

}
*/