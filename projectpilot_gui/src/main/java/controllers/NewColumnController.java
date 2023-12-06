package controllers;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Column;
import model.Project;
import model.ProjectPilotFacade;
//import model.User;
import projectpilot.App;

public class NewColumnController {

    @FXML
    private Button btn_addColumn;

    @FXML
    private Button btn_backToHome;

    @FXML
    private Label lbl_sortTypeSelection;

    @FXML
    private ComboBox<String> menu_sortType;

    @FXML
    private TextField txt_column_name;
    private ProjectPilotFacade ppf = ProjectPilotFacade.getInstance();

    @FXML
    private Label lbl_errorMessage;
    //private User currentUser;


    @FXML 
    void addColumnButtonClicked(ActionEvent event) throws IOException{
        Project currentProject = ppf.getCurrentProject();
        if(currentProject==null){
            showAlert("Error", "No project selected");
            return;
        }
       
        String columnName = txt_column_name.getText();
        String selectedSortType= menu_sortType.getValue();
        

        if(selectedSortType==null || selectedSortType.isEmpty()){
            selectedSortType="Priority";
        }

        if(columnName==null || columnName.isEmpty()){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Must enter a column name!");
            alert.setContentText(null);
            alert.showAndWait();
            
            return;
        }

        if(currentProject.addColumn(new Column(columnName, selectedSortType, new ArrayList<>(), new ArrayList<>()))){
            showAlert("Success", "Column added successfully!");
            switchToHome(event);
        } else{
            showAlert("Error", "Failed to add the column. Project ID: " + currentProject);
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    @FXML
    void initialize(){
        ObservableList<String> sortTypeOptions = FXCollections.observableArrayList("Alphabetical", "Priority Level", "Assignee");
        menu_sortType.setItems(sortTypeOptions);
        menu_sortType.setValue("Priority Level");
    }

    @FXML
    void switchToHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void saveColumn(ActionEvent event) throws IOException {
        //TODO: set current project to the actual current project. Currently null
        ppf.addColumn(txt_column_name.getText(), null, null, null);
        App.setRoot("home");
    }

}
