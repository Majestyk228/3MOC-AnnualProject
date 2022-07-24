package application;

import java.io.IOException;
import java.util.Objects;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
	
	private Stage stage;
    private Scene scene;
    @FXML
    private TextField EmailTextfield;
    @FXML
    private TextField PasswordTextfield;
    
    
    public void loogin (ActionEvent event) throws IOException {
    	
    	String email = EmailTextfield.getText();
    	String password = PasswordTextfield.getText();
    	
    	// API CALL FOR LOGIN
    	
    	/*HttpResponse <String> httpResponse = Unirest.get("<some_url>/<endpoint>?param1=value1&param2=value2")
    		       .header("header1", header1)
    		       .header("header2", header2);
    		       .asString();
    		System.out.println( httpResponse.getHeaders().get("Content-Type"));*/
    	
    	
    	try {
			HttpResponse<String> response = Unirest.get("https://www.titan-photography.com/post/all")
				      .header("accept", "application/json").asString();
			
			System.out.println(response.getBody());
		} catch (UnirestException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	// API CALL FOR LOGIN
    	
        if (Objects.equals(email, "Sarah") && Objects.equals(password, "test1234")){
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/resources/HomePage.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
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
            //ErrorField.setText("Identifiants incorrects!");
        	System.out.println("Mauvais credentials");
        }
    }
    
    public void ExitApp(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quitter");
        alert.setHeaderText("Vous êtes sur le point de quitter l'appli !");
        alert.setContentText("Voulez-vous vraiment fermer cette application ? Tout progrès non sauvegardé sera perdu");

        if (alert.showAndWait().get() == ButtonType.OK){
            System.out.println("User exited the app");
            stage.close();
        }
    }
}
