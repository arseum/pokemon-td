package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.modele.Game;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ObsClicSurTour implements EventHandler<MouseEvent> {

    protected SimpleBooleanProperty unetourCarteSelectionnee;
    protected StringProperty idTourSelectionnee;
    protected StringProperty nomTour;
    private Game game;
    private Pane pane;

    public ObsClicSurTour(Game game, Pane pane){
        this.unetourCarteSelectionnee = new SimpleBooleanProperty(false);
        this.idTourSelectionnee = new SimpleStringProperty("placeholder");
        this.nomTour = new SimpleStringProperty("placeholder");
        this.game = game;
        this.pane = pane;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        ImageView tourImg = (ImageView)mouseEvent.getTarget();
        String idTour = tourImg.getId();

        tourImg.toFront();
        if(idTour.equals(idTourSelectionnee.get()) && unetourCarteSelectionnee.get()){
            this.nomTour.set("placeholder");
            this.unetourCarteSelectionnee.set(false);
        }
        else {
            this.idTourSelectionnee.set(idTour);
            this.nomTour.set(game.retourneTourAPartirId(tourImg.getId()).getNom());
            this.unetourCarteSelectionnee.set(true);
        }

        /*
        if(idTour.equals(idTourSelectionnee.get())){
            if(unetourCarteSelectionnee.get()){
                this.nomTour.set("placeholder");
                this.unetourCarteSelectionnee.set(false);
            }
            else {
                this.idTourSelectionnee.set(idTour);
                this.nomTour.set(game.retourneTourAPartirId(tourImg.getId()).getNom());
                this.unetourCarteSelectionnee.set(true);
            }
        }
        else {
            this.idTourSelectionnee.set(idTour);
            this.nomTour.set(game.retourneTourAPartirId(tourImg.getId()).getNom());
            this.unetourCarteSelectionnee.set(true);
        }

         */

    }
}
