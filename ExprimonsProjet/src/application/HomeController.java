package application;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeController {
	
	private Stage stage;
    private Scene scene;
    @FXML
    private Text Username;
    @FXML
    private HBox ListButton;
    @FXML
    private HBox AccountButton;
    @FXML
    private HBox SettingsButton;
    @FXML
    private Button NewListButton;
    @FXML
    private HBox HostList; 		// THIS IS THE LAYOUT THAT HOSTS ALL THE LISTS
    

    public void createDialogue(ActionEvent event) {
        // create a text input dialog
        TextInputDialog td = new TextInputDialog("Nom de la liste...");

        // setHeaderText
        td.setHeaderText("Nommez la nouvelle liste");

        Optional<String> result = td.showAndWait();

        result.ifPresent(listName -> {
        	createList(listName);
        });
    }
    
    
    public void createList(String listName) {
    	
    	/* TODO : ACTION TO MAKE IN THIS CREATE LIST FUNCTION
    	 * 
    	 * 1) create the Accordion with the TitledPane
    	 *
    	 *	Button new Task
    	 *	VBox a
    	 *	TitledPane t
    	 *	Accordion acc
    	 *
    	 *	Put a in t ; put t in acc and put acc in HostList
    	 * */
    	
    	Button button = new Button();
    	button.setId("AddTaskButton");
    	button.setText("Nouvelle tâche");
    	button.setFont(new Font("System Bold", 17));
    	button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                shittyFunction(button.getParent());
            	//System.out.println("Prout");
            }
        });
    	
    	VBox vbox = new VBox();
    	vbox.prefWidth(182);
    	vbox.prefHeight(450);
    	vbox.getChildren().add(button);
    	vbox.setAlignment(Pos.TOP_CENTER);
    	
    	AnchorPane anchor = new AnchorPane();
    	anchor.prefWidth(182);
    	anchor.prefHeight(450);
    	
    	TitledPane tpane = new TitledPane(listName, vbox);
    	tpane.prefWidth(202);
    	tpane.prefHeight(450);
    	tpane.setFont(new Font("System Bold", 22));
    	
    	Accordion accord =  new Accordion();
    	accord.getPanes().add(tpane);
        
        // ADD accor in HostList
        HostList.getChildren().add(HostList.getChildren().indexOf(NewListButton),accord);
    }
    
    private void shittyFunction(Parent parent) {
    	System.out.println("Prout");
    	
    	//parent.getChildren().add(HostList.getChildren().indexOf(NewListButton),accord);
    	
    	// BUILD TASK ELEMENT
    	
    	HBox task = new HBox();
    	task.prefWidth(200);
    	task.prefHeight(50);
    	//HBox.setMargin(task, new Insets(550, 0, 0, 50));
    	
    	Label nameTask = new Label("Lorem Ipsum");
    	nameTask.setFont(new Font("System Bold", 22));
    	
    	ImageView tag = new ImageView();
    	Image image = new Image(new File("ios_tag.png").toURI().toString());
    	tag.setImage(image);
    	tag.prefHeight(50);
    	tag.prefWidth(50);
    	
    	task.getChildren().add(nameTask);
    	task.getChildren().add(tag);
    	
    	//arent.getChildrenUnmodifiable().add(task);
    	
    	((VBox)parent).getChildren().add(((VBox)parent).getChildren().indexOf(parent.lookup("AddTaskButton"))+1,task);
    }
}
