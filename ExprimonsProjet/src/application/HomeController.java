package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Pair;
import modele.Modele;
import objects.ListTask;
import objects.Tag;
import objects.Task;
import objects.User;

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

	private ArrayList<Tag> tags;

	private ArrayList<ListTask> lists;

	private HashMap<Integer, String> urlToTag;

	public HomeController(User user) {
		this.user = user;
	}

	public void createDialogue(ActionEvent event) {
		// create a text input dialog
		TextInputDialog td = new TextInputDialog("Nom de la liste...");

		td.setHeaderText("Nommez la nouvelle liste");

		Optional<String> result = td.showAndWait();

		result.ifPresent(listName -> {
			createList(listName);
		});
	}

	public void createList(String listName) {

		Button button = new Button();
		button.setId("AddTaskButton");
		button.setText("Nouvelle tâche");
		button.setFont(new Font("System Bold", 17));

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

		ListTask listTask = new ListTask(0, listName, user.getIdUser());
		int idList = Modele.insertList(listTask);
		tpane.setId("idList = " + idList);

		button.setOnMouseClicked((event) -> {

			System.out.println("Tache à ouvrir...");
			// Window window = ((Node) event.getTarget()).getScene().getWindow();
			try {
				showAddTask2(event, idList); // TODO to correct
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	public void createListWithTask(String listName, ArrayList<Task> tasks, int idList) {

		Button button = new Button();
		button.setId("AddTaskButton");
		button.setText("Nouvelle tâche");
		button.setFont(new Font("System Bold", 17));

		button.setOnMouseClicked((event) -> {

			System.out.println("Tache à ouvrir...");
			// Window window = ((Node) event.getTarget()).getScene().getWindow();
			try {
				showAddTask2(event, idList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		tpane.setId("idList = " + idList);
		tpane.prefWidth(202);
		tpane.prefHeight(450);
		tpane.setFont(new Font("System Bold", 22));

		Accordion accord = new Accordion();
		accord.getPanes().add(tpane);
		accord.setId(idList + "");

		// CONTEXT MENU
		ContextMenu contextMenu = new ContextMenu();

		MenuItem item1 = new MenuItem("Modifier cette liste");
		item1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Modification de la liste id " + accord.getId());
			}
		});
		MenuItem item2 = new MenuItem("Supprimer cette liste");
		item2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Suppression de la liste id " + accord.getId());
			}
		});

		// Add MenuItem to ContextMenu
		contextMenu.getItems().addAll(item1, item2);

		// When user right-click on Circle
		accord.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

			@Override
			public void handle(ContextMenuEvent event) {

				contextMenu.show(accord, event.getScreenX(), event.getScreenY());
			}
		});

		// ADD accor in HostList
		HostList.getChildren().add(HostList.getChildren().indexOf(NewListButton), accord);

		for (int i = 0; i < tasks.size(); i++) {
			// TODO : code

			HBox task = new HBox();
			task.prefWidth(200);
			task.prefHeight(50);
			task.setId(tasks.get(i).getIdTask() + "");

			task.setOnMouseClicked((event) -> {
				// Window window = ((Node) event.getTarget()).getScene().getWindow();
				try {
					showDetailPage(event);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

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

			vbox.getChildren().add(vbox.getChildren().indexOf(vbox.lookup("AddTaskButton")) + 1, task);
		}
	}

	private void createTask(Parent parent, Task taskPM) {

		// BUILD TASK ELEMENT

		HBox task = new HBox();
		task.prefWidth(200);
		task.prefHeight(50);
		task.setId(taskPM.getIdTask() + "");

		System.out.println("idTask in ID = " + taskPM.getIdTask());

		Label nameTask = new Label(taskPM.getTitle());
		nameTask.setFont(new Font("System Bold", 21));

		ImageView tag = new ImageView();
		Image image = new Image(urlToTag.get(taskPM.getIdTag()), 50, 50, false, true);
		tag.setImage(image);
		tag.prefHeight(50);
		tag.prefWidth(50);

		task.getChildren().add(nameTask);
		task.getChildren().add(tag);

		((VBox) parent).getChildren().add(((VBox) parent).getChildren().indexOf(parent.lookup("AddTaskButton")) + 1,
				task);

		for (int i = 0; i < ((VBox) parent).getChildren().size(); i++) {
			((VBox) parent).getChildren().get(i).setOnMouseClicked((event) -> {

				try {
					showDetailPage(event);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}

		/*
		 * ((VBox) parent).setOnMouseClicked((event) -> {
		 * 
		 * try { showDetailPage(event); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } });
		 */
	}

	public void ExitApp(ActionEvent event) throws IOException {

		Label label = new Label(
				"Voulez-vous vraiment fermer cette application ?\nTout progrès non sauvegardé sera perdu.");
		label.setWrapText(true);

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Quitter");
		alert.setHeaderText("Vous êtes sur le point de quitter l'application !");
		alert.getDialogPane().setContent(label);

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
		for (int i = 0; i < listTask.size(); i++) {
			// System.out.println(listTask.get(i).getTitle());
			ArrayList<Task> tasksForList = Modele.getTasksFromList(listTask.get(i).getIdList());
			createListWithTask(listTask.get(i).getTitle(), tasksForList, listTask.get(i).getIdList());
			listTask.get(i).setListTask(tasksForList);
		}

		// RETREIVING ALL THE TAGS FROM DATABASE
		tags = Modele.getAllTags();
	}

	private void showDetailPage(MouseEvent event) throws IOException {

		Stage modalDialog = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/DetailPage.fxml"));

		Node source = (Node) event.getSource();
		String id = source.getId();

		System.out.println("Task id = " + source);

		DetailController controller = new DetailController(id, user, modalDialog, source);
		loader.setController(controller);
		Parent modalDialogRoot = loader.load();
		// CreateTaskModalController controller = loader.getController(); // Retrieve
		// the controller
		Scene modalScene = new Scene(modalDialogRoot);
		modalDialog.initOwner(((Node) event.getSource()).getScene().getWindow());
		modalDialog.setTitle("Détail de tâche");
		modalDialog.setScene(modalScene);
		modalDialog.setResizable(false);

		Platform.runLater(() -> modalDialog.showAndWait());
	}

	private void showAddTask2(MouseEvent event, int idList) throws IOException {
		Stage modalDialog = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/CreateTaskModal.fxml"));
		CreateTaskModalController controller = new CreateTaskModalController(user, idList, tags, modalDialog);
		loader.setController(controller);
		Parent modalDialogRoot = loader.load();
		// CreateTaskModalController controller = loader.getController(); // Retrieve
		// the controller
		Scene modalScene = new Scene(modalDialogRoot);
		modalDialog.initOwner(((Node) event.getSource()).getScene().getWindow());
		modalDialog.setScene(modalScene);
		modalDialog.setResizable(false);

		Platform.runLater(() -> {
			modalDialog.showAndWait();
			createTask(((Parent) event.getSource()).getParent(), controller.getLastCreatedTask());
		});
	}
}
