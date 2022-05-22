package com.exprimonsnous.projet.exprimonsnousprojet;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AppView {

    @FXML
    private GridPane ListPane;
    @FXML
    private Button AddListButton;



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
        ListView content = new ListView<>();
        content.setMaxWidth(150);
        content.setPrefWidth(150);
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
        TitledListPane.setContent(content);
        TitledListPane.setWrapText(true);
        ListPane.add(TitledListPane, GridPane.getColumnIndex(AddListButton), 0);
        ListPane.setValignment(TitledListPane, VPos.TOP);
        ColumnConstraints column = new ColumnConstraints();
        column.setMaxWidth(150);
        ListPane.getColumnConstraints().add(column);
        ListPane.setColumnIndex(AddListButton, GridPane.getColumnIndex(AddListButton) + 1);

    }

    public void AddTask(String taskname, ListView parent, Button button){
        Label test = new Label("Test");
        TitledPane TitledTaskPane = new TitledPane();
        TitledTaskPane.setText(taskname);
        TitledTaskPane.setContent(test);
        TitledTaskPane.setWrapText(true);
        TitledTaskPane.setMaxWidth(140);
        parent.getItems().remove(parent.getItems().toArray().length - 1);
        parent.getItems().add(TitledTaskPane);
        button.setAlignment(Pos.CENTER);
        parent.getItems().add(button);
    }

    public void CloseListMenu(ActionEvent event) {
    }

    public void SwitchToHome(ActionEvent event) {
    }

    public void ExitApp(ActionEvent event) {
    }
}
