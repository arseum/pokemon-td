package fr.montreuil.iut.kalos_pokemon.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public abstract class Tour {
    private static int compteurID = 1;
    private int portee;
    private int DPS;
    private String type;
    private String nom;
    private int prix;
    private IntegerProperty x;
    private IntegerProperty y;
    private Game game;
    private String id;

    public Tour(int portee, int DPS, String type, int prix, int x, int y, String pokemon, Game game) {
        this.id = "Tour_n°" + compteurID;
        compteurID++;
        this.portee = portee;
        this.DPS = DPS;
        this.type = type;
        this.prix = prix;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.nom = pokemon;
        this.game = game;
    }

    public String getNom() {
        return nom;
    }

    public String getId() {
        return id;
    }

    public int getPortee() {
        return portee;
    }

    public int getDPS() {
        return DPS;
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

    public void setGame(Game game) {
        this.game = game;
    }

    public void attaque() {

        Ennemi cible = null;
        int index = 0;
        int super_x;
        int super_y;
        int distance;
        List<Ennemi> listEnnemi = game.getListEnnemi().stream().toList();

        //debug
        if (listEnnemi.size() != 0) {
            super_x = Math.abs(getX() - listEnnemi.get(0).getX());
            super_y = Math.abs(getY() - listEnnemi.get(0).getY());
            distance = (int) Math.sqrt((super_x * super_x) + (super_y * super_y));
            System.out.println("DEBUG ! distance formule entre poussifeu et le premier ennemie = " + distance);
        }

        //cherche une cible
        while (cible == null && index < listEnnemi.size()) {

            super_x = Math.abs(getX() - listEnnemi.get(index).getX());
            super_y = Math.abs(getY() - listEnnemi.get(index).getY());
            distance = (int) Math.sqrt((super_x * super_x) + (super_y * super_y));

            if (distance <= portee)
                cible = listEnnemi.get(index);
            else
                index++;
        }

        //attaque la cible
        if (cible != null) {
            cible.diminueHP(this.DPS);
            System.out.println(cible.getId() + " vient de subir " + this.DPS + " degats !!!");
        }


    }
}
