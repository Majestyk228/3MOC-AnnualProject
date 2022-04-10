package com.exprimonsnousprojet.gestiondeprojetfx;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class TestFX extends Application {
    private int nbClick = 0;
    private ArrayList<Category> categories = new ArrayList<Category>();
    private User currentUser;
    private Scanner cli;
    private String firstname;
    private String lastname;
    private int inputEvents = 0;
    private int sceneNumber = 0;

    public static void main(String[] args) {
        launch(); //démarrage de l'interface graphique
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//modification de l'eshétique basique de la fenêtre principale
        primaryStage.setTitle("Gestion de projet");
        primaryStage.setWidth(500);
        primaryStage.setHeight(400);
//instanciation d'un label
        Label unLabel = new Label("Welcome, please enter your firstname : ");

//instanciation d'un bouton
        Button unBouton = new Button("Next");
//écoute du bouton
        unBouton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
        //action à effectuer au clique
                if(event.getSource()==unBouton)
                {
                    sceneNumber++;
                    switchScene(sceneNumber,(VBox) unBouton.getParent());
                    unBouton.setVisible(false);
                    unBouton.setManaged(false);
                }
            }
        });
        unBouton.setVisible(false);

//instanciation d'un textField (text input)
        TextField inputField = new TextField("Your firstname...");
//écoute du textField
        inputField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
//action à effectuer au clique
                if(event.getSource()==inputField)
                {
                    if (inputEvents == 0){
                        firstname = inputField.getText().trim();
                        unLabel.setText("Please enter your lastname : ");
                        inputField.setText("Your lastname...");
                        inputEvents++;
                    }else {
                        lastname = inputField.getText().trim();
                        inputEvents++;
                        inputField.setVisible(false);
                        inputField.setManaged(false);
                        unLabel.setText("Welcome " + firstname + " " + lastname + " !");
                        unBouton.setVisible(true);
                    }
                }
            }
        });
        //layout vertical
        VBox root = new VBox(25);
//centrage des éléments de la VBox
        root.setAlignment(Pos.CENTER);
//instanciation de la scene avec la VBox root comme parent
        Scene uneScene = new Scene(root, 400,300);
//ajout des éléments à la vbox : le label puis le bouton en dessous
        root.getChildren().addAll(unLabel, inputField, unBouton);
//définition de la scène attribuée à la Stage
        primaryStage.setScene(uneScene);
//interdiction du redimentionnement
        primaryStage.setResizable(false);
//affichage à l'écran
        primaryStage.show();
//centrage de la fenêtre au centre de l'écran
        primaryStage.centerOnScreen();

    }
    private void switchScene(int sceneNumber, VBox root){
        if (sceneNumber == 1){
            System.out.println(root.getChildren());
            System.out.println(root);
        }
    }
}


