package controllers;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import projectpilot.App;
import model.*;

public class TaskEditorController implements Initializable{
    private ProjectPilotFacade ppf;
    private Project currentProject;
    private Column currentColumn;
    private Column moveColumn;
    private Task currentTask;
    private static final int PRIORITY_NUM = 3;
    @FXML
    private Button btn_backToHome;
    @FXML
    private Button btn_saveChanges;
    @FXML
    private Label lbl_assigneeSelection;
    @FXML
    private Label lbl_comment1;
    @FXML
    private Label lbl_prioritySelection;
    @FXML
    private ListView<User> list_assignee;
    @FXML
    private ListView<Integer> list_priority;
    @FXML
    private TitledPane titledPane_assignee;
    @FXML
    private TitledPane titledPane_priority;
    @FXML
    private TextField txt_add_comment;
    @FXML
    private TextField txt_taskTitle;
    @FXML
    private TextField txt_task_description;
    @FXML
    private TextField txt_taskStatus;
    @FXML
    private ImageView background_pic;
    @FXML
    private VBox commentBox;
    @FXML
    private Button addComment;
    @FXML
    private Label lbl_column;
    @FXML
    private TitledPane titledPane_column;
    @FXML
    private ListView<Column> list_column;

    /**
     * Initializes the facade to populate the data in the scene
     * @author ctaks
     * @param url unused
     * @param rb unused
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.ppf = ProjectPilotFacade.getInstance();
        this.currentProject = ppf.getCurrentProject();
        this.currentColumn = this.moveColumn = ppf.getCurrentColumn();
        this.currentTask = ppf.getCurrentTask();
        txt_taskTitle.setText(currentTask.getName());
        txt_task_description.setText(currentTask.getDescription());
        txt_taskStatus.setText(currentTask.getStatus());
        lbl_prioritySelection.setText(String.valueOf(currentTask.getPriority()));
        lbl_assigneeSelection.setText(currentTask.getAssignee().getUserName());
        lbl_column.setText(currentColumn.getName());
        setChangePriority();
        setChangeAssignee();
        setChangeColumn();
        setComments();

        list_priority.setOnMouseClicked(event -> {
            try {
                priorityItemSelected(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        list_assignee.setOnMouseClicked(event -> {
            try {
                assigneeItemSelected(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        list_column.setOnMouseClicked(event -> {
            try {
                columnItemSelected(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
       
        Image background = new Image(getClass().getResourceAsStream("/images/background.jpg"));
        background_pic.setImage(background);
    }

    /**
     * Saves the edited task to ProjectPilotFacade's currentTask
     * @param event
     * @throws IOException
     */
    @FXML
    private void saveChanges(ActionEvent event) throws IOException {
        currentTask.setName(txt_taskTitle.getText());
        currentTask.setDescription(txt_task_description.getText());
        currentTask.setStatus(txt_taskStatus.getText());
        ppf.setCurrentTask(currentTask);

        currentProject.moveTask(currentColumn, moveColumn, currentTask);

        switchToHome(event);
    }

    /**
     * 
     * @author duayne
     * @param event mouse click
     * @throws IOException
     */
    @FXML
    private void expandLists(MouseEvent event) throws IOException {
        if (titledPane_priority.isExpanded()) {
            titledPane_priority.toFront();
            return;
        }
        if (titledPane_assignee.isExpanded()) {
            titledPane_assignee.toFront();
            return;
        }
        if (titledPane_column.isExpanded()) {
            titledPane_column.toFront();
            return;
        }
        if (!(titledPane_column.isExpanded() && titledPane_assignee.isExpanded() && titledPane_priority.isExpanded())) {
            titledPane_priority.toFront();
            titledPane_assignee.toFront();
            titledPane_column.toFront();
            background_pic.toBack();
        }
    }

    /**
     * What happens when a priority is selected
     * @author ctaks
     * @param event mouse click
     * @throws IOException
     */
    @FXML
    private void priorityItemSelected(MouseEvent event) throws IOException {
        int selectedPriority = list_priority.getSelectionModel().getSelectedItem();
        lbl_prioritySelection.setText(String.valueOf(selectedPriority));
        currentTask.setPriority(selectedPriority);
    }

    /**
     * What happens when an assignee is selected
     * @author ctaks
     * @param event mouse click
     * @throws IOException
     */
    @FXML
    private void assigneeItemSelected(MouseEvent event) throws IOException {
        User selectedUser = list_assignee.getSelectionModel().getSelectedItem();
        currentTask.setAssignee(selectedUser);
        lbl_assigneeSelection.setText(currentTask.getAssignee().getUserName());
    }

    /**
     * What happens when a column is selected
     * @author ctaks 
     * @param event mouse click
     * @throws IOException
     */
    @FXML
    private void columnItemSelected(MouseEvent event) throws IOException {
        Column selectedColumn = list_column.getSelectionModel().getSelectedItem();
        moveColumn = selectedColumn;
        lbl_column.setText(moveColumn.getName());
    }

    /**
     * Sets up the priority dropdown
     * @author ctaks
     */
    private void setChangePriority() {
        ObservableList<Integer> priorityList = FXCollections.observableArrayList();

        for (int i = 1; i <= PRIORITY_NUM; i++) {
            Integer wrappedI = i;
            priorityList.add(wrappedI);
        }
        list_priority.setItems(priorityList);
    }

     /**
     * Sets up the assignee dropdown
     * @author ctaks
     */
    private void setChangeAssignee() {
        ObservableList<User> userList = FXCollections.observableArrayList();
        
        // for (User user : ppf.getCurrentProject().getTeam()) {
        for (User user : ppf.getUsers()) {
            userList.add(user);
        }
        list_assignee.setItems(userList);
    }

    /**
     * Sets up the column dropdown
     * @author ctaks
     */
    private void setChangeColumn() {
        ObservableList<Column> columnList = FXCollections.observableArrayList();

        for (Column col : currentProject.getColumns()) {
            if (!col.getID().equals(currentColumn.getID())) {
                columnList.add(col);
            }
        }
        list_column.setItems(columnList);
    }

    /**
     * Sets the comments
     * @author ctaks
     */
    private void setComments() {
        commentBox.getChildren().clear();
        if (!currentTask.getComments().isEmpty()) {
            lbl_comment1.setText("");
            for (Comment comment : currentTask.getComments()) {
                commentBox.getChildren().add(createComment(comment));
            }
        }
    }

    /**
     * Creates a comment
     * @author ctaks
     * @param com comment to be turned into a VBox of the comment
     * @return VBox of the comment
     */
    private VBox createComment(Comment com) {
        VBox comment = new VBox();
        Label userAndDate = new Label(com.getUser().getUserName()+" "+com.getDateClean());
        userAndDate.setWrapText(true);
        Label message = new Label(com.getMessage());
        message.setWrapText(true);
        comment.getChildren().addAll(userAndDate, message);

        comment.setStyle("-fx-border-color: lightgray; -fx-border-width: 1;");
        comment.setPadding(new Insets(2, 2, 2, 2));

        return comment;
    }

    /**
     * Adds a comment to the task
     * @author ctaks
     * @param event mouse click
     * @throws IOException
     */
    @FXML
    void addCommentToTask(ActionEvent event) throws IOException {
        String message = txt_add_comment.getText();
        if (!message.isBlank()) {
            currentTask.addComment(ppf.getUser(), message);
            setComments();
        }
        txt_add_comment.clear();
    }

    @FXML
    void switchToHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void switchToTaskHistory(ActionEvent event) throws IOException {
        App.setRoot("taskHistory");
    }


}
