package com.exprimonsnous.projet.exprimonsnousprojet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AppView {

    private static Stage stage;
    private Scene scene;

    @FXML
    static Rectangle ReferenceRectangle;
    @FXML
    private GridPane GridPane;
    @FXML
    private Button AddListButton;
    @FXML
    private VBox VBOX;

    @FXML
    private static MenuButton OptionsButton;



    public void CreateDialogue(ActionEvent event) {
        // create a text input dialog
        TextInputDialog td = new TextInputDialog("Nom de la liste...");

        // setHeaderText
        td.setHeaderText("Nommez la nouvelle liste");

        Optional<String> result = td.showAndWait();

        result.ifPresent(listName -> {
            AddList(listName);
        });
    }

    public void AddList(String listName){
        ListPane TitledListPane = new ListPane(listName);
        GridPane.add(TitledListPane, GridPane.getColumnIndex(AddListButton), 0);
        GridPane.setValignment(TitledListPane, VPos.TOP);
        GridPane.setHgrow(TitledListPane, Priority.NEVER);
        GridPane.setVgrow(TitledListPane, Priority.ALWAYS);
        VBOX.setVgrow(TitledListPane, Priority.ALWAYS);
        ColumnConstraints column = new ColumnConstraints();
        column.setMaxWidth(150);
        GridPane.getColumnConstraints().add(column);
        GridPane.setColumnIndex(AddListButton, GridPane.getColumnIndex(AddListButton) + 1);
        System.out.println(GridPane.getHeight());
    }

    public static void AddTask(String taskname, ListView parent, Button button){
        TitledPane TitledTaskPane = new TitledPane();
        TitledTaskPane.setText(taskname);
        Button ManageTaskButton = new Button("Gérer cette tâche...");

        ManageTaskButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ManageTask(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        TitledTaskPane.setContent(ManageTaskButton);
        TitledTaskPane.setWrapText(true);
        TitledTaskPane.setTextOverrun(OverrunStyle.CLIP);
        parent.getItems().remove(parent.getItems().toArray().length - 1);
        parent.getItems().add(TitledTaskPane);
        button.setAlignment(Pos.CENTER);
        parent.getItems().add(button);
    }

    public static void ManageTask(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Home.class.getResource("TaskViewController.fxml"));
        stage = (Stage) (OptionsButton.getParent().getScene().getWindow());
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void CloseListMenu(ActionEvent event) {
    }

    public void SwitchToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Home.class.getResource("MainView.fxml"));
        stage = (Stage) (AddListButton.getParent().getScene().getWindow());
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void ExitApp(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quitter");
        alert.setHeaderText("Vous êtes sur le point de quitter l'appli !");
        alert.setContentText("Voulez-vous vraiment fermer cette application ? Tout progrès non sauvegardé sera perdu");

        if (alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) GridPane.getScene().getWindow();
            System.out.println("User exited the app");
            stage.close();
        }
    }
}
