package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import controllers.ListTask;
import controllers.Tag;
import controllers.Task;
import controllers.User;
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
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

		// setHeaderText
		td.setHeaderText("Nommez la nouvelle liste");

		Optional<String> result = td.showAndWait();

		result.ifPresent(listName -> {
			createList(listName);
		});
	}

	public void createDialogueTask2(ActionEvent event) {
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("TestName");

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		VBox dialogVbox = new VBox();
		dialogVbox.setPadding(new Insets(20, 150, 10, 10));

		TextField from = new TextField();
		from.setPromptText("From");
		TextField to = new TextField();
		to.setPromptText("To");

		dialogVbox.getChildren().add(from);
		dialogVbox.getChildren().add(to);

		dialog.getDialogPane().setContent(dialogVbox);

		// Request focus on the username field by default.
		Platform.runLater(() -> from.requestFocus());

		// Convert the result to a username-password-pair when the login button is
		// clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return new Pair<>(from.getText(), to.getText());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(pair -> {
			System.out.println("From=" + pair.getKey() + ", To=" + pair.getValue());
		});
	}

	public void createDialogueTask3(ActionEvent event) {
		Dialog<ButtonType> alert = new Dialog();
		alert.setTitle("Ajout d'une tâche");
		alert.setHeaderText("Entrez les informations de votre nouvelle tâche");
		
		DialogPane dialogPane = new DialogPane();
		dialogPane.getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
		
		TextField titleTXT = new TextField();
		TextField descriptionTXT = new TextField();
		ComboBox<Tag> tag = new ComboBox<>();
		tag.getItems().addAll(tags);
		
		VBox layout = new VBox();
		layout.getChildren().addAll(titleTXT, descriptionTXT, tag);
		
		dialogPane.setContent(layout);
		//alert.setContentText("Choose your option.");

		//ButtonType buttonTypeOne = new ButtonType("One");
		//ButtonType buttonTypeTwo = new ButtonType("Two");
		//ButtonType buttonTypeThree = new ButtonType("Three");
		//ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		
		

		//alert.setDialogPane(new DialogPane().);
		//alert.getButtonTypes().setAll(titleTXT, descriptionTXT, tag, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
	}

	public void createDialogueTask(ActionEvent event) {
		// create a text input dialog
		TextInputDialog td = new TextInputDialog("Nom de la tâche...");

		// setHeaderText
		td.setHeaderText("Nommez la nouvelle tâche");

		Optional<String> result = td.showAndWait();

		result.ifPresent(taskName -> {
			createTask(((Parent) event.getSource()).getParent(), taskName);
			System.out.println(((Node) ((Parent) event.getSource()).getParent()).getParent().getParent().getId());
			int idList = 0;

			/*
			 * for(int i=0 ; i<lists.size() ; i++) { if(lists.get(i).getTitle() == ) }
			 */

			// TODO : insert task in list
			// Task taskToInsert = new Task(0, taskName, "", user.getIdUser(), idList,
			// idTag);
		});
	}

	public void createList(String listName) {

		Button button = new Button();
		button.setId("AddTaskButton");
		button.setText("Nouvelle tâche");
		button.setFont(new Font("System Bold", 17));
		/*button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//createDialogueTask2(event);
				///createDialogueTask3(event);
				// System.out.println("Prout");
			}
		});*/
		
		/*button.setOnMouseClicked((event) -> {

			System.out.println("Tache à ouvrir...");
			// Window window = ((Node) event.getTarget()).getScene().getWindow();
			try {
				showAddTask(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});*/

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

	public void createListWithTask(String listName, ArrayList<Task> tasks, int idList) {

		Button button = new Button();
		button.setId("AddTaskButton");
		button.setText("Nouvelle tâche");
		button.setFont(new Font("System Bold", 17));
		/*button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//createDialogueTask2(event);
				
			}
		});*/
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

		// ADD accor in HostList
		HostList.getChildren().add(HostList.getChildren().indexOf(NewListButton), accord);
		// addTaskToList(vbox, lists);

		for (int i = 0; i < tasks.size(); i++) {
			// TODO : code

			HBox task = new HBox();
			task.prefWidth(200);
			task.prefHeight(50);
			task.setId(tasks.get(i).getIdTask()+"");			
			
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

	private void createTask(Parent parent, String taskName) {

		// BUILD TASK ELEMENT

		HBox task = new HBox();
		task.prefWidth(200);
		task.prefHeight(50);
		// HBox.setMargin(task, new Insets(550, 0, 0, 50));

		Label nameTask = new Label(taskName);
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
		
		for(int i=0 ; i<((VBox) parent).getChildren().size(); i++) {
			((VBox) parent).getChildren().get(i).setOnMouseClicked((event) -> {

				System.out.println("Tache à ouvrir...");
				// Window window = ((Node) event.getTarget()).getScene().getWindow();
				try {
					showDetailPage(event);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}

		/*((VBox) parent).setOnMouseClicked((event) -> {

			System.out.println("Tache à ouvrir...");
			// Window window = ((Node) event.getTarget()).getScene().getWindow();
			try {
				showDetailPage(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});*/
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
		for (int i = 0; i < listTask.size(); i++) {
			// System.out.println(listTask.get(i).getTitle());
			ArrayList<Task> tasksForList = Modele.getTasksFromList(listTask.get(i).getIdList());
			createListWithTask(listTask.get(i).getTitle(), tasksForList, listTask.get(i).getIdList());
			listTask.get(i).setListTask(tasksForList);
		}

		// NewListButton.setOnAction(null)
		// RETREIVING ALL THE TAGS FROM DATABASE
		tags = Modele.getAllTags();
	}

	public void addTaskToList(Node host, ArrayList<ListTask> lists) {

		System.out.println("Will build tasks in lists with action button");

	}

	private void showDetailPage(MouseEvent event) throws IOException {
		
		Stage modalDialog = new Stage();
	    FXMLLoader loader = new FXMLLoader(getClass().getResource( "/resources/DetailPage.fxml" ));
	    
	    Node source = (Node) event.getSource();
	    String id = source.getId();
	    
	    System.out.println("task id = " + id);
	    
	    DetailController controller = new DetailController(id, user);
	    loader.setController(controller);
	    Parent modalDialogRoot = loader.load();
	    //CreateTaskModalController controller = loader.getController(); // Retrieve the controller
	    Scene modalScene = new Scene(modalDialogRoot);
	    modalDialog.initOwner(((Node) event.getSource()).getScene().getWindow());
	    modalDialog.setTitle("Détail de tâche");
	    modalDialog.setScene(modalScene);
	    modalDialog.setResizable(false);

	    // You need Platform.runLater() so that this method doesn't get blocked
	    Platform.runLater(() -> modalDialog.showAndWait());
	}
	
	private void showAddTask2(MouseEvent event, int idList) throws IOException {
		Stage modalDialog = new Stage();
	    FXMLLoader loader = new FXMLLoader(getClass().getResource( "/resources/CreateTaskModal.fxml" ));
	    CreateTaskModalController controller = new CreateTaskModalController(user, idList, tags);
	    loader.setController(controller);
	    Parent modalDialogRoot = loader.load();
	    //CreateTaskModalController controller = loader.getController(); // Retrieve the controller
	    Scene modalScene = new Scene(modalDialogRoot);
	    modalDialog.initOwner(((Node) event.getSource()).getScene().getWindow());
	    modalDialog.setScene(modalScene);
	    modalDialog.setResizable(false);

	    // You need Platform.runLater() so that this method doesn't get blocked
	    Platform.runLater(() -> modalDialog.showAndWait());
	}
}
