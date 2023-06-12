package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;


public abstract class Attaque {

    private static int compteur = 1;
    private final String id;
    protected Tour tireur;
    protected IntegerProperty x;
    protected IntegerProperty y;
    protected Game game;
    protected BooleanProperty bouge;

    public Attaque(Tour tour, Game game) {
        tireur = tour;
        id = "Tir_n°" + compteur;
        compteur++;
        this.game = game;
        bouge = new SimpleBooleanProperty(false);

        x = new SimpleIntegerProperty(tour.getX() + 22 - Parametres.offsetXTour);
        y = new SimpleIntegerProperty(tour.getY() + 22 - Parametres.offsetYTour);
    }
    public BooleanProperty bougeProperty() {
        return bouge;
    }

    public Tour getTireur() {
        return tireur;
    }

    public String getId() {
        return id;
    }

    public int getX() {
        return x.get();
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public int getY() {
        return y.get();
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public abstract void bouge();

}
