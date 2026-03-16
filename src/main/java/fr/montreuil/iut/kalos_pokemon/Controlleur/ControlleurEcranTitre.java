package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.SoundManager;
import fr.montreuil.iut.kalos_pokemon.main;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

public class ControlleurEcranTitre {

    @FXML
    private StackPane fondTitre;
    @FXML
    private VBox contenu;
    @FXML
    private Label titrePrincipal;
    @FXML
    private Button boutonJouer;
    @FXML
    private Label credits;

    @FXML
    public void initialize() {
        fondTitre.setOpacity(0);
        titrePrincipal.setOpacity(0);
        boutonJouer.setOpacity(0);
        credits.setOpacity(0);

        titrePrincipal.setTranslateY(-50);

        SoundManager.init();

        lancerAnimations();
    }

    private void lancerAnimations() {
        // Fond fade-in (0s -> 0.5s)
        FadeTransition fadeFond = new FadeTransition(Duration.millis(500), fondTitre);
        fadeFond.setFromValue(0);
        fadeFond.setToValue(1);

        // Titre translate Y + fade-in (0.5s -> 1.3s)
        FadeTransition fadeTitre = new FadeTransition(Duration.millis(800), titrePrincipal);
        fadeTitre.setFromValue(0);
        fadeTitre.setToValue(1);

        TranslateTransition slideTitre = new TranslateTransition(Duration.millis(800), titrePrincipal);
        slideTitre.setFromY(-50);
        slideTitre.setToY(0);

        ParallelTransition animTitre = new ParallelTransition(fadeTitre, slideTitre);

        // Bouton fade-in (1.3s -> 1.8s)
        FadeTransition fadeBouton = new FadeTransition(Duration.millis(500), boutonJouer);
        fadeBouton.setFromValue(0);
        fadeBouton.setToValue(1);

        // Credits fade-in avec le bouton
        FadeTransition fadeCredits = new FadeTransition(Duration.millis(500), credits);
        fadeCredits.setFromValue(0);
        fadeCredits.setToValue(0.5);

        // Bouton pulse infini
        ScaleTransition pulse = new ScaleTransition(Duration.millis(800), boutonJouer);
        pulse.setFromX(1.0);
        pulse.setFromY(1.0);
        pulse.setToX(1.05);
        pulse.setToY(1.05);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setAutoReverse(true);

        // Sequence
        SequentialTransition sequence = new SequentialTransition(
                fadeFond,
                new PauseTransition(Duration.millis(100)),
                animTitre,
                new ParallelTransition(fadeBouton, fadeCredits)
        );

        sequence.setOnFinished(e -> pulse.play());

        // Jouer le jingle quand le titre apparait
        fadeFond.setOnFinished(e -> SoundManager.playJingle());

        sequence.play();
    }

    @FXML
    public void jouer() {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), fondTitre);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(main.class.getResource("acceuil.fxml"));
                Parent accueil = loader.load();
                Scene scene = fondTitre.getScene();
                scene.setRoot(accueil);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        fadeOut.play();
    }
}
