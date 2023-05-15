package kalos.pokemon_td.Controlleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import kalos.pokemon_td.Vue.EnnemiSprite;
import kalos.pokemon_td.Vue.TerrainVue;
import kalos.pokemon_td.modele.Game;
import kalos.pokemon_td.modele.Terrain;
import kalos.pokemon_td.modele.Togepi;

import java.io.*;
import java.net.URL;
import java.time.chrono.ThaiBuddhistChronology;
import java.util.Objects;
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

        pane.getChildren().add(terrainVue.genereMap(game.getTerrain().getMap_test()));

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
