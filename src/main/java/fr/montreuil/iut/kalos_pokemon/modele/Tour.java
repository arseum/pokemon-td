package fr.montreuil.iut.kalos_pokemon.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public abstract class Tour {
    private static int compteurID = 1;
    protected IntegerProperty portee;
    protected int degats;
    private final String type;
    private final String nom;
    private final int prix;
    private final IntegerProperty x;
    private final IntegerProperty y;
    protected final IntegerProperty level;
    private final String id;
    protected int attaqueSpeed;
    protected int tempProchaineAttaque;
    protected Game game;

    public Tour(int portee, int degats, String type, int prix, int x, int y, String pokemon, int attaqueSpeed) {
        this.id = "Tour_n°" + compteurID;
        compteurID++;
        this.portee = new SimpleIntegerProperty(portee);
        this.degats = degats;
        this.type = type;
        this.prix = prix;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.level = new SimpleIntegerProperty(1);
        this.nom = pokemon;
        this.attaqueSpeed = attaqueSpeed;
        tempProchaineAttaque = 0;
    }

    public String getNom() {
        return nom;
    }

    public String getId() {
        return id;
    }

    public int getPortee() {
        return portee.get();
    }

    public IntegerProperty porteeProperty() {
        return portee;
    }

    public int getDegats() {
        return degats;
    }

    public int getX() {
        return x.get();
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public int getLevel() {
        return level.get();
    }

    public IntegerProperty levelProperty() {
        return level;
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

    public int getTempProchaineAttaque() {
        return tempProchaineAttaque;
    }

    public abstract void levelUp();
    public abstract void actif();

    protected int distance(Ennemi e) {
        int super_x;
        int super_y;

        super_x = Math.abs(getX() - e.getX());
        super_y = Math.abs(getY() - e.getY());

        return (int) Math.sqrt((super_x * super_x) + (super_y * super_y));
    }

    public void attaque() {

        Ennemi cible = null;
        int index = 0;

        List<Ennemi> listEnnemi = game.getListEnnemi().stream().toList();

        //cherche une cible
        while (cible == null && index < listEnnemi.size()) {

            if (distance(listEnnemi.get(index)) <= portee.get())
                cible = listEnnemi.get(index);
            else
                index++;

        }

        //attaque la cible
        if (cible != null) {
            game.ajouteProjectile(new Projectile(this, cible, game));
            tempProchaineAttaque = game.getNbFrame() + attaqueSpeed;
        }


    }

    public int getPrix() {
        return this.prix;
    }
}
