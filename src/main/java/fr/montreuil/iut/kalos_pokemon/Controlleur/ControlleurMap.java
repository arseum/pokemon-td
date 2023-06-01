package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.Vue.EnnemiSprite;
import fr.montreuil.iut.kalos_pokemon.Vue.TerrainVue;
import fr.montreuil.iut.kalos_pokemon.Vue.TourSprite;
import fr.montreuil.iut.kalos_pokemon.modele.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControlleurMap implements Initializable {

    @FXML
    private Pane pane;

    private Timeline gameLoop;

    private int frame;
    private TerrainVue terrainVue;

    private TerrainVue terrainDecor;
    private Game game;

    //todo ZONE TEST ATTRIBUT
    @FXML
    private Label testDrag;
    @FXML
    private VBox testDrop;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //inevitable debut de initialize
        //game = new Game();
        game = new Game("savane");
        terrainVue = new TerrainVue();

        TilePane map = terrainVue.genererMapAvecDecor(game.getTerrain());

        pane.getChildren().add(map);
        //init game loop + label utile
        initAnimation();
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

        //ajout d'un lambda sur la map lors d'un click
        pane.setOnMouseClicked(event -> {
            for (Node node : pane.getChildren()) {
                if (node instanceof Circle && event.getTarget() instanceof TilePane) {
                    if (node.isVisible()) {
                        node.setVisible(false);
                    }
                }
            }
        });

        //ajout d'un poussifeu
        game.ajouteTour(new Poussifeu(5 * 32, 5 * 32));

        //lancement de la game loop
        gameLoop.play();

        //todo: ZONE DE TEST

        //ControleurAjoutTour t = new ControleurAjoutTour(4);
        //pane.addEventHandler(MouseEvent.MOUSE_CLICKED, t);

        ObservateurClicSelectionTour o = new ObservateurClicSelectionTour(pane);
        ObservateurMouvementSourisAjoutTour mv = new ObservateurMouvementSourisAjoutTour(o, pane, game);
        testDrag.addEventHandler(MouseEvent.MOUSE_CLICKED, o);
        pane.addEventHandler(MouseEvent.MOUSE_MOVED, mv);


/*
        boolean poussifeuSelect = false;
        BooleanProperty p = new SimpleBooleanProperty(false);
        //ObservableBooleanValue p2 = new SimpleBooleanProperty(p.getValue());
        //p.bind(p2);

        testDrag.setOnMouseClicked( e -> {
            System.out.println("Poussifeu Select");
            if(p.getValue() == false) p.setValue(true);
            else p.setValue(false);
        });

        pane.setOnMouseMoved( e -> {
            if(p.getValue()){
                Image i;
                try {
                    i = new Image(Objects.requireNonNull(EnnemiSprite.class.getResource( "poussifeu.png")).openStream());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

        pane.setOnMouseEntered( e -> {
                    if(p.getValue()){
                        Image i;
                        try {
                            i = new Image(Objects.requireNonNull(EnnemiSprite.class.getResource( "poussifeu.png")).openStream());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        //Image i = new Image("/home/zen/Documents/Cours/BUT_Informatique/Semestre_2/SAE/Dev/pokemon-td/src/main/resources/fr/montreuil/iut/kalos_pokemon/Vue/poussifeu.png");
                        //pane.setCursor(Cursor.HAND);

                        //pane.setCursor(new ImageCursor(i));
                        //System.out.println(e.getX());

                        if(e.getX() < 500){
                            pane.setCursor(new ImageCursor(i));
                        }else {
                            pane.setCursor(Cursor.HAND);
                        }
                    }
                }
        );



        pane.setOnMouseClicked( e -> {
            //System.out.println("click");
            //System.out.println(e.getX() + "," + e.getY());
            if(p.getValue()){
                int x = (int) e.getX();
                int y = (int) e.getY();
                int heightPane = (int)pane.getHeight();
                int widthPane = (int)pane.getWidth();
                if( 0 <= x && x <= widthPane && 0 <= y && y <= heightPane){
                    game.ajouteTour(new Poussifeu(x, y));
                }
                p.setValue(false);
                pane.setCursor(Cursor.DEFAULT);
            }
        });
*/
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
                        gameLoop.stop();
                    }
                    if (frame % 2 == 0) {
                        game.deplacment();
                    }
                    if (frame % 30 == 0) {
                        game.demiSeconde();
                    }

                    //simulation d'une wave ou des togepi spon toutes les 5s
                    //if (frame % (60 * 5 ) == 0) {
                    if (frame % (60 * 5 ) == 0) {
                        //game.ajouteEnnemi(new Togepi(0, 6 * 32, game));
                        //game.ajouteEnnemi(new Togepi(0, 3 * 32, game));
                        //game.ajouteEnnemi(new Togepi(0, 1 * 32, game));
                        int[] caseDepart = game.getTerrain().caseDepart();
                        //game.ajouteEnnemi(new Togepi(caseDepart[0] * Parametres.tailleTuile, caseDepart[1] * Parametres.tailleTuile, game));
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

        Circle c = new Circle(3);
        c.centerXProperty().bind(ennemi.xProperty());
        c.centerYProperty().bind(ennemi.yProperty());
        pane.getChildren().add(c);
    }

    private void creerTourSprite(Tour tour) throws IOException {
        TourSprite Sprite = new TourSprite(tour);
        Sprite.getSprite().xProperty().bind(tour.xProperty().add(-(Sprite.getSprite().getImage().getWidth() / 2)));
        Sprite.getSprite().yProperty().bind(tour.yProperty().add(-(Sprite.getSprite().getImage().getWidth() / 2)));
        pane.getChildren().add(Sprite.getSprite());
        pane.getChildren().add(Sprite.getRange());

        Sprite.getSprite().setOnMouseClicked(event -> {
            Sprite.getRange().setVisible(true);
        });

        //test
        Circle c = new Circle(3);
        c.setCenterX(tour.getX());
        c.setCenterY(tour.getY());
        pane.getChildren().add(c);

    }

}
