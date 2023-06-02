package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.Vue.EnnemiSprite;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Objects;

public class ObservateurClicSelectionTour implements EventHandler<MouseEvent> {

    private boolean estSelectionne;
    public StackPane imageTour;
    private Pane paneTerrain;

    private  BorderPane scene;

    public ObservateurClicSelectionTour(BorderPane scene) {
        this.estSelectionne = false;

        Image i, i_bw;
        try {
            //i = new Image(Objects.requireNonNull(EnnemiSprite.class.getResource( "testDrag.png")).openStream());
            i = new Image("file:" + Parametres.cheminIconeTour +"testDrag.png");

            i_bw= new Image(Objects.requireNonNull(EnnemiSprite.class.getResource( "testDrag_bw.png")).openStream());

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        ImageView im = new ImageView(i);
        im.setId("normal");
        ImageView im_bw = new ImageView(i_bw);
        im_bw.setId("grise");
        this.imageTour = new StackPane(im_bw, im);
        this.imageTour.setId("tourEnCoursAjout");

        //Empeche l'image de catch des events
        this.imageTour.setPickOnBounds(false);

        //Permet de décaller l'image
        this.imageTour.setTranslateX(22 -6);
        this.imageTour.setTranslateY(22 -6);


        this.scene = scene;
        this.paneTerrain = (Pane) scene.lookup("#pane");

    }

    public boolean isEstSelectionne() {
        return estSelectionne;
    }

    public void supprimeImage(){
        scene.getChildren().remove(scene.lookup("#tourEnCoursAjout"));
        estSelectionne = false;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        if(estSelectionne){
            supprimeImage();
        }
        else {
            estSelectionne = true;
            scene.getChildren().add(this.imageTour);
        }
        //todo
        // mouseEvent.getTarget(); Permet d'avoir le node clique
        // ecoute le contener
        mouseEvent.consume();
    }
}
