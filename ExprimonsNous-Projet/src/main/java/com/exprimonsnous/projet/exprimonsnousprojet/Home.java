package com.exprimonsnous.projet.exprimonsnousprojet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Home extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Connexion");
        stage.setScene(scene);
        stage.show();

        /*Button ConnexionButton = (Button) scene.lookup("#ConnexionButton");
        TextField firstname = (TextField) scene.lookup("#FirstnameTextField");
        TextField lastname = (TextField) scene.lookup("#LastnameTextField");
        int destroyEvent = 0;

        //Creating the mouse event handler
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("clicked");
                if (ConnexionButton.getText().compareTo("Connected!") == 0){
                    ConnexionButton.setVisible(false);
                    ConnexionButton.setManaged(false);
                } else if (firstname.getText().compareTo("Kilian") == 0 && lastname.getText().compareTo("Cassaigne") == 0){
                    ConnexionButton.setText("Connected!");
                } else {
                    ConnexionButton.setText("Identifiants incorrects");
                    FXMLLoader loader = new FXMLLoader(GestionProjetApplication.class.getResource("com.exprimonsnous.projet.exprimonsnousprojet.AppView.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(loader.load(), 600, 500);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    stage.setScene(scene);
                }
            }
        };
        //Registering the event filter
        ConnexionButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);*/



    }

    public static void main(String[] args) {
        launch();
    }
}