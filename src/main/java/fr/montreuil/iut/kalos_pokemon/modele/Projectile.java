package fr.montreuil.iut.kalos_pokemon.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Projectile {

    private static int compteur = 1;
    private Tour tireur;
    private Ennemi cible;
    private String id;
    private IntegerProperty x;
    private IntegerProperty y;

    public Projectile(Tour tour,Ennemi ennemi){
        tireur = tour;
        cible = ennemi;
        id = "Tir_n°" + compteur;
        compteur++;

        x = new SimpleIntegerProperty(tour.getX());
        y = new SimpleIntegerProperty(tour.getY());
    }

    public Tour getTireur() {
        return tireur;
    }

    public Ennemi getCible() {
        return cible;
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
}
