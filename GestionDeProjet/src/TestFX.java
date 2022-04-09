import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestFX extends Application {
    private int nbClick = 0;

    public static void main(String[] args) {
        launch(); //démarrage de l'interface graphique
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//modification de l'eshétique basique de la fenêtre principale
        primaryStage.setTitle("Hello FX!");
        primaryStage.setWidth(300);
        primaryStage.setHeight(200);
//instanciation d'un label
        Label unLabel = new Label("Nombre de clique : ");
//instanciation d'un bouton
        Button unBouton = new Button("Cliquez-moi !");
//écoute du bouton
        unBouton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
//action à effectuer au clique
                if(event.getSource()==unBouton)
                {
                    setNbClick(getNbClick() + 1);
                    unLabel.setText("Nombre de clique : " +
                            getNbClick());
                }
            }
        });//layout vertical
        VBox root = new VBox(25);
//centrage des éléments de la VBox
        root.setAlignment(Pos.CENTER);
//instanciation de la scene avec la VBox root comme parent
        Scene uneScene = new Scene(root, 300,200);
//ajout des éléments à la vbox : le label puis le bouton en dessous
        root.getChildren().addAll(unLabel, unBouton);
//définition de la scène attribuée à la Stage
        primaryStage.setScene(uneScene);
//interdiction du redimentionnement
        primaryStage.setResizable(false);
//affichage à l'écran
        primaryStage.show();
//centrage de la fenêtre au centre de l'écran
        primaryStage.centerOnScreen();
    }
    /*================== getter & setter de la variable nbClick
    ==================*/
    public int getNbClick() {
        return nbClick;
    }
    public void setNbClick(int nbClick) {
        this.nbClick = nbClick;
    }
}


