package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.Vue.EnnemiSprite;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Objects;

public class ObservateurClicSelectionTour implements EventHandler<MouseEvent> {

    private boolean estSelectionne;
    public StackPane imageTour;
    private Pane paneTerrain;

    public ObservateurClicSelectionTour(Pane pane) {
        this.estSelectionne = false;

        Image i, i_bw;
        try {
            i = new Image(Objects.requireNonNull(EnnemiSprite.class.getResource( "testDrag.png")).openStream());
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


        //this.imageTour.setVisible(false);

        this.imageTour.setLayoutX(5 * 32 - 6);
        this.imageTour.setLayoutY(3 * 32 - 6);



        //this.imageTour.setVisible(false);

        this.paneTerrain = pane;
        //paneTerrain.getChildren().add(this.imageTour);

        //pane.getChildren().add(this.imageTour);


    }

    public boolean isEstSelectionne() {
        return estSelectionne;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if(estSelectionne){
            estSelectionne = false;
            System.out.println(this.imageTour.getChildren());
            System.out.println(this.imageTour.lookup("#grise"));
            paneTerrain.getChildren().remove(paneTerrain.lookup("#tourEnCoursAjout"));
        }
        else {
            estSelectionne = true;
            paneTerrain.getChildren().add(this.imageTour);
        }
    }
}
