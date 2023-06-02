package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.Vue.*;
import fr.montreuil.iut.kalos_pokemon.modele.*;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Camerupt;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Tiplouf;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Togepi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
        game = new Game("savane");
        terrainVue = new TerrainVue();

        //TilePane map = terrainVue.genereMap(game.getTerrain().getArrierePlan());
        TilePane map = terrainVue.genererMapAvecDecor(game.getTerrain());
        //TilePane mapDecor = terrainVue.genereMap(game.getTerrain().getDecor());
        pane.getChildren().add(map);
        //pane.getChildren().add(mapDecor);
        /*
        if (game.getTerrain().getDecor() != null) {
            pane.getChildren().add(terrainDecor.genereMap(game.getTerrain().getDecor()));
        }

         */
        //init game loop + label utile
        try {
            initAnimation();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        initLabel();

        //creation du listener qui va ecouter la list des ennemi de game
        ListChangeListener<Ennemi> listenEnnemi = (c -> {
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
        game.getListEnnemi().addListener(listenEnnemi);

        //de meme pour les tours
        ListChangeListener<Tour> listenTour = (c -> {
            while (c.next()) {
                if (c.wasAdded())
                    for (Tour a : c.getAddedSubList()) {
                        try {
                            creerTourSprite(a);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                else if (c.wasRemoved())
                    for (Tour a : c.getRemoved()) {
                        pane.getChildren().remove(pane.lookup("#" + a.getId()));
                    }
            }
        });
        game.getListTour().addListener(listenTour);

        //de meme pour les projectiles
        ListChangeListener<Projectile> listenProjectiles = (c -> {
            while (c.next()) {
                if (c.wasAdded())
                    for (Projectile p : c.getAddedSubList()) {
                        try {
                            creerTirSprite(p);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                else if (c.wasRemoved())
                    for (Projectile p : c.getRemoved()) {
                        pane.getChildren().remove(pane.lookup("#" + p.getId()));
                    }
            }
        });
        game.getListProjectile().addListener(listenProjectiles);


        //ajout de tours pour test
        game.ajouteTour(new Poussifeu(6 * 32, 5 * 32, game));
        game.ajouteTour(new Granivol(4 * 32, 9 * 32, game));
        game.ajouteTour(new Grenousse(9 * 32, 4 * 32, game));
        //game.ajouteTour(new Magneti(3 * 32, 5 * 32, game));
        game.ajouteTour(new Venalgue(7 * 32, 8 * 32, game));


        //lancement de la game loop
        gameLoop.play();

    }

    private void initAnimation() throws IOException {
        gameLoop = new Timeline();
        frame = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        int[] caseDepart = game.getTerrain().caseDepart();

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev -> {

                    game.deplacment();

                    if (frame % 30 == 0) {
                        game.demiSeconde();
                    }

                    if (frame % (60 * 10) == 0) {
                        game.ajouteEnnemi(new Camerupt(caseDepart[0] * 32, caseDepart[1] * 32, game));
                    }

                    //simulation d'une wave ou des togepi spon toutes les 5s
                    if (frame % (60 * 5) == 0) {
                        game.ajouteEnnemi(new Togepi(caseDepart[0] * Parametres.tailleTuile, caseDepart[1] * Parametres.tailleTuile, game));
                    }


                    //a faire pour chaque frame:
                    /*
                    for (int i = ensembleTirVue.size() - 1; i >= 0; i--) {
                        if (ensembleTirVue.get(i).isActif()) {
                            try {
                                ensembleTirVue.get(i).bouge();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } //pas de else car il se peux que le "sprite tir" ne devienne plus actif en bougeant.
                        if (!ensembleTirVue.get(i).isActif()) {
                            if (ensembleTirVue.get(i) instanceof TirSprite) {
                                if (game.chercheEnnemi(((TirSprite) ensembleTirVue.get(i)).getIdCible()) != null)
                                    game.chercheEnnemi(((TirSprite) ensembleTirVue.get(i)).getIdCible()).diminueHP(((TirSprite) ensembleTirVue.get(i)).getDegatTir());
                                pane.getChildren().remove(pane.lookup("#" + ensembleTirVue.get(i).getHitBox().getId()));
                                ensembleTirVue.remove(i);
                            }

                        }
                    }

                     */

                    frame++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    private void initLabel() {
        // creation du label pour les pokedollar
        Label labelDollar = new Label("300 $");
        labelDollar.setLayoutX(900);
        labelDollar.setLayoutY(10);
        labelDollar.setPrefWidth(50);
        labelDollar.setPrefHeight(15);
        labelDollar.setAlignment(Pos.CENTER);
        labelDollar.getStyleClass().add("labelDollar");
        game.PokedollarProperty().addListener((observableValue, number, t1) -> {
            labelDollar.setText(t1 + " $");
            labelDollar.setPrefWidth(t1.toString().length() * 10 + 40);
        });
        pane.getChildren().add(labelDollar);
    }

    /**
     * creer une entite sprite pour un ennemi + fait les bind pour les deplacement
     */
    private void creerEnnemiSprite(Ennemi ennemi) throws IOException {
        EnnemiSprite nouveauEnnemiSprite = new EnnemiSprite(ennemi);
        nouveauEnnemiSprite.getHitBox().xProperty().bind(ennemi.xProperty());
        nouveauEnnemiSprite.getHitBox().yProperty().bind(ennemi.yProperty());
        pane.getChildren().add(nouveauEnnemiSprite.getSprite());
    }

    private void creerTourSprite(Tour tour) throws IOException {
        TourSprite sprite = new TourSprite(tour);
        sprite.getSprite().xProperty().bind(tour.xProperty().add(-(sprite.getSprite().getImage().getWidth() / 2)));
        sprite.getSprite().yProperty().bind(tour.yProperty().add(-(sprite.getSprite().getImage().getWidth() / 2)));
        pane.getChildren().add(sprite.getSprite());
        pane.getChildren().add(sprite.getRange());

        //ajout d'un onMouseClicked qui permet de afficher la range de la tour/details
        sprite.getSprite().setOnMouseClicked(e -> {
            sprite.getSprite().toFront();
            sprite.getRange().setVisible(!sprite.getRange().isVisible());
        });

    }

    private void creerTirSprite(Projectile p) throws IOException {
        TirSprite sprite = new TirSprite(p);

        sprite.getHitBox().xProperty().bind(p.xProperty());
        sprite.getHitBox().yProperty().bind(p.yProperty());
        p.idImageProperty().addListener( ((observableValue, number, t1) -> {
            try {
                sprite.updateImage(t1.intValue());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }) );
        pane.getChildren().add(sprite.getHitBox());
    }

    private void creerZoneSprite(TourZone tour) throws IOException {
        ZoneSprite sprite = new ZoneSprite(tour);

        sprite.getHitBox().xProperty().bind(tour.xProperty().add(-(sprite.getHitBox().getImage().getWidth() / 2)));
        sprite.getHitBox().yProperty().bind(tour.yProperty().add(-(sprite.getHitBox().getImage().getWidth() / 2)));
        sprite.getHitBox().visibleProperty().bind(tour.actifProperty());

        pane.getChildren().add(sprite.getHitBox());
    }

}
