package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import projectpilot.App;
import model.*;

public class TaskEditorController implements Initializable{
    private ProjectPilotFacade ppf;
    private Task currentTask;
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
    private Label lbl_comment1;
    @FXML
    private Label lbl_prioritySelection;
    @FXML
    private TextField txt_taskTitle;
    @FXML
    private MenuButton menu_assignee;
    @FXML
    private MenuButton menu_priority;
    @FXML
    private TextField txt_add_comment;
    @FXML
    private TextField txt_task_description;
    @FXML
    private ListView<User> lst_users;
    @FXML
    private ListView<Integer> lst_priority;

    private static final int PRIORITY_NUM = 3;

    /**
     * Initializes the facade to populate the data in the scene
     * @author ctaks
     * @param url unused
     * @param rb unused
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.ppf = ProjectPilotFacade.getInstance();
        this.currentTask = ppf.getCurrentTask();
        txt_taskTitle.setText(currentTask.getName());
        txt_task_description.setText(currentTask.getDescription());
        lbl_prioritySelection.setText(String.valueOf(currentTask.getPriority()));
        lbl_assigneeSelection.setText(currentTask.getAssignee().getUserName());

        setChangeAssignee();
        setChangePriority();

        // save
         btn_saveChanges.setOnAction(event -> {
             try {
                 saveChanges();
                 switchToHome(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    }

    /**
     * Saves the changes made to the task
     * @author ctaks
     */
    private void saveChanges() {
        ppf.getCurrentTask().setName(txt_taskTitle.getText());
        ppf.getCurrentTask().setDescription(txt_task_description.getText());
        
    }

    /**
     * 
     */
    private void displayTask() {

    }

    /**
     * Sets up the assignee dropdown
     * @author ctaks
     */
    private void setChangeAssignee() {
        ObservableList<User> userList = FXCollections.observableArrayList();
        
        for (User user : ppf.getUsers()) {
            userList.add(user);
        }
        lst_users.setItems(userList);
    }

    /**
     * What happens when an assignee is selected
     * @author ctaks
     * @param event mouse click
     * @throws IOException
     */
    @FXML
    private void assigneeSelected(MouseEvent event) throws IOException {
        User selectedUser = lst_users.getSelectionModel().getSelectedItem();
        currentTask.setAssignee(selectedUser);
        ppf.getCurrentTask().setAssignee(selectedUser);
        lbl_assigneeSelection.setText(currentTask.getAssignee().getUserName());
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
        lst_priority.setItems(priorityList);
    }

    /**
     * What happens when a priority is selected
     * @author ctaks
     * @param event mouse click
     * @throws IOException
     */
    @FXML
    private void prioritySelected(MouseEvent event) throws IOException {
        int selectedPriority = lst_priority.getSelectionModel().getSelectedItem();
        currentTask.setPriority(selectedPriority);
        ppf.getCurrentTask().setPriority(selectedPriority);
        lbl_prioritySelection.setText(String.valueOf(selectedPriority));
    }

    @FXML
    void addCommentToTask(ActionEvent event) throws IOException{

    }

    @FXML
    void setAssignee(ActionEvent event) throws IOException{

    }

    @FXML
    void setPriority(ActionEvent event) throws IOException{

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
