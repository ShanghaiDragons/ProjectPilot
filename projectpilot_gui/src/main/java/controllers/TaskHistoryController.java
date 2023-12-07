package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import projectpilot.App;
import model.*;

public class TaskHistoryController implements Initializable {
    private ProjectPilotFacade ppf = ProjectPilotFacade.getInstance();
    private TaskHistory taskHistory;
    @FXML
    private ScrollPane taskHistoryPane;
    @FXML
    private HBox taskHistoryBox;

    /**
     * Initializes the facade to populate the data in the scene
     * @author ctaks
     * @param url unused
     * @param rb unused
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.taskHistory = ppf.getCurrentTask().getTaskHistory();
        buildTaskHistory();
        //TODO: HBoxes of the types of changes, each with their own VBox of each change.
        // Should all be inside a scroll view, just like home.
    }

    /**
     * Builds the task history box
     * @author ctaks
     */
    private void buildTaskHistory() {
        taskHistoryBox.getChildren().clear();
        taskHistoryBox.getChildren().add(createChangeColumn(taskHistory.getNameChanges(), "Name Changes"));
        taskHistoryBox.getChildren().add(createChangeColumn(taskHistory.getDescriptionChanges(), "Description Changes"));
        taskHistoryBox.getChildren().add(createChangeColumn(taskHistory.getStatusChanges(), "Status Changes"));
        taskHistoryBox.getChildren().add(createChangeColumn(taskHistory.getPriorityChanges(), "Priority Changes"));
        taskHistoryBox.getChildren().add(createChangeColumn(taskHistory.getAssigneeChanges(), "Assignee Changes"));
        taskHistoryBox.getChildren().add(createChangeColumn(taskHistory.getMoveChanges(), "Move Changes"));
    }

    /**
     * Builds a column of a change
     * @author ctaks
     * @param changes ArrayList<String> of changes to be added
     * @param title String of the column's title
     * @return VBox of the change column
     */
    private VBox createChangeColumn(ArrayList<String> changes, String title) {
        VBox changeColumn = new VBox();
        Label changeTitle = new Label(title);
        changeColumn.getChildren().add(changeTitle);
        ObservableList<String> changeList = FXCollections.observableArrayList();
        ListView<String> changesList = new ListView<String>();
        for (String change : changes) {
            // Label lbl_change = new Label(change);
            // lbl_change.setStyle("-fx-border-color: lightgray; -fx-border-width: 1;");
            // lbl_change.setPadding(new Insets(1, 1, 1, 1));
            // changeColumn.getChildren().add(lbl_change);
            changeList.add(change);
        }
        changesList.setItems(changeList);
        //double height = (taskHistoryPane.getPrefHeight()-0);
        changesList.setPrefHeight((taskHistoryPane.getPrefHeight()-50));
        changesList.setPrefWidth(350);
        //changesList.prefWidthProperty().bind(computeMaxWidth(changesList));
        changeColumn.getChildren().add(changesList);
        changeColumn.setStyle("-fx-border-color: lightgray; -fx-border-width: 1;");
        changeColumn.setPadding(new Insets(10, 10, 10, 10));
        return changeColumn;
    }

    @FXML
    private Button btn_backToTask;

    @FXML
    private Label lbl_taskTitle;

    @FXML
    private Label txt_taskHistory;

    @FXML
    void switchToTaskEditor(ActionEvent event) throws IOException {
        App.setRoot("taskEditor");
    }
}
