package application;

import java.io.IOException;
import controllers.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Modele;

public class MainController {

	private Stage stage;
	private Scene scene;
	@FXML
	private TextField EmailTextfield;
	@FXML
	private TextField PasswordTextfield;

	public void loogin(ActionEvent event) throws IOException {

		String email = EmailTextfield.getText();
		String password = PasswordTextfield.getText();

		User user = Modele.verifConnexion(email, password);
		
		if (user == null) {
			System.out.println("Erreur de connexion, vérifiez vos identifiants.");
		}else {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/resources/HomePage.fxml"));
			HomeController controller = new HomeController(user);
			
			loader.setController(controller);
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setUserData(user);
			scene = new Scene(loader.load());
			stage.setScene(scene);
			stage.show();
			stage.setOnCloseRequest(closeEvent -> {
				closeEvent.consume();
				try {
					ExitApp(event);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
			System.out.println("bon credential");
		}

		
		/*if (sendLoginCredentials(lc) != null) {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/resources/HomePage.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(loader.load());
			stage.setScene(scene);
			stage.show();
			stage.setOnCloseRequest(closeEvent -> {
				closeEvent.consume();
				try {
					ExitApp(event);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
			System.out.println("bon credential");
		} else {
			// ErrorField.setText("Identifiants incorrects!");
			System.out.println("Mauvais credentials");
		}*/
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// API CALL FUNCTIONS KEPT IN COMMENT BLOCK

	/*public static LoginCredentialsResponse sendLoginCredentials(LoginCredentials lc) {
		HttpResponse<JsonNode> response;
		try {
			response = makeRequestToAPI(lc);
			System.out.println(response + "hehe");
			Gson gson = new Gson();
			return gson.fromJson(response.getBody().toString(), LoginCredentialsResponse.class);
		} catch (UnirestException e) {
			System.out.println(e);
			return null;
		}
	}

	private static HttpResponse<JsonNode> makeRequestToAPI(LoginCredentials lc) throws UnirestException {
		Unirest.setTimeouts(0, 0);
		return Unirest.post("https://esgi-ep-server-bis.herokuapp.com//user/login")
				.header("Content-Type", "application/json")
	            .body(lc.toString())
	            .asJson();
	}*/
}
