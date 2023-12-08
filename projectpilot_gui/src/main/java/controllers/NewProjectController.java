package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.LocalDateStringConverter;
import projectpilot.App;
import model.*;

public class NewProjectController implements Initializable{
    private ProjectPilotFacade ppf;
    private Project newProject;
    @FXML
    private ImageView background;
    @FXML
    private Button btn_backToHome;
    @FXML
    private Button btn_saveChanges;
    @FXML
    private VBox collaboratorBox;
    @FXML
    private Label lbl_scrum_master;
    @FXML
    private ListView<User> list_collaborator;
    @FXML
    private ListView<User> list_scrum_master;
    @FXML
    private ListView<User> list_viewer;
    @FXML
    private TitledPane titledPane_collaborator;
    @FXML
    private TitledPane titledPane_scrum_master;
    @FXML
    private TitledPane titledPane_viewer;
    @FXML
    private TextField txt_end_sprint;
    @FXML
    private TextField txt_project_title;
    @FXML
    private TextField txt_start_sprint;
    @FXML
    private VBox viewerBox;
    @FXML
    private ListView<User> list_selected_collaborators;
    @FXML
    private ListView<User> list_selected_viewers;


    /**
     * Initializes the new project screen
     * @author ctaks
     * @param URL unused
     * @param ResourceBundle unused
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ppf = ProjectPilotFacade.getInstance();
        newProject = new Project(null, null, null, null, null, null, null, null, null);
        setAddScrumMaster();
        setAddCollaborators();
        setAddViewers();
        
        list_selected_collaborators = new ListView<User>();
        list_selected_viewers = new ListView<User>();
        collaboratorBox.getChildren().add(list_selected_collaborators);
        viewerBox.getChildren().add(list_selected_viewers);

        list_scrum_master.setOnMouseClicked(event -> {
            try {
                scrumMasterItemSelected(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        list_collaborator.setOnMouseClicked(event -> {
            try {
                collaboratorItemSelected(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        list_viewer.setOnMouseClicked(event -> {
            try {
                viewerItemSelected(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Image backgroundpic = new Image(getClass().getResourceAsStream("/images/background.jpg"));
        background.setImage(backgroundpic);
    }

    /**
     * sets up the scrum master userlist dropdown
     * @author ctaks
     */
    private void setAddScrumMaster() {
        ObservableList<User> userList = FXCollections.observableArrayList();

        for (User user : ppf.getUsers()) {
            userList.add(user);
        }
        list_scrum_master.setItems(userList);
    }

    /**
     * Sets up the collaborator userlist dropdown
     * @author ctaks
     */
    private void setAddCollaborators() {
        ObservableList<User> userList = FXCollections.observableArrayList();

        for (User user : ppf.getUsers()) {
            userList.add(user);
        }
        list_collaborator.setItems(userList);
    }

    /**
     * Sets up the viewer userlist dropdown
     * @author ctaks
     */
    private void setAddViewers() {
        ObservableList<User> userList = FXCollections.observableArrayList();

        for (User user : ppf.getUsers()) {
            userList.add(user);
        }
        list_viewer.setItems(userList);
    }

    /**
     * What happens when a scrum master is selecteds
     * @author ctaks
     * @param event mouse click
     * @throws IOException
     */
    @FXML
    private void scrumMasterItemSelected(MouseEvent event) throws IOException {
        User selectedUser = list_scrum_master.getSelectionModel().getSelectedItem();
        newProject.setScrumMaster(selectedUser);
        lbl_scrum_master.setText(selectedUser.getUserName());
    }

    /**
     * What happens when a collaborator is selected
     * @author ctaks
     * @param event mouse click
     * @throws IOException
     */
    @FXML
    private void collaboratorItemSelected(MouseEvent event) throws IOException {
        User selectedUser = list_collaborator.getSelectionModel().getSelectedItem();
        newProject.addTeamMember(selectedUser, UserType.COLLABORATOR);
        list_selected_collaborators.getItems().add(selectedUser);
    }

    /**
     * What happens when a collaborator is selected
     * @author ctaks
     * @param event mouse click
     * @throws IOException
     */
    @FXML
    private void viewerItemSelected(MouseEvent event) throws IOException {
        User selectedUser = list_viewer.getSelectionModel().getSelectedItem();
        newProject.addTeamMember(selectedUser, UserType.VIEWER);
        list_selected_viewers.getItems().add(selectedUser);
    }

     /**
     * 
     * @author chris
     * @param event mouse click
     * @throws IOException
     */
    @FXML
    private void expandLists(MouseEvent event) throws IOException {
        if (titledPane_scrum_master.isExpanded()) {
            titledPane_scrum_master.toFront();
            return;
        }
        if (titledPane_collaborator.isExpanded()) {
            titledPane_collaborator.toFront();
            return;
        }
        if (titledPane_viewer.isExpanded()) {
            titledPane_viewer.toFront();
            return;
        }
        if (!(titledPane_viewer.isExpanded() && titledPane_collaborator.isExpanded() && titledPane_scrum_master.isExpanded())) {
            titledPane_scrum_master.toFront();
            titledPane_collaborator.toFront();
            titledPane_viewer.toFront();
            background.toBack();
        }
    }


    @FXML
    void addColumn(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void switchToHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    /**
     * saves the new project and adds it to ProjectPilot
     * @author ctaks
     * @param event button click
     * @throws IOException
     */
    @FXML
    void saveChanges(ActionEvent event) throws IOException {
        LocalDate start = LocalDate.now(); // TODO: make the start and end sprints parse their respective labels turn into LocalDatses
        LocalDate end = LocalDate.now().plusDays(14);
        ppf.addProject(txt_project_title.getText(), start, end, newProject.getTeam(), newProject.getScrumMaster(), newProject.getCollaborators(), newProject.getViewers(), null, null);
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

}
