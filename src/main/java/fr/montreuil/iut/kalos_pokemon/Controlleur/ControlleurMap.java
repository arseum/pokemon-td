package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.Vue.*;
import fr.montreuil.iut.kalos_pokemon.modele.*;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.*;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlleurMap implements Initializable {

    @FXML
    private Pane pane;

    private Timeline gameLoop;

    private IntegerProperty frame;
    private TerrainVue terrainVue;
    private Game game;

    @FXML
    private BorderPane scene;

    @FXML
    private HBox conteneurTourMenu;

    @FXML
    private ImageView backgroundMenuTour;

    @FXML
    private ImageView backgroundMenuBas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //inevitable debut de initialize
        game = new Game("savane");
        terrainVue = new TerrainVue();
        frame = new SimpleIntegerProperty(0);

        //todo Ajouts Zen
        TilePane map = terrainVue.genererMapAvecDecor(game.getTerrain());
        pane.setPrefHeight(game.getTerrain().getHauteurTerrain());
        pane.setPrefWidth(game.getTerrain().getLargeurTerrain());
        pane.getChildren().add(map);
        backgroundMenuBas.setFitWidth(game.getTerrain().getLargeurTerrain());

        String[] listeTour = {"poussifeu", "salameche", "magneti", "granivol", "grenousse", "venalgue"};
        CreateurMenu createurMenu = new CreateurMenu(listeTour, game.PokedollarProperty().get());
        createurMenu.creationMenu(conteneurTourMenu);
        ObsPokedollar testPoke2 = new ObsPokedollar(conteneurTourMenu, listeTour);
        game.PokedollarProperty().addListener(testPoke2);

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
        ListChangeListener<Attaque> listenProjectiles = (c -> {
            while (c.next()) {
                if (c.wasAdded())
                    for (Attaque a : c.getAddedSubList()) {
                        try {
                            creerTirSprite(a);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                else if (c.wasRemoved())
                    for (Attaque a : c.getRemoved()) {
                        pane.getChildren().remove(pane.lookup("#" + a.getId()));
                    }
            }
        });
        game.getListProjectile().addListener(listenProjectiles);


        //ajout de tours pour test
        //game.ajouteTour(new Poussifeu(6 * 32, 5 * 32));
        //game.ajouteTour(new Granivol(4 * 32, 9 * 32));
        //game.ajouteTour(new Grenousse(9 * 32, 4 * 32));
        //game.ajouteTour(new Magneti(3 * 32, 5 * 32));
        //game.ajouteTour(new Venalgue(7 * 32, 8 * 32));

        //lancement de la game loop
        gameLoop.play();

        //todo: Ajouts Zen
        ObsClicMenuTour menuTourObs = new ObsClicMenuTour(scene, game);
        ObsMvtClicAjoutTour ajoutTour = new ObsMvtClicAjoutTour(menuTourObs, pane, game);
        conteneurTourMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, menuTourObs);
        scene.addEventHandler(MouseEvent.MOUSE_MOVED, ajoutTour);
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, ajoutTour);

        ObsTourEnCoursAjout obsTourEnCoursAjout = new ObsTourEnCoursAjout(scene);
        menuTourObs.estSelectionnee.addListener(obsTourEnCoursAjout);

    }

    private void initAnimation() throws IOException {
        gameLoop = new Timeline();
        game.nbFrameProperty().bind(frame);
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        int[] caseDepart = game.getTerrain().caseDepart();

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev -> {
                    game.uneFrame();
                    try {
                        game.wave();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    /*if (frame.get() % 120 == 0 && frame.get() > 119)
                        game.ajouteEnnemi(new Fantominus(caseDepart[0] * Parametres.tailleTuile, caseDepart[1] * Parametres.tailleTuile, game));

                     */

                    frame.set(frame.get()+1);
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
        //sprite.getSprite().xProperty().bind(tour.xProperty().add(-(sprite.getSprite().getImage().getWidth() / 2)));
        //sprite.getSprite().yProperty().bind(tour.yProperty().add(-(sprite.getSprite().getImage().getWidth() / 2)));

        //todo : Modifs Zen
        //Niveau modele place la tour niveau coin sup gauche, par exemple (0,0) ou bien (32,32)
        //Le sprite a les memes coordonnes - le offset
        //L'image étant plus grande que la tuile il y a un offset pour compenser
        sprite.getSprite().xProperty().bind(tour.xProperty().add(- Parametres.offsetXTour));
        sprite.getSprite().yProperty().bind(tour.yProperty().add(- Parametres.offsetYTour));
        //fin modifs Zen

        pane.getChildren().add(sprite.getSprite());
        pane.getChildren().add(sprite.getRange());

        //ajout d'un onMouseClicked qui permet de afficher la range de la tour/details
        sprite.getSprite().setOnMouseClicked(e -> {
            sprite.getSprite().toFront();
            sprite.getRange().setVisible(!sprite.getRange().isVisible());
        });

    }

    private void creerTirSprite(Attaque a) throws IOException {
        TirSprite sprite = new TirSprite(a);
        if (a instanceof Zone) {
            sprite.getHitBox().visibleProperty().bind(((Zone) a).actifProperty());
            sprite.getHitBox().xProperty().bind(a.xProperty().add(-(sprite.getHitBox().getImage().getWidth() / 2)));
            sprite.getHitBox().yProperty().bind(a.yProperty().add(-(sprite.getHitBox().getImage().getWidth() / 2)));
            sprite.getHitBox().getStyleClass().add("magneti_zone");
        } else {
            sprite.getHitBox().xProperty().bind(a.xProperty());
            sprite.getHitBox().yProperty().bind(a.yProperty());
        }
        a.idImageProperty().addListener(((observableValue, number, t1) -> {
            try {
                sprite.updateImage(t1.intValue());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        pane.getChildren().add(sprite.getHitBox());
    }


}
