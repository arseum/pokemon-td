package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Vue.TerrainVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import fr.montreuil.iut.kalos_pokemon.Vue.EnnemiSprite;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Togepi;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlleurMap implements Initializable {

    @FXML
    private Pane pane;

    private Timeline gameLoop;

    private int frame;
    private TerrainVue terrainVue;
    private Game game;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initAnimation();

        game = new Game();
        terrainVue = new TerrainVue();

        // creation du label pour les pokedollar
        Label label = new Label("300 $");
        label.setLayoutX(920);
        label.setLayoutY(10);
        label.setPrefWidth(50);
        label.setPrefHeight(15);
        label.setAlignment(Pos.CENTER);
        label.getStyleClass().add("labelDollar");
        game.PokedollarProperty().addListener((observableValue, number, t1) -> label.setText(t1 + " $"));

        pane.getChildren().addAll(terrainVue.genereMap(game.getTerrain().getMap_test()),label);

        //test
        Togepi togepi = new Togepi(0,5*32);
        try {
            EnnemiSprite a = new EnnemiSprite();
            a.getHitBox().xProperty().bind(togepi.xProperty());
            a.getHitBox().yProperty().bind(togepi.yProperty());
            pane.getChildren().add(a.getHitBox());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        game.ajouteEnnemi(togepi);

        //lancement de la game loop
        gameLoop.play();

    }

    private void initAnimation() {
        gameLoop = new Timeline();
        frame=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if(frame==5000){
                        System.out.println("fini");
                        gameLoop.stop();
                    }
                    else if (frame%2==0){
                        game.uneFrame();
                    }
                    if (frame == 1000){
                        game.getListEnnemi().get(0).meurt();
                    }
                    frame++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

}
