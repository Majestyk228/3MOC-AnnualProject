package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Modele;
import objects.Tag;
import objects.Task;
import objects.User;

public class CreateTaskModalController implements Initializable {
	
	private User user;
	private int idList;
	private ArrayList<Tag> tags;
	private Stage modal;
	private Task lastCreatedTask;
	
	@FXML
	private TextField TaskName;
	
	@FXML
	private TextArea TaskDesc;
	
	@FXML
	private ComboBox<Tag> TaskTag;
	
	@FXML
	private Button SubmitVote;
	

	public CreateTaskModalController(User user, int idList, ArrayList<Tag> tags, Stage modal) {
		// TODO Auto-generated constructor stub
		this.user = user;
		this.idList = idList;
		this.tags = tags;
		this.modal = modal;
	}
	
	public void submitTask() {
		// GET STRINGS FROM TEXTFIELDS
		String taskName = TaskName.getText();
		String taskDesc = TaskDesc.getText();
		Tag selectedTag = TaskTag.getValue();
		
		Task taskToInsert = new Task(0, taskName, taskDesc, user.getIdUser(), idList,selectedTag.getIdTag());
		
		Modele.insertTask(taskToInsert);
		
		lastCreatedTask = Modele.getLastCreatedTask();
		
		modal.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println("init");
		
		TaskTag.getItems().addAll(tags);		
	}

	public Task getLastCreatedTask() {
		return lastCreatedTask;
	}
}
