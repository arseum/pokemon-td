package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Attaque;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Magneti;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * classe pour la zone de magneti
 * elle ne fait pas grand chose car la zone ne se deplace pas et ne fait pas de degats
 */
public class Zone extends Attaque {

    private final BooleanProperty actif;
    private final IntegerProperty range;

    public Zone(Magneti tour) {
        //super(tour, game);
        super(tour);
        range = new SimpleIntegerProperty(tour.porteeProperty().get());
        range.bind(tour.porteeProperty());
        actif = new SimpleBooleanProperty(false);
        actif.bind(tour.actifProperty());
    }

    public IntegerProperty rangeProperty() {
        return range;
    }

    public BooleanProperty actifProperty() {
        return actif;
    }

}
