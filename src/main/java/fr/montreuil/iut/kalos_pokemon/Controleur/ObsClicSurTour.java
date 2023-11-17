package fr.montreuil.iut.kalos_pokemon.Controleur;

import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Permet de gerer les clics sur une tour de la map. Clic gauche sur range + achat/vente
 * Clic droit attaque spéciale si disponible
 */
public class ObsClicSurTour implements EventHandler<MouseEvent> {

    protected SimpleBooleanProperty unetourCarteSelectionnee;
    protected StringProperty idTourSelectionnee;
    protected StringProperty nomTour;
    protected DoubleProperty compteurDegats;
    protected IntegerProperty niveauTour;
    protected Game game;

    public ObsClicSurTour(Game game){
        this.unetourCarteSelectionnee = new SimpleBooleanProperty(false);
        this.idTourSelectionnee = new SimpleStringProperty("placeholder");
        this.nomTour = new SimpleStringProperty("placeholder");
        this.niveauTour = new SimpleIntegerProperty(0);
        this.compteurDegats = new SimpleDoubleProperty(0);
        this.game = game;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        ImageView tourImg = (ImageView) mouseEvent.getTarget();
        String idTour = tourImg.getId();

        if(mouseEvent.getButton() == MouseButton.PRIMARY) {
            tourImg.toFront();
            if (idTour.equals(idTourSelectionnee.get()) && unetourCarteSelectionnee.get()) {
                this.nomTour.set("placeholder");
                this.niveauTour.set(0);
                this.unetourCarteSelectionnee.set(false);
            } else {
                this.idTourSelectionnee.set(idTour);
                this.nomTour.set(game.retourneTourAPartirId(tourImg.getId()).getNom());
                this.niveauTour.set(game.retourneTourAPartirId(tourImg.getId()).getLevel());
                this.unetourCarteSelectionnee.set(true);
            }
        }else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            Tour t = game.retourneTourAPartirId(idTour);
            if (t.actifPret())
                t.actif();
        }
    }
}
