package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.Vue.*;
import fr.montreuil.iut.kalos_pokemon.main;
import fr.montreuil.iut.kalos_pokemon.modele.*;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Magneti;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Nidoran;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Salameche;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.TourActif;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    private BooleanProperty pause;

    @FXML
    private BorderPane scene;

    @FXML
    private HBox conteneurTourMenu;

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

    public ControlleurMap() {
    }

    public void setPause(boolean etat) {
        pause.setValue(etat);
    }

    public BooleanProperty getPause() {
        return pause;
    }

    public BooleanProperty pauseProperty() {
        return pause;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //inevitable debut de initialize
        game = new Game("savane");
        terrainVue = new TerrainVue();
        frame = new SimpleIntegerProperty(0);
        pause = new SimpleBooleanProperty(false);
        Parametres.init();

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
            if (tourSelectionnee) {
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
                        if (a instanceof bouleDeFeu)
                            creerExploxionSprite(a,"salameche_exploxion.gif");
                    }
            }
        });
        game.getListProjectile().addListener(listenProjectiles);

        game.vieProperty().addListener((obs,old,nouv)-> {
            if ((int)nouv==0)
                partiePerdue();
        });

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
        game.getVague().nbFrameProperty().bind(frame);

        gameLoop.setCycleCount(Timeline.INDEFINITE);


        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                (ev -> {

                    game.uneFrame();
                    try {
                        game.getVague().wave();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    frame.set(frame.get()+1);
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    private void initLabel() {
        // creation du label pour les pokedollar
        Label labelDollar = new Label(this.game.getPokedollar() + " $");
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
        labelWave.setPrefWidth(100);
        labelWave.setPrefHeight(15);
        labelWave.setAlignment(Pos.CENTER);
        labelWave.getStyleClass().add("label");
        game.getVague().cptWaveProperty().addListener(((observableValue, number, t1) -> {
            labelWave.setText( "Vague : " + t1.toString());
        }));

        Button pauseButton = new Button("pause");

        pauseButton.setLayoutX(0);
        pauseButton.setLayoutY(0);
        pauseButton.setPrefWidth(125);
        pauseButton.setPrefHeight(15);
        pauseButton.setAlignment(Pos.CENTER);

        pauseButton.setOnAction(e-> {
            setPause(!pause.getValue());
                });

        pause.addListener((obs,old,nouv)-> {
            if (nouv.equals(true)){
                gameLoop.stop();
            }else {
                gameLoop.play();
            }
        });


        pane.getChildren().add(labelDollar);
        pane.getChildren().add(labelVie);
        pane.getChildren().add(labelWave);
        conteneurTourMenu.getChildren().add(pauseButton);

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

        tour.levelProperty().addListener( ((observableValue, number, t1) -> {
            if(t1.equals(Parametres.niveauEvolutionTour)){
                sprite.getSprite().setImage(new Image("file:" + Parametres.cheminSpritePokemon + tour.getNom() + ".png"));
                obsClicSurTour.nomTour.set(tour.getNom());
            }
        }));

        //modif pour l'animation de salamehce
        if (tour instanceof Salameche)
            ((Salameche) tour).actifProperty().addListener(((observableValue, number, t1) -> creerExploxionSprite(tour,"deflagration.gif")));

        //Ajout sprite empoisonnée
        if (tour instanceof Nidoran){
            ((Nidoran) tour).getEnnemiEmpoisone().addListener((ListChangeListener<? super Ennemi>) change -> {
                while (change.next()){
                    if (change.wasAdded())
                        for (Ennemi e : change.getAddedSubList()) {
                            ImageView i = (ImageView)pane.lookup("#hitbox_" + e.getId());
                            if(i != null){ //un ennemi peut etre dans plusieurs liste donc bug potentiel
                                i.setImage(new Image("file:" + Parametres.cheminSpritePokemon + e.getNom() + "_poison.png"));
                            }
                        }
                }
            });
        }

        //Est actif
        if(tour instanceof TourActif t){

            t.getEstPretActif().bind(game.getNbFrame().greaterThan(t.getTempProchainActif()));

            t.getEstPretActif().addListener((observableValue, aBoolean, t1) -> {
                if(t1) sprite.getSprite().setImage(new Image("file:" + Parametres.cheminSpritePokemon + t.getNom() + "_ready.png"));
                else sprite.getSprite().setImage(new Image("file:" + Parametres.cheminSpritePokemon + t.getNom() + ".png"));
            });

            //todo: redondant; le bind tour.levelProperty().isEqualTo(Parametres.niveauEvolutionTour) ne fonctionne pas
            t.levelProperty().addListener((observableValue, number, t1) -> {
                if(t1.intValue() == Parametres.niveauEvolutionTour) sprite.getSprite().setImage(new Image("file:" + Parametres.cheminSpritePokemon + t.getNom() + "_ready.png"));
            });
        }

    }

    private void creerTirSprite(Attaque a) throws IOException {
        TirSprite sprite = new TirSprite(a);
        if (a instanceof Zone) {
            sprite.getHitBox().fitHeightProperty().bind(((Zone) a).rangeProperty().multiply(2));
            sprite.getHitBox().fitWidthProperty().bind(((Zone) a).rangeProperty().multiply(2));
            sprite.getHitBox().visibleProperty().bind(((Zone) a).actifProperty());
            sprite.getHitBox().xProperty().bind(sprite.getHitBox().fitHeightProperty().divide(2).multiply(-1).add(a.xProperty()));
            sprite.getHitBox().yProperty().bind(sprite.getHitBox().fitWidthProperty().divide(2).multiply(-1).add(a.yProperty()));
            sprite.getHitBox().getStyleClass().add("magneti_zone");
        } else {
            sprite.getHitBox().xProperty().bind(a.xProperty());
            sprite.getHitBox().yProperty().bind(a.yProperty());
        }
        a.bougeProperty().addListener((observableValue, aBoolean, nouvelleValeur) -> {
            if (nouvelleValeur) {
                try {
                    sprite.updateImage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        pane.getChildren().add(sprite.getHitBox());
    }

    public void partiePerdue(){
        gameLoop.stop();
        Stage popup = new Stage();
        popup.setTitle("Partie Terminée !");

        Label msg = new Label("Vous Perdez La Partie (loser)");
        Label msg2 = new Label("Continuer ?");
        Button oui = new Button("on continue quand même");
        Button non = new Button("on fuit");
        HBox hbox = new HBox(oui,non);
        VBox vbox= new VBox(msg,msg2,hbox);

        vbox.setAlignment(Pos.CENTER);
        hbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);hbox.setSpacing(20);

        Scene scene =new Scene(vbox,400,300);
        popup.setScene(scene);

        oui.setOnAction(e->{
            popup.close();
            gameLoop.play();
        });

        non.setOnAction(e ->{
            popup.close();
            FXMLLoader fxmlNiveau1 = new FXMLLoader(main.class.getResource("acceuil.fxml"));
            Parent p;
            try {
                p = fxmlNiveau1.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            Scene s = pane.getScene();

            s.setRoot(p);
        });

        popup.initModality(Modality.APPLICATION_MODAL); // empeche de toucher a l'autre fenetre

        popup.show();

        popup.setOnCloseRequest(e->{

        });
    }
    public void partieGagnee(){
        gameLoop.stop();
        Stage popup = new Stage();
        popup.setTitle("Partie Terminée !");

        Label msg = new Label("Vous Gagnez La Partie (tricheur)");
        Label msg2 = new Label("Rejouer ?");
        Button oui = new Button("on rejoue");
        Button non = new Button("on part sur une victoire");
        HBox hbox = new HBox(oui,non);
        VBox vbox= new VBox(msg,msg2,hbox);

        vbox.setAlignment(Pos.CENTER);
        hbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);hbox.setSpacing(20);

        Scene scene =new Scene(vbox,400,300);
        popup.setScene(scene);

        oui.setOnAction(e->{

            popup.close();

            Stage popup2 = new Stage();
            popup2.setTitle("Rejouer ?");

            Label msgbis = new Label("Vous voulez vraiment rejouer ?");
            Label msg2bis = new Label("ne mentez pas on le saura même pas");
            Button ouibis = new Button("on rejoue");
            Button nonbis = new Button("on part sur une victoire");
            HBox hbox2 = new HBox(ouibis,nonbis);
            VBox vbox2 = new VBox(msgbis,msg2bis,hbox2);

            vbox2.setAlignment(Pos.CENTER);
            hbox2.setAlignment(Pos.CENTER);
            vbox2.setSpacing(20);hbox2.setSpacing(20);

            Scene scenebis =new Scene(vbox2,250,300);
            popup2.setScene(scenebis);

            ouibis.setOnAction(e2->{
                popup2.close();
                Platform.exit();
            });

            nonbis.setOnAction(e2 ->{
                popup2.close();
                Platform.exit();
            });

            popup2.initModality(Modality.APPLICATION_MODAL); // empeche de toucher a l'autre fenetre

            popup2.show();

            popup2.setOnCloseRequest(e2->{

            });
        });

        non.setOnAction(e ->{
            popup.close();
            Platform.exit();
        });

        popup.initModality(Modality.APPLICATION_MODAL); // empeche de toucher a l'autre fenetre

        popup.show();

        popup.setOnCloseRequest(e->{

        });
    }
    private void creerExploxionSprite(Objet a, String nameFile) {
        ImageView gifImageView = new ImageView(new Image("file:" + Parametres.cheminTirSprite + nameFile));

        if (a instanceof bouleDeFeu){
            gifImageView.setFitHeight(((bouleDeFeu) a).getRayonExploxion());
            gifImageView.setFitWidth(((bouleDeFeu) a).getRayonExploxion());
        }
        gifImageView.setX(a.getX() - (gifImageView.getImage().getWidth() / 2) );
        gifImageView.setY(a.getY() - (gifImageView.getImage().getHeight() / 2) );
        gifImageView.setMouseTransparent(true);

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(1),
                event -> {
                    pane.getChildren().remove(gifImageView);
                }
        ));

        pane.getChildren().add(gifImageView);
        timeline.play();

    }


  }



