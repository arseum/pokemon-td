package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.modele.Tours.Magneti;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Zone extends Attaque {

    private final BooleanProperty actif;

    public Zone(Magneti tour, Game game) {
        super(tour, game);
        actif = new SimpleBooleanProperty(false);
        actif.bind(tour.actifProperty());
    }

    @Override
    public void bouge() {

        if (idImage.get() + 1 > nbImageMax)
            idImage.set(0);
        else
            idImage.set(idImage.get() + 1);

    }

    public BooleanProperty actifProperty() {
        return actif;
    }

    public boolean isActif() {
        return actif.get();
    }
}
