package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modele.Modele;
import objects.Comment;
import objects.Task;
import objects.User;

public class DetailController implements Initializable {

	private int idTask;
	private Task task;
	private ArrayList<Comment> comments;
	private User user;
	private Stage stage;
	private Node taskGUI;

	@FXML
	private Label Title;

	@FXML
	private Text Description;

	@FXML
	private Button addComment;

	@FXML
	private ImageView editTask;

	@FXML
	private ImageView deleteTask;

	@FXML
	private TextField newComment;

	@FXML
	private VBox CommentList;

	@FXML
	private ScrollPane CommentList2;

	public DetailController(String id, User user, Stage stage, Node taskGUI) {
		this.idTask = Integer.parseInt(id);
		this.user = user;
		this.stage = stage;
		this.taskGUI = taskGUI;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// addComment.setOnAction(new);

		task = Modele.getTask(idTask);

		Title.setText(task.getTitle());
		Description.setText(task.getDescription());

		comments = Modele.getCommentFromTask(idTask);

		loadComments();

	}

	private void loadComments() {
		System.out.println("Loading...");

		for (int i = 0; i < comments.size(); i++) {
			VBox root = new VBox();
			VBox.setMargin(root, new Insets(8, 0, 8, 0));
			root.setPadding(new Insets(5, 16, 0, 16));
			HBox nameDate = new HBox();
			Text name = new Text();
			Text date = new Text();
			Text body = new Text();

			nameDate.getChildren().add(name);
			nameDate.getChildren().add(date);
			nameDate.setSpacing(18);

			root.getChildren().add(nameDate);

			root.getChildren().add(body);

			// GETTING DATA FROM TEXTFIELD
			// String bodyTXT = newComment.getText();

			name.setText(user.getFirstname() + " " + user.getLastname()); // TAKEN FROM BD
			name.setFont(new Font("System", 18));

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now();
			date.setText(comments.get(i).getDate());
			date.setFont(new Font("System", 12));
			date.setFill(Color.GREY);
			date.setStyle("-fx-margin: 8px;");
			HBox.setMargin(date, new Insets(5, 8, 0, 0));

			body.setText(comments.get(i).getBody());
			body.setFont(new Font("System", 12));
			body.setFill(Color.BLACK);

			CommentList.getChildren().add(root);
		}
	}

	// GET USER FROM ID ?

	@FXML
	private void createComment(ActionEvent event) {
		// CREATE ELEMENT
		VBox root = new VBox();
		VBox.setMargin(root, new Insets(8, 0, 8, 0));
		root.setPadding(new Insets(5, 16, 0, 16));
		HBox nameDate = new HBox();
		Text name = new Text();
		Text date = new Text();
		Text body = new Text();

		nameDate.getChildren().add(name);
		nameDate.getChildren().add(date);
		nameDate.setSpacing(18);

		root.getChildren().add(nameDate);

		root.getChildren().add(body);

		// GETTING DATA FROM TEXTFIELD
		String bodyTXT = newComment.getText();

		name.setText(user.getFirstname() + " " + user.getLastname());
		name.setFont(new Font("System", 18));

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		date.setText(dtf.format(now));
		date.setFont(new Font("System", 12));
		date.setFill(Color.GREY);
		date.setStyle("-fx-margin: 8px;");
		HBox.setMargin(date, new Insets(5, 8, 0, 0));

		body.setText(bodyTXT);
		body.setFont(new Font("System", 12));
		body.setFill(Color.BLACK);

		CommentList.getChildren().add(root);

		Comment commentToInsert = new Comment(0, bodyTXT, dtf.format(now), idTask, user.getIdUser());
		Modele.insertComment(commentToInsert);

		// ERASE TEXTFIELD
		newComment.setText("");

	}

	@FXML
	public void deleteTask() {
		
		Label label = new Label("Voulez-vous vraiment supprimer cette tâche ?\nCette action est IRRÉVERSIBLE.");
		label.setWrapText(true);

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Quitter");
		alert.setHeaderText("Suppression d'une tâche !");
		alert.getDialogPane().setContent(label);

		if (alert.showAndWait().get() == ButtonType.OK) {
			Modele.deleteTask(idTask);
			System.out.println("Task #" + idTask + " deleted.");
			((VBox)taskGUI.getParent()).getChildren().remove(taskGUI);
			stage.close();
		}
	}

	@FXML
	public void editTask(MouseEvent event) throws IOException {
		// Modele.editTask(updatedTask);
		
		Stage modalDialog = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/EditTaskPage.fxml"));
		EditTaskPageController controller = new EditTaskPageController(user, task, modalDialog, (HBox)taskGUI);
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
			stage.close();
		});
		
		modalDialog.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Closing edit modal");
				//HomeController.refreshHost();
			}
			
		});
		System.out.println("Task #" + idTask + " edited.");
	}
}
