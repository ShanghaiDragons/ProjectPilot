package controllers;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.ProjectPilotFacade;
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

    @FXML
    private Label lbl_errorMessage;
    
    private ProjectPilotFacade ppf = new ProjectPilotFacade();

    @FXML 
    void addColumnButtonClicked(ActionEvent event) throws IOException{
        String columnName = txt_column_name.getText();
        String selectedSortType= menu_sortType.getValue();

        if(selectedSortType==null || selectedSortType.isEmpty()){
            selectedSortType="Priority";
        }

        if(columnName.isEmpty()){
            showAlert("Column Name is Empty!", "Must enter a value for column name.");
            return;
        }

        if(ppf.addColumn(columnName, selectedSortType, new ArrayList<>(), new ArrayList<>())){
            showAlert("Success", "Column added successfully!");
            lbl_errorMessage.setText("");
            switchToHome(event);
        } else{
            showAlert("Error", "Failed to add the column.");
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
        menu_sortType.setValue("Priority");
    }

    @FXML
    void switchToHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

}
