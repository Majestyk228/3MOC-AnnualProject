package com.exprimonsnous.projet.exprimonsnousprojet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

import java.util.Optional;

import static java.lang.Double.MAX_VALUE;

public class ListPane extends TitledPane {
    public ListPane(String listName) {
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
                    AppView.AddTask(taskName, content, AddTaskButton);
                });
            }
        });
        content.getItems().add(AddTaskButton);
        TitledListPane.setAnimated(false);
        TitledListPane.setContent(content);
        TitledListPane.setWrapText(true);
        TitledListPane.setTextOverrun(OverrunStyle.CLIP);
    }
}
