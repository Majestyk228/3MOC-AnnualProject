package com.exprimonsnous.projet.exprimonsnousprojet;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.Double.MAX_VALUE;

public class AppView {

    private static Stage stage;
    private Scene scene;

    @FXML
    static Rectangle ReferenceRectangle;
    @FXML
    private GridPane ListPane;
    @FXML
    private Button AddListButton;
    @FXML
    private VBox VBOX;



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
        TitledPane TitledListPane = new TitledPane();
        TitledListPane.setText(listName);
        Button AddTaskButton = new Button("Ajouter une tâche");
        AddTaskButton.setWrapText(true);
        AddTaskButton.setTextOverrun(OverrunStyle.CLIP);
        ListView content = new ListView<>();
        content.setMaxWidth(150);
        content.setPrefWidth(150);
        content.setMaxHeight(MAX_VALUE);
        AddTaskButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog td = new TextInputDialog("Nom de la tâche...");

                // setHeaderText
                td.setHeaderText("Nommez la nouvelle tâche");

                Optional<String> result = td.showAndWait();

                result.ifPresent(taskName -> {
                    AddTask(taskName, content, AddTaskButton);
                });
            }
        });
        content.getItems().add(AddTaskButton);
        TitledListPane.setAnimated(false);
        TitledListPane.setContent(content);
        TitledListPane.setWrapText(true);
        TitledListPane.setTextOverrun(OverrunStyle.CLIP);
        ListPane.add(TitledListPane, GridPane.getColumnIndex(AddListButton), 0);
        ListPane.setValignment(TitledListPane, VPos.TOP);
        ListPane.setHgrow(TitledListPane, Priority.NEVER);
        ListPane.setVgrow(TitledListPane, Priority.ALWAYS);
        ListPane.setVgrow(content, Priority.ALWAYS);
        VBOX.setVgrow(TitledListPane, Priority.ALWAYS);
        VBOX.setVgrow(content, Priority.ALWAYS);
        ColumnConstraints column = new ColumnConstraints();
        column.setMaxWidth(150);
        ListPane.getColumnConstraints().add(column);
        ListPane.setColumnIndex(AddListButton, GridPane.getColumnIndex(AddListButton) + 1);
        System.out.println(ListPane.getHeight());
    }

    public void AddTask(String taskname, ListView parent, Button button){
        Label test = new Label("Test");
        TitledPane TitledTaskPane = new TitledPane();
        TitledTaskPane.setText(taskname);
        TitledTaskPane.setContent(test);
        TitledTaskPane.setWrapText(true);
        TitledTaskPane.setTextOverrun(OverrunStyle.CLIP);
        parent.getItems().remove(parent.getItems().toArray().length - 1);
        parent.getItems().add(TitledTaskPane);
        button.setAlignment(Pos.CENTER);
        parent.getItems().add(button);
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
            stage = (Stage) ListPane.getScene().getWindow();
            System.out.println("User exited the app");
            stage.close();
        }
    }
}
