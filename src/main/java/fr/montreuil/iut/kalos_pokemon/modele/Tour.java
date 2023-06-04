package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public abstract class Tour {
    private static int compteurID = 1;
    protected final int portee;
    private final int degats;
    protected int attaqueSpeed;
    protected int tempProchaineAttaque;
    private final String type;
    private final String nom;
    private final int prix;
    private final IntegerProperty x;
    private final IntegerProperty y;
    private final String id;
    private final int nbImagedefault;
    protected Game game;

    public Tour(int portee, int DPS, String type, int prix, int x, int y, String pokemon, int nbImageAdefault, int attaqueSpeed) {
        this.id = "Tour_n°" + compteurID;
        compteurID++;
        this.portee = portee;
        this.degats = DPS;
        this.type = type;
        this.prix = prix;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.nom = pokemon;
        this.nbImagedefault = nbImageAdefault;
        this.attaqueSpeed = attaqueSpeed;
        tempProchaineAttaque = 0;
    }

    public int getNbImagedefault() {
        return nbImagedefault;
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

    public int getDegats() {
        return degats;
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

    public int getTempProchaineAttaque() {
        return tempProchaineAttaque;
    }

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

            if (distance(listEnnemi.get(index)) <= portee)
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

    public int getPrix(){return this.prix;}
}
