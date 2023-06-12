package fr.montreuil.iut.kalos_pokemon.Controlleur;

import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ObsClicSurTour implements EventHandler<MouseEvent> {

    protected SimpleBooleanProperty tourCarteSelectionnee;
    private String nomTour;
    private Game game;

    public ObsClicSurTour(Game game){
        this.tourCarteSelectionnee = new SimpleBooleanProperty(false);
        this.nomTour = "placeholder";
        this.game = game;
    }

    public String getNomTour(){return this.nomTour;}

    @Override
    public void handle(MouseEvent mouseEvent) {
        //System.out.println(mouseEvent.getTarget());
        ImageView tourImg = (ImageView)mouseEvent.getTarget();

        System.out.println(tourImg.getId());

        Tour nomTourCliqué = game.retourneTourAPartirId(tourImg.getId());
        this.nomTour = nomTourCliqué.getNom();
        this.tourCarteSelectionnee.set(!this.tourCarteSelectionnee.get());

    }
}
