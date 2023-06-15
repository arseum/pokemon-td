package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.modele.Tours.Magneti;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Zone extends Attaque {

    private final BooleanProperty actif;
    private final IntegerProperty range;

    public Zone(Magneti tour, Game game) {
        super(tour, game);
        range = new SimpleIntegerProperty(tour.porteeProperty().get());
        range.bind(tour.porteeProperty());
        actif = new SimpleBooleanProperty(false);
        actif.bind(tour.actifProperty());
    }

    public int getRange() {
        return range.get();
    }

    public IntegerProperty rangeProperty() {
        return range;
    }

    @Override
    public void bouge() {
        //permet de faire bouger la vue
        bouge.set(true);
        bouge.set(false);
    }

    public BooleanProperty actifProperty() {
        return actif;
    }

    public boolean isActif() {
        return actif.get();
    }
}
