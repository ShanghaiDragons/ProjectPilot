package controllers;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.ProjectPilotFacade;
import model.User;
import model.Column;

import projectpilot.App;

public class NewTaskController {
    private ProjectPilotFacade ppf = ProjectPilotFacade.getInstance();
    private User currentUser = ppf.getUser();
    private Column selectedColumn;
    private String status;

    @FXML
    private Button btn_backToHome;

    @FXML
    private Button btn_saveChanges;

    @FXML
    private MenuItem item_assignee1;

    @FXML
    private MenuItem item_assignee2;

    @FXML
    private MenuItem item_priority1;
    
    @FXML
    private MenuItem item_priority2;

    @FXML
    private MenuItem item_priority3;
    
    @FXML
    private Label lbl_assigneeSelection;

    @FXML
    private Label lbl_prioritySelection;

    @FXML
    private Label lbl_statusSelection;

    @FXML 
    private ComboBox<String>menu_status;
    
    @FXML
    private ComboBox<String> menu_assignee;
    
    @FXML
    private ComboBox<String> menu_priority;
    
    @FXML
    private TextField txt_task_description;
    
    @FXML
    private TextField txt_task_name;
    
    @FXML
    void saveChanges(ActionEvent event) throws IOException{
        selectedColumn =ppf.getCurrentProject().getColumn(null);
        String taskName = txt_task_name.getText();
        String taskDescription = txt_task_description.getText();
        String assignee = menu_assignee.getValue();
        String priority = menu_priority.getValue();
        if (taskName.isEmpty()|| assignee== null || priority == null) {
            showAlert("Please fill in all of the fields", "");
            return;
        } 
        
        if(ppf.addTask(selectedColumn, taskName, currentUser, Integer.parseInt(priority), "TODO", taskDescription, new ArrayList<>())){
            showAlert("Success","Changes saved successfully!");
            switchToHome(event);
        }
        else{
        showAlert("Failed to save changes","");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    //LOOK AT CHRIS TAKS HOMECONTROLLER
    @FXML
    void initialize(){
        ArrayList<User> userList = ppf.getUsers();
        ObservableList<String> assigneeOptions = FXCollections.observableArrayList();
        for(User user: userList){
            assigneeOptions.add(user.getUserName());
        }
        menu_assignee.setItems(assigneeOptions);

        ObservableList<String> priorityOptions= FXCollections.observableArrayList("1","2","3");
        menu_priority.setItems(priorityOptions);

        ObservableList<String> statusOptions= FXCollections.observableArrayList("To Do","In Progress","Done");
        menu_status.setItems(statusOptions);


    }

    @FXML
    void setAssignee(ActionEvent event) {

    }

    @FXML
    void setPriority1(ActionEvent event) {
        lbl_prioritySelection.setText("1");
    }

    @FXML
    void setPriority2(ActionEvent event) {
        lbl_prioritySelection.setText("2");
    }

    @FXML
    void setPriority3(ActionEvent event) {
        lbl_prioritySelection.setText("3");
    }

    @FXML
    void switchToHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void saveTask(ActionEvent event) throws IOException {
        //TODO: set current project to the actual current project. Currently null
        // for (int i = 1; i < ppf.getCurrentProject().getColumns().size() + 1; i++) {

        // }
        ppf.addTask(ppf.getCurrentColumn(), txt_task_name.getText(), ppf.getUser(), Integer.parseInt(lbl_prioritySelection.getText()), null, txt_task_description.getText(), null);
        App.setRoot("home");
    }

}
