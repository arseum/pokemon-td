package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.Vue.*;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Camerupt;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Tiplouf;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Togepi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Granivol;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Grenousse;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Poussifeu;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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
    private Game game;
    private ArrayList<TirSprite> ensembleTirVue;

    //todo ZONE TEST ATTRIBUT
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
        //game = new Game();
        game = new Game("savane");
        terrainVue = new TerrainVue();
        ensembleTirVue = new ArrayList<>();

        //todo Ajouts Zen
        TilePane map = terrainVue.genererMapAvecDecor(game.getTerrain());
        //scene.setPrefHeight(game.getTerrain().getHauteurTerrain() + 258);
        pane.setPrefHeight(game.getTerrain().getHauteurTerrain());
        pane.setPrefWidth(game.getTerrain().getLargeurTerrain());
        pane.getChildren().add(map);
        backgroundMenuBas.setFitWidth(game.getTerrain().getLargeurTerrain());
        //backgroundMenuBas.setFitHeight(258);

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


        //ajout d'un poussifeu
        //game.ajouteTour(new Poussifeu(6 * 32, 5 * 32, game));
        //game.ajouteTour(new Granivol(4 * 32, 9 * 32, game));
        //game.ajouteTour(new Grenousse(9 * 32, 4 * 32, game));
        //game.ajouteTour(new Magneti(6 * 32 , 5 * 32, game));


        //lancement de la game loop
        gameLoop.play();

        //todo: Ajouts Zen
        ObservateurMenuTourClic menuTourObs = new ObservateurMenuTourClic(scene, game);
        ObservateurAjoutTour ajoutTour = new ObservateurAjoutTour(menuTourObs, pane, game);
        conteneurTourMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, menuTourObs);
        scene.addEventHandler(MouseEvent.MOUSE_MOVED, ajoutTour);
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, ajoutTour);

        ObsTourEnCoursAjout obsTourEnCoursAjout = new ObsTourEnCoursAjout(scene);
        menuTourObs.estSelectionnee.addListener(obsTourEnCoursAjout);

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
                    if (frame == 5000) {
                        System.out.println("fini");
                        gameLoop.stop();
                    }
                    if (frame % 2 == 0) {
                        game.deplacment();
                    }
                    if (frame % 30 == 0) {
                        game.demiSeconde();
                    }
                    if (frame % (60 * 10) == 0) {
                        game.ajouteEnnemi(new Camerupt(caseDepart[0] * 32, caseDepart[1] * 32, game));
                    }

                    //simulation d'une wave ou des togepi spon toutes les 5s
                    if (frame % (60 * 5) == 0) {
                        //game.ajouteEnnemi(new Togepi(0, 6 * 32, game));
                        //game.ajouteEnnemi(new Togepi(0, 3 * 32, game));
                        //game.ajouteEnnemi(new Togepi(0, 1 * 32, game));

                        game.ajouteEnnemi(new Togepi(caseDepart[0] * Parametres.tailleTuile, caseDepart[1] * Parametres.tailleTuile, game));
                    }
                    if ((frame + 2) % (60 * 5) == 0) {
                        game.ajouteEnnemi(new Tiplouf(caseDepart[0] * 32, caseDepart[1] * 32, game));
                    }

                    //a faire pour chaque frame:
                    for (int i = ensembleTirVue.size() - 1; i >= 0; i--) {
                        try {
                            ensembleTirVue.get(i).bouge();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if (!ensembleTirVue.get(i).isActif()) {
                            pane.getChildren().remove(pane.lookup("#" + ensembleTirVue.get(i).getHitBox().getId()));
                            ensembleTirVue.remove(i);
                        }
                    }

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
        EnnemiSprite Sprite = new EnnemiSprite(ennemi);
        Sprite.getHitBox().xProperty().bind(ennemi.xProperty());
        Sprite.getHitBox().yProperty().bind(ennemi.yProperty());
        pane.getChildren().add(Sprite.getHitBox());


    }

    private void creerTourSprite(Tour tour) throws IOException {
        TourSprite Sprite = new TourSprite(tour);

        //todo : Modifs Zen
        //Niveau modele place la tour niveau coin sup gauche, par exemple (0,0) ou bien (32,32)
        //Le sprite a les memes coordonnes - le offset
        //L'image étant plus grande que la tuile il y a un offset pour compenser
        Sprite.getSprite().xProperty().bind(tour.xProperty().add(- Parametres.offsetXTour));
        Sprite.getSprite().yProperty().bind(tour.yProperty().add(- Parametres.offsetYTour));

        //fin modifs Zen

        pane.getChildren().add(Sprite.getSprite());
        pane.getChildren().add(Sprite.getRange());


        //ajout d'un onMouseClicked qui permet de afficher la range de la tour/details
        Sprite.getSprite().setOnMouseClicked(e -> {
            Sprite.getSprite().toFront();
            Sprite.getRange().setVisible(!Sprite.getRange().isVisible());
        });


        //ajout d'un listener pour creer des sprites lorsque la tour attaque
        tour.idCibleProperty().addListener(((observableValue, aBoolean, nouv) -> {
            if (nouv != null) {
                try {
                    creerTirSprite(tour, nouv);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }));
    }

    private void creerTirSprite(Tour tour, String idCible) throws IOException {
        TirSprite sprite = new TirSprite(tour);
        sprite.setCibleSprite((ImageView) pane.lookup("#" + idCible));

        sprite.getHitBox().setX(tour.getX());
        sprite.getHitBox().setY(tour.getY());

        pane.getChildren().add(sprite.getHitBox());
        ensembleTirVue.add(sprite);
    }

}
