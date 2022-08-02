package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
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
    
    
    public void createList(ActionEvent event) {
    	
    	/* TODO : ACTION TO MAKE IN THIS CREATE LIST FUNCTION
    	 * 
    	 * 1) create the Accordion with the TitledPane
    	 *
    	 *AnchorPane a
    	 *TitledPane t
    	 *Accordion acc
    	 *
    	 *Put a in t ; put t in acc and put acc in HostList
    	 * */
    	
    	AnchorPane anchor = new AnchorPane();
    	anchor.prefWidth(182);
    	anchor.prefHeight(450);
    	
    	TitledPane tpane = new TitledPane("Nouvelle liste...", anchor);
    	tpane.prefWidth(202);
    	tpane.prefHeight(450);
    	tpane.setFont(new Font("System Bold", 22));
    	
    	Accordion accord =  new Accordion();
    	accord.getPanes().add(tpane);
        
        // ADD accor in HostList
        HostList.getChildren().add(HostList.getChildren().indexOf(NewListButton),accord);
    	
    }

}
