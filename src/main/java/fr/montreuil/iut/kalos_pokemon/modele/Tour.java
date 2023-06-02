package fr.montreuil.iut.kalos_pokemon.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public abstract class Tour {
    private static int compteurID = 1;
    private final int portee;
    private final int DPS;
    private final String type;
    private final String nom;
    private final int prix;
    private final IntegerProperty x;
    private final IntegerProperty y;
    private Game game;
    private final String id;
    private final StringProperty idCible;
    private final int nbImageAdefault;

    public Tour(int portee, int DPS, String type, int prix, int x, int y, String pokemon, int nbImageAdefault, Game game) {
        this.id = "Tour_n°" + compteurID;
        compteurID++;
        this.portee = portee;
        this.DPS = DPS;
        this.type = type;
        this.prix = prix;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.nom = pokemon;
        idCible = new SimpleStringProperty(null);
        this.nbImageAdefault = nbImageAdefault;
    }

    public int getNbImageAdefault() {
        return nbImageAdefault;
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

    public StringProperty idCibleProperty() {
        return idCible;
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
        idCible.setValue(null);

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
            idCible.setValue(cible.getId());
            cible.diminueHP(this.DPS);
        }


    }
}
