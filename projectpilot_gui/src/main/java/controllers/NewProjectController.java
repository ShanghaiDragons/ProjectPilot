package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.converter.LocalDateStringConverter;
import projectpilot.App;
import model.*;

public class NewProjectController implements Initializable{

    @FXML
    private Button btn_addColumn;

    @FXML
    private Button btn_addUserToProject;

    @FXML
    private Button btn_backToHome;

    @FXML
    private Button btn_saveChanges;

    @FXML
    private TextField txt_column;

    @FXML
    private TextField txt_column2;

    @FXML
    private TextField txt_column3;

    @FXML
    private TextField txt_project_description;

    @FXML
    private TextField txt_project_title;
    private ProjectPilotFacade ppf = ProjectPilotFacade.getInstance();

    @FXML
    void addColumn(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void switchToHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void saveChanges(ActionEvent event) throws IOException {
        ppf.addProject(txt_project_title.getText(), LocalDate.now(), LocalDate.now(), null, ppf.getUser(), null, null, null, null);
            ppf.saveProjects();
            App.setRoot("home");
        }
    

    @FXML
    void switchToInviteUsers(ActionEvent event) throws IOException {
        App.setRoot("inviteNewUsers");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

}
