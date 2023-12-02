package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import projectpilot.App;

public class NewColumnController {

    @FXML
    private Button btn_addColumn;

    @FXML
    private Button btn_backToHome;

    @FXML
    private Label lbl_sortTypeSelection;

    @FXML
    private MenuButton menu_sortType;

    @FXML
    private TextField txt_column_description;

    @FXML
    private TextField txt_column_name;

    @FXML
    void switchToHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

}
