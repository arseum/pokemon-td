package fr.montreuil.iut.kalos_pokemon.modele.Tours;

import fr.montreuil.iut.kalos_pokemon.modele.Tour;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class TourActif extends Tour {

    protected IntegerProperty tempProchainActif;
    protected BooleanProperty estPretActif;

    public TourActif(int portee, int degats, String type, int prix, int x, int y, String pokemon, int attaqueSpeed) {
        super(portee, degats, type, prix, x, y, pokemon, attaqueSpeed);
        tempProchainActif = new SimpleIntegerProperty(1000000);
        //grand nombre pour empecher que le boolean est pret passe a true dés le debut du jeu
        estPretActif = new SimpleBooleanProperty(false);
    }

    @Override
    protected void evolution() {
        super.evolution();
        tempProchainActif.set(game.getNbFrameValue());
    }

    public BooleanProperty getEstPretActif() {return estPretActif;}
    public IntegerProperty getTempProchainActif() {return tempProchainActif;}

    public boolean isEstPretActif() {
        return estPretActif.get();
    }

    public abstract void actif();

}
