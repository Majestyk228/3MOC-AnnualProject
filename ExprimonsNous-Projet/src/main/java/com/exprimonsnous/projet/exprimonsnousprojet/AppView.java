package com.exprimonsnous.projet.exprimonsnousprojet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class AppView {

    @FXML
    private TextField ListNameField;
    @FXML
    private AnchorPane ListPane;

    public void AddList(ActionEvent event) {
        if (!Objects.equals(ListNameField.getText(), "")){
            TextField TaskNameField = new TextField("Saisissez le titre...");
            TaskNameField.setId("TaskNameField");
            MenuItem AddTaskMenuItem = new MenuItem("Ajouter une tâche");

            EventHandler AddTask = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    System.out.println("Ajout d'une tâche");
                }
            };
            AddTaskMenuItem.setOnAction(AddTask);
            SplitMenuButton TaskSplitMenuButton = new SplitMenuButton(AddTaskMenuItem);
            TaskSplitMenuButton.getChildrenUnmodifiable().add(TaskNameField);
            TitledPane titledPane = new TitledPane(ListNameField.getText(), TaskSplitMenuButton);
            Accordion accordion = new Accordion(titledPane);

            ListPane.getChildren().addAll(accordion);
            ListPane.setLayoutX(ListPane.getWidth() + ListPane.getLayoutX());
        }
    }

    public void CloseListMenu(ActionEvent event) {
    }

    public void SwitchToHome(ActionEvent event) {
    }

    public void ExitApp(ActionEvent event) {
    }
}
