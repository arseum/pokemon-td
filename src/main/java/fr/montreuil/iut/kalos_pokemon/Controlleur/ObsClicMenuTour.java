package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class ObsClicMenuTour implements EventHandler<MouseEvent> {

    private BorderPane scene;

    public StackPane imageTour;

    private String imageTourNom;

    public String tourSelectionnee;

    public BooleanProperty estSelectionnee;
    private Game game;

    public ObsClicMenuTour(BorderPane scene, Game game){
        this.scene = scene;
        setImageTour("grenousse");
        this.imageTourNom = "grenousse";
        this.tourSelectionnee = null;
        this.estSelectionnee = new SimpleBooleanProperty(false);
        this.game = game;
    }

    private void setImageTour(String nom) {
        Image i, i_bw;
        i = new Image("file:" + Parametres.cheminIconeTour + nom +".png");
        i_bw = new Image("file:" + Parametres.cheminIconeTour + nom +"_bw.png");

        ImageView im = new ImageView(i);
        im.setId("normal");
        ImageView im_bw = new ImageView(i_bw);
        im_bw.setId("grise");

        this.imageTour = new StackPane(im_bw, im);
        this.imageTour.setId("tourEnCoursAjout");

        //L'image étant plus grande que la tuile il y a un offset pour compenser
        this.imageTour.setTranslateX(- Parametres.offsetXTour );
        this.imageTour.setTranslateY(- Parametres.offsetYTour );

        this.imageTour.setMouseTransparent(true);
    }

    private void mettreAJourImage(String nom){
        ImageView i = (ImageView) this.imageTour.getChildren().get(0);
        ImageView i2 = (ImageView) this.imageTour.getChildren().get(1);
        i.setImage(new Image("file:" + Parametres.cheminIconeTour + nom +".png"));
        i2.setImage(new Image("file:" + Parametres.cheminIconeTour + nom +"_bw.png"));
    }

    public void supprimeImage(){
        scene.getChildren().remove(scene.lookup("#tourEnCoursAjout"));
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        String idCible;
        EventTarget elementCible = mouseEvent.getTarget();

        if(elementCible instanceof ImageView){
            idCible = ((ImageView)elementCible).getId();
            tourSelectionnee = idCible.replace("tourMenuSprite_", "").replace("_normal","").replace("_grise","");
            if(game.tourAchetable(tourSelectionnee)){
                if(estSelectionnee.getValue() == true){
                    if(imageTourNom.equals(tourSelectionnee)){
                        supprimeImage();
                        estSelectionnee.setValue(false);
                    }
                    else {
                        mettreAJourImage(tourSelectionnee);
                        imageTourNom = tourSelectionnee;
                    }
                }else {
                    mettreAJourImage(tourSelectionnee);
                    imageTourNom = tourSelectionnee;
                    scene.getChildren().add(this.imageTour);
                    estSelectionnee.setValue(true);
                }
            }
        }
    }
}