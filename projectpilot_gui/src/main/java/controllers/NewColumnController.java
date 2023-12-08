package controllers;
import java.io.IOException;
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
    private ProjectPilotFacade ppf = ProjectPilotFacade.getInstance();

    @FXML
    private Label lbl_errorMessage;
   

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
        String columnName = txt_column_name.getText();

        if(columnName==null || columnName.isEmpty()){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Must enter a column name!");
            alert.setContentText(null);
            alert.showAndWait();
            
            return;
        }

        if(ppf.addColumn(txt_column_name.getText(), null, null, null)){
            App.setRoot("home");
        } else{
            showAlert("Error", "Failed to add the column.");
        }
    
    }

}
