package application;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController<APOD> {
	
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
    	
    	URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
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
