package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControlleurAcceuil implements Initializable {
    @FXML
    private Button buttonNiveau1;
    @FXML
    private ImageView imageNiveau1;
    @FXML
    private VBox Vbox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonNiveau1.setCursor(Cursor.HAND);
        imageNiveau1.setImage(new Image("File:" + Parametres.cheminInterface + "niveau 1.png"));
        Vbox.toBack();

    }

    public void launchNiveau1() throws IOException {

        FXMLLoader fxmlNiveau1 = new FXMLLoader(main.class.getResource("vueJeu.fxml"));
        Parent p = fxmlNiveau1.load();

        Scene scene = buttonNiveau1.getScene();

        scene.setRoot(p);

    }
}
