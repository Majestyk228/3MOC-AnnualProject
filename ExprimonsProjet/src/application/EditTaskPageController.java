package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import modele.Modele;
import objects.ListTask;
import objects.Tag;
import objects.User;
import objects.Task;

public class EditTaskPageController implements Initializable {

	private User user;
	private Task task;
	private ArrayList<Tag> tags;
	private ArrayList<ListTask> lists;
	private Stage stage;
	private HBox taskGUI;

	@FXML
	private TextField TaskName;

	@FXML
	private TextArea TaskDesc;

	@FXML
	private ComboBox<Tag> TaskTag;
	
	@FXML 
	private Button SubmitTaskUpdate;
	
	@FXML
	private ComboBox<ListTask> TaskList;

	public EditTaskPageController(User user, Task task, Stage stage, HBox taskGUI) {
		this.user = user;
		this.task = task;
		this.stage = stage;
		this.taskGUI = taskGUI;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		tags = Modele.getAllTags();
		lists = Modele.getUsersTaskList(user.getIdUser());
		
		TaskName.setText(task.getTitle());
		TaskDesc.setText(task.getDescription());
		for(int i=0 ; i<tags.size() ; i++) {
			if(tags.get(i).getIdTag() == task.getIdTag()) {
				TaskTag.setValue(tags.get(i));
			}
		}
		
		for(int i=0 ; i<lists.size() ; i++) {
			if(lists.get(i).getIdList() == task.getIdList()) {
				TaskList.setValue(lists.get(i));
			}
		}
		
		TaskTag.getItems().addAll(tags);
		TaskList.getItems().addAll(lists);
	}
	
	@FXML
	private void editTask() {
		Task updatedTask = new Task(task.getIdTask(),TaskName.getText(), TaskDesc.getText(),user.getIdUser(),TaskList.getValue().getIdList(),TaskTag.getValue().getIdTag());
		Modele.updateTask(updatedTask);
		Label taskName = (Label)taskGUI.getChildren().get(0);
		taskName.setText(TaskName.getText());
		
		stage.close();
		
	}
}
