package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Vue.EnnemiSprite;
import fr.montreuil.iut.kalos_pokemon.Vue.TerrainVue;
import fr.montreuil.iut.kalos_pokemon.Vue.TourSprite;
import fr.montreuil.iut.kalos_pokemon.modele.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
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

    private int frame;
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

        //ajout d'un poussifeu
        Poussifeu poussifeu = new Poussifeu(5 * 32, 5 * 32);
        try {
            creerTourSprite(poussifeu);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //creation du listener qui va ecouter la list des ennemi de game
        ListChangeListener<Ennemi> listen =(c ->{
            while (c.next()) {
                if (c.wasAdded())
                    for (Ennemi a : c.getAddedSubList()) {
                        try {
                            creerEnnemiSprite(a);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                else if (c.wasRemoved())
                    for (Ennemi a : c.getRemoved()) {
                        pane.getChildren().remove(pane.lookup("#" + a.getId()));
                    }
            }
        });
        game.getListEnnemi().addListener(listen);

        //lancement de la game loop
        gameLoop.play();

    }

    private void initAnimation() {
        gameLoop = new Timeline();
        frame = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev -> {
                    if (frame == 5000) {
                        System.out.println("fini");
                        gameLoop.stop();
                    }
                    if (frame % 2 == 0) {
                        game.uneFrame();
                    }
                    if (frame % 30 == 0) {
                        game.demiSeconde();
                    }

                    //simulation d'une wave ou des togepi spon toutes les 5s
                    if (frame % (60*5) == 0){
                        Togepi togepi = new Togepi(0, 5 * 32);
                        game.ajouteEnnemi(togepi);
                    }
                    frame++;
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
    private void creerEnnemiSprite(Ennemi ennemi) throws IOException {
        EnnemiSprite Sprite = new EnnemiSprite(ennemi);
        Sprite.getHitBox().xProperty().bind(ennemi.xProperty());
        Sprite.getHitBox().yProperty().bind(ennemi.yProperty());
        pane.getChildren().add(Sprite.getHitBox());
    }

    private void creerTourSprite(Tour tour) throws IOException {
        TourSprite Sprite = new TourSprite(tour);
        Sprite.getSprite().xProperty().bind(tour.xProperty());
        Sprite.getSprite().yProperty().bind(tour.yProperty());
        pane.getChildren().add(Sprite.getSprite());

        Sprite.getSprite().setOnMouseClicked(event -> {
            if (Sprite.isClickActif()) {
                pane.getChildren().remove(Sprite.getRange());
            } else {
                pane.getChildren().add(Sprite.getRange());
            }
            Sprite.clickChange();
            System.out.println("Tour cliquée !");
        });

    }

}
