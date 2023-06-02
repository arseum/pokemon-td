package fr.montreuil.iut.kalos_pokemon.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public abstract class Attaque {

    private static int compteur = 1;
    protected final int nbImageMax;
    protected Tour tireur;
    protected IntegerProperty x;
    protected IntegerProperty y;
    protected IntegerProperty idImage;
    protected Game game;
    private final String id;

    public Attaque(Tour tour, Game game) {
        nbImageMax = tour.getNbImagedefault();
        idImage = new SimpleIntegerProperty(0);
        tireur = tour;
        id = "Tir_n°" + compteur;
        compteur++;
        this.game = game;

        x = new SimpleIntegerProperty(tour.getX());
        y = new SimpleIntegerProperty(tour.getY());
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

    public IntegerProperty idImageProperty() {
        return idImage;
    }

    public abstract void bouge();

}
