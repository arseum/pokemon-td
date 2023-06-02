package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ObservateurClicSelectionTour implements EventHandler<MouseEvent> {

    //private boolean estSelectionne;
    private BooleanProperty uneTourEstSelectionnee;
    public boolean cetteTourEstSelectionnee;
    public String nom;
    public StackPane imageTour;
    private Pane paneTerrain;
    private  BorderPane scene;

    public ObservateurClicSelectionTour(BorderPane scene, String nom, BooleanProperty uneTourEstSelectionnee) {
        //this.estSelectionne = false;
        this.cetteTourEstSelectionnee = false;
        this.uneTourEstSelectionnee = new SimpleBooleanProperty(false);
        this.nom = nom;
        setImageTour(nom);
        this.scene = scene;

        this.paneTerrain = (Pane) scene.lookup("#pane");
        this.uneTourEstSelectionnee.bindBidirectional(uneTourEstSelectionnee);

    }

    public void setImageTour(String nom) {
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
    }

    public boolean getUneTourEstSelectionnee() {
        return this.uneTourEstSelectionnee.get();
    }

    public void testEstSelect(){
        System.out.println(scene.lookup("#tourEnCoursAjout"));
    }

    public void setEstSelectionne(){
        if(scene.lookup("#tourEnCoursAjout") == null){
            this.uneTourEstSelectionnee.setValue(false);
        } else {
            this.uneTourEstSelectionnee.setValue(false);
        }
    }

    public void supprimeImage(){
        scene.getChildren().remove(scene.lookup("#tourEnCoursAjout"));
        //this.estSelectionne.setValue(false);
        //estSelectionne = false;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        testEstSelect();

        if(this.uneTourEstSelectionnee.getValue()){
            supprimeImage();
            this.uneTourEstSelectionnee.setValue(false);
            this.cetteTourEstSelectionnee = false;
        }
        else {
            supprimeImage();
            //estSelectionne = true;
            this.uneTourEstSelectionnee.setValue(true);
            this.cetteTourEstSelectionnee = true;
            scene.getChildren().add(this.imageTour);
        }
        //todo
        // mouseEvent.getTarget(); Permet d'avoir le node clique
        // ecoute le contener
        mouseEvent.consume();
    }
}
