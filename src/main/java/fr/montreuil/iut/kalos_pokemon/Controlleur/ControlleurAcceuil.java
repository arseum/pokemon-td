package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.Progression;
import fr.montreuil.iut.kalos_pokemon.main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControlleurAcceuil implements Initializable {
    @FXML
    private Button buttonNiveau1;
    @FXML
    private ImageView imageNiveau1;
    @FXML
    private ImageView imageNiveau2;
    @FXML
    private ImageView imageNiveau3;
    @FXML
    private HBox hboxNiveaux;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonNiveau1.setCursor(Cursor.HAND);
        imageNiveau1.setImage(new Image("File:" + Parametres.cheminInterface + "niveau 1.png"));
        imageNiveau2.setImage(new Image("File:" + Parametres.cheminInterface + "niveau 2.png"));
        imageNiveau3.setImage(new Image("File:" + Parametres.cheminInterface + "niveau 3.png"));

        for (int i = 0; i < hboxNiveaux.getChildren().size(); i++) {
            if (!Progression.estDebloque(i + 1)) {
                VBox vbox = (VBox) hboxNiveaux.getChildren().get(i);
                vbox.getStyleClass().add("vbox-verrouille");

                // Désactiver le bouton
                for (Node child : vbox.getChildren()) {
                    if (child instanceof Button) {
                        child.setDisable(true);
                    }
                }

                // Superposer un cadenas sur l'ImageView
                for (int j = 0; j < vbox.getChildren().size(); j++) {
                    Node child = vbox.getChildren().get(j);
                    if (child instanceof ImageView) {
                        Label cadenas = new Label("\uD83D\uDD12");
                        cadenas.getStyleClass().add("label-cadenas");
                        StackPane stack = new StackPane(child, cadenas);
                        vbox.getChildren().set(j, stack);
                        break;
                    }
                }
            }
        }
    }

    @FXML
    public void launchNiveau1() throws IOException {
        Parametres.setMap("savane");
        changeScene();
    }

    @FXML
    public void launchNiveau2() throws IOException {
        Parametres.setMap("neige");
        changeScene();
    }
    @FXML
    public void launchNiveau3() throws IOException {
        Parametres.setMap("eau");
        changeScene();
    }

    private void changeScene() throws IOException {
        FXMLLoader niveau = new FXMLLoader(main.class.getResource("vueJeu.fxml"));
        Parent p = niveau.load();
        Scene scene = buttonNiveau1.getScene();
        scene.setRoot(p);
    }
}
