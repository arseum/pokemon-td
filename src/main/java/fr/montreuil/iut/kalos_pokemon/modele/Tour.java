package fr.montreuil.iut.kalos_pokemon.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public abstract class Tour {
    private int portee;
    private int DPS;
    private String type;
    private String nom;
    private int prix;
    private IntegerProperty x;
    private IntegerProperty y;
    private Game game;
    private static int compteurID = 1;
    private String id;

    public Tour(int portee, int DPS, String type, int prix, int x, int y, String pokemon) {
        this.id = "Tour_n°" + compteurID;
        compteurID++;
        this.portee = portee;
        this.DPS = DPS;
        this.type = type;
        this.prix = prix;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.nom = pokemon;
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

        Ennemi cible;
        int index = 0;
        List<Ennemi> listEnnemi = game.getListEnnemi().stream().toList();

        while (cible == null && index < listEnnemi.size()){
            //todo coder la boucle qui choisi l'ennemi en range et le plus proche
        }

        //todo attaquer la cible si elle n'est pas null

    }
}
