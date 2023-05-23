package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Vue.EnnemiSprite;
import fr.montreuil.iut.kalos_pokemon.Vue.TerrainVue;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Poussifeu;
import fr.montreuil.iut.kalos_pokemon.modele.Togepi;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlleurMap implements Initializable {

    @FXML
    private Pane pane;

    private Timeline gameLoop;

    private int temps;
    private TerrainVue terrainVue;

    private TerrainVue terrainDecor;
    private Game game;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //inevitable debut de initialize
        game = new Game();
        terrainVue = new TerrainVue();
        terrainDecor = new TerrainVue();


        pane.getChildren().add(terrainVue.genereMap(game.getTerrain().getArrierePlan()));
        //Todo : le decor n'est plus charge
        if(game.getTerrain().getDecor() != null){
            pane.getChildren().add(terrainDecor.genereMap(game.getTerrain().getDecor()));
        }

        //init game loop + label utile
        initAnimation();
        initLabel();

        //ajout d'un togepi et d'un poussifeu
        Togepi togepi = new Togepi(0, 5 * 32);
        Poussifeu poussifeu = new Poussifeu(5 * 32, 5 * 32);
        game.ajouteEnnemi(togepi);
        try {
            creerSrite(togepi);
            EnnemiSprite poussifeuSprite = new EnnemiSprite("poussifeu");
            poussifeuSprite.getHitBox().xProperty().bind(poussifeu.xProperty());
            poussifeuSprite.getHitBox().yProperty().bind(poussifeu.yProperty());
            pane.getChildren().add(poussifeuSprite.getHitBox());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        gameLoop.play();

    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev -> {
                    if (temps == 5000) {
                        System.out.println("fini");
                        gameLoop.stop();
                    } else if (temps % 2 == 0) {
                        game.uneFrame();
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    private void initLabel() {
        // creation du label pour les pokedollar
        Label labelDollar = new Label("300 $");
        labelDollar.setLayoutX(920);
        labelDollar.setLayoutY(10);
        labelDollar.setPrefWidth(50);
        labelDollar.setPrefHeight(15);
        labelDollar.setAlignment(Pos.CENTER);
        labelDollar.getStyleClass().add("labelDollar");
        game.PokedollarProperty().addListener((observableValue, number, t1) -> labelDollar.setText(t1 + " $"));
        pane.getChildren().add(labelDollar);
    }

    /**
     * creer une entite sprite pour un ennemi + fait les bind pour les deplacement
     */
    private void creerSrite(Ennemi ennemi) throws IOException {
        EnnemiSprite togepiSrite = new EnnemiSprite(ennemi.getNom());
        togepiSrite.getHitBox().xProperty().bind(ennemi.xProperty());
        togepiSrite.getHitBox().yProperty().bind(ennemi.yProperty());
        pane.getChildren().add(togepiSrite.getHitBox());
    }

}
