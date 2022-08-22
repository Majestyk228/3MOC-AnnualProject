package application;

import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modele.Modele;
import objects.User;

public class MainController {

	private Stage stage;
	private Scene scene;
	@FXML
	private TextField EmailTextfield;
	@FXML
	private TextField PasswordTextfield;
	@FXML
	private Text WrongCredentialWarning;
	@FXML
	private Text WrongCredentialWarning2;

	public void loogin(ActionEvent event) throws IOException {

		String email = EmailTextfield.getText();
		String password = PasswordTextfield.getText();

		User user = Modele.verifConnexion(email, password);
		
		if (user == null) {
			System.out.println("Erreur de connexion, vérifiez vos identifiants.");
			WrongCredentialWarning.setOpacity(100);
			WrongCredentialWarning2.setOpacity(100);
		}else {
			WrongCredentialWarning.setOpacity(0);
			WrongCredentialWarning2.setOpacity(0);
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/resources/HomePage.fxml"));
			HomeController controller = new HomeController(user);
			
			loader.setController(controller);
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setUserData(user);
			scene = new Scene(loader.load());
			stage.setScene(scene);
			stage.show();
			controller.setStage(stage);
			controller.setScene(scene);
			stage.setOnCloseRequest(closeEvent -> {
				closeEvent.consume();
				try {
					ExitApp(event);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
		}
	}

	public void ExitApp(ActionEvent event) throws IOException {
		Label label = new Label("Voulez-vous vraiment fermer cette application ?\nTout progrès non sauvegardé sera perdu.");
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
