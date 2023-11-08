package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.main;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Map.BFS;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonNiveau1.setCursor(Cursor.HAND);
        imageNiveau1.setImage(new Image("File:" + Parametres.cheminInterface + "niveau 1.png"));
        imageNiveau2.setImage(new Image("File:" + Parametres.cheminInterface + "niveau 2.png"));
        imageNiveau3.setImage(new Image("File:" + Parametres.cheminInterface + "niveau 3.png"));

    }
    @FXML
    public void launchNiveau1() throws IOException {
        //
        Parametres.setMap("savane");
        changeScene();
    }

    @FXML
    public void launchNiveau2() throws IOException {
        //Game.resetGame();
        Parametres.setMap("neige");
        changeScene();
    }
    @FXML
    public void launchNiveau3() throws IOException {
        //Game.resetGame();
        Parametres.setMap("eau");
        changeScene();
    }

    private void changeScene() throws IOException {
        //todo : à voir si reset bfs et game separe
        //Peut etre que bfs sera un attribut
        BFS.resetBFS();
        Game.resetGame();
        FXMLLoader niveau = new FXMLLoader(main.class.getResource("vueJeu.fxml"));
        Parent p = niveau.load();
        Scene scene = buttonNiveau1.getScene();
        scene.setRoot(p);
    }
}
