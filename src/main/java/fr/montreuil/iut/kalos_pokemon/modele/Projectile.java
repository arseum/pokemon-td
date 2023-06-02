package fr.montreuil.iut.kalos_pokemon.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

public class Projectile {

    private static int compteur = 1;
    private Tour tireur;
    private Ennemi cible;
    private String id;
    private IntegerProperty x;
    private IntegerProperty y;
    private final int nbImageMax;
    private IntegerProperty idImage;
    private Game game;

    public Projectile(Tour tour,Ennemi ennemi,Game game){
        nbImageMax = tour.getNbImagedefault();
        idImage = new SimpleIntegerProperty(0);
        tireur = tour;
        cible = ennemi;
        id = "Tir_n°" + compteur;
        compteur++;
        this.game = game;

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

    public IntegerProperty idImageProperty() {
        return idImage;
    }

    public void bouge(){
        if (getX() > cible.getX() + 15 || getY() > cible.getY() + 15 || getX() < cible.getX() - 15 || getY() < cible.getY() - 15) {

            for (int i = 0; i < 6; i++) {
                y.set(getY() < cible.getY() ? getY() + 1 : getY() - 1);
                x.set(getX() < cible.getX() ? getX() + 1 : getX() - 1);

                if (idImage.get() + 1 > nbImageMax)
                    idImage.set(0);
                else
                    idImage.set(idImage.get() + 1);

            }


        } else {
            if (cible.getHp() > 0)
                cible.diminueHP(tireur.getDPS());
            game.remove(this);
        }
    }

}
