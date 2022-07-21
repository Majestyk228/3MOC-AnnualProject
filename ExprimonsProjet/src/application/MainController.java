package application;

import java.io.IOException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
        if (Objects.equals(EmailTextfield.getText(), "Sarah") && Objects.equals(PasswordTextfield.getText(), "test1234")){
            /*FXMLLoader loader = new FXMLLoader(Home.class.getResource("AppView.fxml"));
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
            });*/
        	System.out.println("bon credential");
        } else {
            //ErrorField.setText("Identifiants incorrects!");
        	System.out.println("Mauvais credentials");
        }
    }
}
