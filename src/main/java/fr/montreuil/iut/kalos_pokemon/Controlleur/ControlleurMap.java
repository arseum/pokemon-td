package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.Vue.*;
import fr.montreuil.iut.kalos_pokemon.modele.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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

    @FXML
    private Label nomTourMenu;

    @FXML
    private Label niveauTourMenu;

    @FXML
    private StackPane imageTourMenu;

    @FXML
    private Button vendreTourMenu;

    @FXML
    private Button ameliorerTourMenu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //inevitable debut de initialize
        game = new Game("savane");
        terrainVue = new TerrainVue();
        frame = new SimpleIntegerProperty(0);
        Parametres.chargeImage();

        //todo Ajouts Zen
        //scene.setPrefWidth(game.getTerrain().getHauteurTerrain() + 100);
        scene.setPrefWidth(1300);
        TilePane map = terrainVue.genererMapAvecDecor(game.getTerrain());
        pane.setPrefHeight(game.getTerrain().getHauteurTerrain());
        pane.setPrefWidth(game.getTerrain().getLargeurTerrain());
        pane.getChildren().add(map);
        backgroundMenuBas.setFitWidth(game.getTerrain().getLargeurTerrain());

        String[] listeTour = {"poussifeu", "salameche", "magneti", "granivol", "grenousse", "nidoran"};
        CreateurMenu createurMenu = new CreateurMenu(listeTour, game.PokedollarProperty().get());
        createurMenu.creationMenu(conteneurTourMenu);
        ObsPokedollarMenuAchat testPoke2 = new ObsPokedollarMenuAchat(conteneurTourMenu, listeTour);
        game.PokedollarProperty().addListener(testPoke2);

        ObsClicSurTour clicSurTour = new ObsClicSurTour(game);
        ObsTourCarteSelectionnee tourCarteSelectionnee = new ObsTourCarteSelectionnee(nomTourMenu,imageTourMenu);
        ObsChangementNiveauSurTourSelectionnee chgNiveauTour = new ObsChangementNiveauSurTourSelectionnee(clicSurTour, niveauTourMenu, vendreTourMenu, ameliorerTourMenu);
        clicSurTour.nomTour.addListener(tourCarteSelectionnee);
        clicSurTour.niveauTour.addListener(chgNiveauTour);

        vendreTourMenu.visibleProperty().bind(clicSurTour.unetourCarteSelectionnee);
        ameliorerTourMenu.visibleProperty().bind(clicSurTour.unetourCarteSelectionnee.and(clicSurTour.niveauTour.lessThan(Parametres.niveauEvolutionTour)));
        nomTourMenu.visibleProperty().bind(clicSurTour.unetourCarteSelectionnee);
        niveauTourMenu.visibleProperty().bind(clicSurTour.unetourCarteSelectionnee);

        vendreTourMenu.setOnAction( e -> {
            Boolean tourSelectionnee = clicSurTour.unetourCarteSelectionnee.get();
            String idTourSelectionnee = clicSurTour.idTourSelectionnee.get();
            if(tourSelectionnee){
                Tour t = game.retourneTourAPartirId(idTourSelectionnee);
                game.vendreTour(t);
                clicSurTour.unetourCarteSelectionnee.set(false);
                clicSurTour.nomTour.set("placeholder");

            }
        });

        ameliorerTourMenu.setOnAction( e -> {
            Boolean tourSelectionnee = clicSurTour.unetourCarteSelectionnee.get();
            String idTourSelectionnee = clicSurTour.idTourSelectionnee.get();
            if(tourSelectionnee){
                Tour t = game.retourneTourAPartirId(idTourSelectionnee);
                game.ameliorerTour(t);
                clicSurTour.niveauTour.set(t.getLevel());

            }
        });

        //Permet de deselectionner une tour lorsqu'on clic ailleurs sur la map
        pane.setOnMouseClicked( e -> {
            Node n = (Node)e.getTarget();
            if(n.getId() == null || !n.getId().contains("Tour")){
                clicSurTour.unetourCarteSelectionnee.set(false);
                clicSurTour.nomTour.set("placeholder");
            }
        });

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
                            creerTourSprite(a, clicSurTour);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                else if (c.wasRemoved())
                    for (Tour a : c.getRemoved()) {
                        pane.getChildren().remove(pane.lookup("#" + a.getId()));
                        pane.getChildren().remove(pane.lookup("#" + "range_" + a.getId()));
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

        //todo: Ajouts Zen
        ObsClicMenuAchatTour menuTourObs = new ObsClicMenuAchatTour(scene, game);
        ObsMvtClicAjoutTour ajoutTour = new ObsMvtClicAjoutTour(menuTourObs, game);
        conteneurTourMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, menuTourObs);
        scene.addEventHandler(MouseEvent.MOUSE_MOVED, ajoutTour);
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, ajoutTour);

        ObsTourEnCoursAjout obsTourEnCoursAjout = new ObsTourEnCoursAjout(scene);
        menuTourObs.estSelectionnee.addListener(obsTourEnCoursAjout);

        //lancement de la game loop
        gameLoop.play();
    }

    private void initAnimation() throws IOException {
        gameLoop = new Timeline();
        game.nbFrameProperty().bind(frame);
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        int[] caseDepart = game.getTerrain().getCaseDepart();

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
        labelDollar.setLayoutX(920);
        labelDollar.setLayoutY(10);
        labelDollar.setPrefWidth(70);
        labelDollar.setPrefHeight(15);
        labelDollar.setAlignment(Pos.CENTER);
        labelDollar.getStyleClass().add("label");
        game.PokedollarProperty().addListener((observableValue, number, t1) -> {
            labelDollar.setText(t1 + " $");
            labelDollar.setPrefWidth(t1.toString().length() * 10 + 40);
        });

        //pour les hp
        Label labelVie = new Label("15 \u2764");
        labelVie.setLayoutX(820);
        labelVie.setLayoutY(10);
        labelVie.setPrefWidth(65);
        labelVie.setPrefHeight(15);
        labelVie.setAlignment(Pos.CENTER);
        labelVie.getStyleClass().add("label");
        game.vieProperty().addListener(((observableValue, number, t1) -> {
            labelVie.setText(t1.toString() + " \u2764");
        }));

        Label labelWave = new Label("Vague: 1");
        labelWave.setLayoutX(20);
        labelWave.setLayoutY(10);
        labelWave.setPrefWidth(125);
        labelWave.setPrefHeight(15);
        labelWave.setAlignment(Pos.CENTER);
        labelWave.getStyleClass().add("label");
        game.cptWaveProperty().addListener(((observableValue, number, t1) -> {
            labelWave.setText( "Vague : " + t1.toString());
        }));


        pane.getChildren().add(labelDollar);
        pane.getChildren().add(labelVie);
        pane.getChildren().add(labelWave);

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

    private void creerTourSprite(Tour tour, ObsClicSurTour obsClicSurTour) throws IOException {
        TourSprite sprite = new TourSprite(tour);

        //Niveau modele place la tour niveau coin sup gauche, par exemple (0,0) ou bien (32,32)
        //Le sprite a les memes coordonnes - le offset
        //L'image étant plus grande que la tuile il y a un offset pour compenser
        sprite.getSprite().xProperty().bind(tour.xProperty().add(-Parametres.offsetXTour));
        sprite.getSprite().yProperty().bind(tour.yProperty().add(-Parametres.offsetYTour));

        pane.getChildren().add(sprite.getSprite());
        pane.getChildren().add(sprite.getRange());

        sprite.getRange().visibleProperty().bind(obsClicSurTour.unetourCarteSelectionnee.and(obsClicSurTour.idTourSelectionnee.isEqualTo(sprite.getSprite().getId())));

        //ajout d'un onMouseClicked qui permet de afficher la range de la tour/details
        //Permet d'afficher la range de la tour et les actions possibles
        sprite.getSprite().addEventHandler(MouseEvent.MOUSE_CLICKED, obsClicSurTour);

        //Permet de changer le sprite de la tour lorsqu'elle évolue
        tour.levelProperty().addListener( ((observableValue, number, t1) -> {
            if(t1.equals(Parametres.niveauEvolutionTour)){
                sprite.getSprite().setImage(new Image("file:" + Parametres.cheminSpritePokemon + tour.getNom() + ".png"));
                obsClicSurTour.nomTour.set(tour.getNom());
            }
        }));

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
