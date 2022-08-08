package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import controllers.ListTask;
import controllers.Task;
import controllers.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modele.Modele;

public class HomeController implements Initializable {

	private Stage stage;
	private Scene scene;
	@FXML
	private Text UsernameText;
	@FXML
	private HBox ListButton;
	@FXML
	private HBox AccountButton;
	@FXML
	private HBox SettingsButton;
	@FXML
	private Button NewListButton;
	@FXML
	private HBox HostList; // THIS IS THE LAYOUT THAT HOSTS ALL THE LISTS

	private User user;

	private ArrayList<ListTask> lists;

	private HashMap<Integer, String> urlToTag;

	public HomeController(User user) {
		this.user = user;
	}

	public void createDialogue(ActionEvent event) {
		// create a text input dialog
		TextInputDialog td = new TextInputDialog("Nom de la liste...");

		// setHeaderText
		td.setHeaderText("Nommez la nouvelle liste");

		Optional<String> result = td.showAndWait();

		result.ifPresent(listName -> {
			createList(listName);
		});
	}
	
	
	public void createDialogueTask(ActionEvent event) {
		// create a text input dialog
		TextInputDialog td = new TextInputDialog("Nom de la tâche...");

		// setHeaderText
		td.setHeaderText("Nommez la nouvelle tâche");

		Optional<String> result = td.showAndWait();

		result.ifPresent(listName -> {
			createTask(((Parent)event.getSource()));
		});
	}

	public void createList(String listName) {

		Button button = new Button();
		button.setId("AddTaskButton");
		button.setText("Nouvelle tâche");
		button.setFont(new Font("System Bold", 17));
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				createTask(button.getParent());
				// System.out.println("Prout");
			}
		});

		VBox vbox = new VBox();
		vbox.prefWidth(182);
		vbox.prefHeight(450);
		vbox.getChildren().add(button);
		vbox.setAlignment(Pos.TOP_CENTER);

		AnchorPane anchor = new AnchorPane();
		anchor.prefWidth(182);
		anchor.prefHeight(450);

		TitledPane tpane = new TitledPane(listName, vbox);
		tpane.prefWidth(202);
		tpane.prefHeight(450);
		tpane.setFont(new Font("System Bold", 22));

		Accordion accord = new Accordion();
		accord.getPanes().add(tpane);

		// ADD accor in HostList
		HostList.getChildren().add(HostList.getChildren().indexOf(NewListButton), accord);
		// addTaskToList(vbox, lists);
	}

	public void createListWithTask(String listName, ArrayList<Task> tasks) {

		Button button = new Button();
		button.setId("AddTaskButton");
		button.setText("Nouvelle tâche");
		button.setFont(new Font("System Bold", 17));
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				createTask(button.getParent());
				// System.out.println("Prout");
			}
		});

		VBox vbox = new VBox();
		vbox.prefWidth(182);
		vbox.prefHeight(450);
		vbox.getChildren().add(button);
		vbox.setAlignment(Pos.TOP_CENTER);

		AnchorPane anchor = new AnchorPane();
		anchor.prefWidth(182);
		anchor.prefHeight(450);

		TitledPane tpane = new TitledPane(listName, vbox);
		tpane.prefWidth(202);
		tpane.prefHeight(450);
		tpane.setFont(new Font("System Bold", 22));

		Accordion accord = new Accordion();
		accord.getPanes().add(tpane);

		// ADD accor in HostList
		HostList.getChildren().add(HostList.getChildren().indexOf(NewListButton), accord);
		// addTaskToList(vbox, lists);

		
		for(int i=0 ; i<tasks.size(); i++) {
			//  TODO : code
			
			HBox task = new HBox();
			task.prefWidth(200);
			task.prefHeight(50);
			// HBox.setMargin(task, new Insets(550, 0, 0, 50));

			Label nameTask = new Label(tasks.get(i).getTitle());
			nameTask.setId("titleTask");
			nameTask.setFont(new Font("System Bold", 21));

			ImageView tag = new ImageView();
			Image image = new Image(urlToTag.get(tasks.get(i).getIdTag()), 50, 50, false, true);
			tag.setImage(image);
			tag.prefHeight(50);
			tag.prefWidth(50);

			task.getChildren().add(nameTask);
			task.getChildren().add(tag);

			// arent.getChildrenUnmodifiable().add(task);

			vbox.getChildren().add(vbox.getChildren().indexOf(vbox.lookup("AddTaskButton")) + 1,
					task);
			
			vbox.setOnMouseClicked( ( e ) ->
		      {
		        System.out.println("Tâche cliquée");
		      } );
		}
		
		
		// BUILD TASK ELEMENT

		/*HBox task = new HBox();
		task.prefWidth(200);
		task.prefHeight(50);
		// HBox.setMargin(task, new Insets(550, 0, 0, 50));

		Label nameTask = new Label("Lorem Ipsum");
		nameTask.setFont(new Font("System Bold", 21));

		ImageView tag = new ImageView();
		Image image = new Image(urlToTag.get(1), 50, 50, false, true);
		tag.setImage(image);
		tag.prefHeight(50);
		tag.prefWidth(50);

		task.getChildren().add(nameTask);
		task.getChildren().add(tag);

		// arent.getChildrenUnmodifiable().add(task);

		vbox.getChildren().add(vbox.getChildren().indexOf(vbox.lookup("AddTaskButton")) + 1,
				task);*/
	}

	private void createTask(Parent parent) {

		// BUILD TASK ELEMENT

		HBox task = new HBox();
		task.prefWidth(200);
		task.prefHeight(50);
		// HBox.setMargin(task, new Insets(550, 0, 0, 50));

		Label nameTask = new Label("Lorem Ipsum");
		nameTask.setFont(new Font("System Bold", 21));

		ImageView tag = new ImageView();
		Image image = new Image("file:///images\\java_tag.png", 50, 50, false, true);
		tag.setImage(image);
		tag.prefHeight(50);
		tag.prefWidth(50);

		task.getChildren().add(nameTask);
		task.getChildren().add(tag);

		// arent.getChildrenUnmodifiable().add(task);

		((VBox) parent).getChildren().add(((VBox) parent).getChildren().indexOf(parent.lookup("AddTaskButton")) + 1,
				task);
	}

	public void ExitApp(ActionEvent event) throws IOException {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Quitter");
		alert.setHeaderText("Vous êtes sur le point de quitter l'appli !");
		alert.setContentText("Voulez-vous vraiment fermer cette application ? Tout progrès non sauvegardé sera perdu");

		if (alert.showAndWait().get() == ButtonType.OK) {
			System.out.println("User exited the app");
			stage.close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// SET TAGS VALUES
		urlToTag = new HashMap<Integer, String>();
		urlToTag.put(1, "file:///images\\java_tag.png");
		urlToTag.put(2, "file:///images\\android_tag.png");
		urlToTag.put(3, "file:///images\\flutter_tag.png");
		urlToTag.put(4, "file:///images\\ios_tag.png");
		urlToTag.put(5, "file:///images\\api_tag.png");

		// CHANGING USER NAME
		UsernameText.setText(user.getFirstname() + " " + user.getLastname());

		// RETREIVING ALL THE LIST OWNED BY THE USER
		ArrayList<ListTask> listTask = Modele.getUsersTaskList(user.getIdUser());
		lists = listTask;

		// CREATING INTERFACE AND FILLING ALL LISTS WITH ITS TASK
		for (int i=0 ; i<listTask.size() ; i++) {
			// System.out.println(listTask.get(i).getTitle());
			ArrayList<Task> tasksForList = Modele.getTasksFromList(listTask.get(i).getIdList());
			createListWithTask(listTask.get(i).getTitle(), tasksForList);
			listTask.get(i).setListTask(tasksForList);
		}
	}

	public void addTaskToList(Node host, ArrayList<ListTask> lists) {

		System.out.println("Will build tasks in lists with action button");
		
	}
}
