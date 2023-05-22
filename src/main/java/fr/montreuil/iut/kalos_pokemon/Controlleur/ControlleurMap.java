package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Vue.TerrainVue;
import fr.montreuil.iut.kalos_pokemon.modele.Poussifeu;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import fr.montreuil.iut.kalos_pokemon.Vue.EntiteSprite;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Togepi;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlleurMap implements Initializable {

    @FXML
    private Pane pane;
    private Timeline gameLoop;
    private int temps;
    private TerrainVue terrainVue;
    private Game game;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initAnimation();

        game = new Game();
        terrainVue = new TerrainVue();

        pane.getChildren().add(terrainVue.genereMap(game.getTerrain().getMap_test())); //affiche terrain

        Togepi togepi = new Togepi(0,5*32);
        Poussifeu poussifeu = new Poussifeu(5*32,5*32);

        try {
            EntiteSprite a;
            a = EntiteSprite.creerEntite("togepi");
            a.getHitBox().xProperty().bind(togepi.xProperty());
            a.getHitBox().yProperty().bind(togepi.yProperty());
            pane.getChildren().add(a.getHitBox());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            EntiteSprite b;
            b = EntiteSprite.creerEntite("poussifeu");
            b.getHitBox().xProperty().bind(poussifeu.xProperty());
            b.getHitBox().yProperty().bind(poussifeu.yProperty());
            pane.getChildren().add(b.getHitBox());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        game.ajouteEnnemi(togepi);

        gameLoop.play();

    }


    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if(temps==5000){
                        System.out.println("fini");
                        gameLoop.stop();
                    }
                    else if (temps%2==0){
                        game.uneFrame();
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

}
