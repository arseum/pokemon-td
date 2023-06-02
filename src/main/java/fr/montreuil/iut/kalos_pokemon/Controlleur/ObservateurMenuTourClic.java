package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.Vue.EnnemiSprite;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Objects;

public class ObservateurMenuTourClic implements EventHandler<MouseEvent> {

    private BorderPane scene;

    public StackPane imageTour;

    public String tourSelectionnee;

    public boolean estSelectionnee;

    public ObservateurMenuTourClic(BorderPane scene){
        this.scene = scene;
        setImageTour("poussifeu");
        this.tourSelectionnee = null;
        this.estSelectionnee = false;
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
        this.imageTour.setPickOnBounds(false);
        this.imageTour.setTranslateX(22 -6);
        this.imageTour.setTranslateY(22 -6);

        this.imageTour.setLayoutX(100);
        this.imageTour.setLayoutY(100);
        //this.imageTour.setVisible(true);
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

        //mettreAJourImage("grenousse");

        if(elementCible instanceof ImageView){
            idCible = ((ImageView)elementCible).getId();
            tourSelectionnee = idCible.replace("tourMenuSprite_", "").replace("_normal","").replace("_grise","");
            if(estSelectionnee == true){
                supprimeImage();
                estSelectionnee = false;
            }else {
                //setImageTour(tourSelectionnee);
                mettreAJourImage(tourSelectionnee);
                scene.getChildren().add(this.imageTour);
                estSelectionnee = true;
            }

        }
        //System.out.println(tourSelectionnee);
    }
}
