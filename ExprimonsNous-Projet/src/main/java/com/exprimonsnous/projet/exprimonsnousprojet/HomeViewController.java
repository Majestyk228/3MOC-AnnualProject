package com.exprimonsnous.projet.exprimonsnousprojet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.util.Objects;

public class HomeViewController {
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField UsernameField;
    @FXML
    private TextField PasswordField;
    @FXML
    private Text ErrorField;

    public void login (ActionEvent event) throws IOException {
        if (Objects.equals(UsernameField.getText(), "Kilian") && Objects.equals(PasswordField.getText(), "1234")){
            FXMLLoader loader = new FXMLLoader(Home.class.getResource("AppView.fxml"));
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
        } else {
            ErrorField.setText("Identifiants incorrects!");
        }
    }

    public void ExitApp(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quitter");
        alert.setHeaderText("Vous êtes sur le point de quitter l'appli !");
        alert.setContentText("Voulez-vous vraiment fermer cette application ? Tout progrès non sauvegardé sera perdu");

        if (alert.showAndWait().get() == ButtonType.OK){
            FXMLLoader loader = new FXMLLoader(Home.class.getResource("AppView.fxml"));
            scene = new Scene(loader.load());
            stage.setScene(scene);
            stage = (Stage) scene.getWindow();
            System.out.println("User exited the app");
            stage.close();
        }
    }
}
