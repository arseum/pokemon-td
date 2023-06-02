package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Map;


public abstract class Ennemi {

    private static int compteurID = 1;
    private final int vitesse;
    private DoubleProperty hp;
    private final double hpMax;
    private final String type;
    private final IntegerProperty x;
    private final IntegerProperty y;
    private final int recompense;
    private final String nom;
    private Game game;
    private final String id;
    private final Map<Integer, Integer> cheminVersArrive;

    private int[] infoDeplacement;

    public Ennemi(int vitesse, int hp, String type, int x, int y, int recompense, String pokemon, Game game) {
        this.id = "Ennemi_n°" + compteurID;
        compteurID++;
        this.vitesse = vitesse;
        this.hp = new SimpleDoubleProperty(hp);
        this.hpMax = hp;
        this.type = type;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.recompense = recompense;
        this.nom = pokemon;

        this.game = game;
        this.cheminVersArrive = this.game.getTerrain().algoBFS();
        setInfoDeplacement();
    }

    private void setInfoDeplacement() {
        int[] caseActuelle = new int[]{this.y.get() / Parametres.tailleTuile, this.x.get() / Parametres.tailleTuile};
        int idCaseSuivante = cheminVersArrive.get(this.game.getTerrain().coordonneesXYenCase(caseActuelle[0], caseActuelle[1]));
        int[] caseSuivante = this.game.getTerrain().coordonneesCaseEnXY(idCaseSuivante);
        this.infoDeplacement = new int[]{(caseSuivante[1] - caseActuelle[1]), (caseSuivante[0] - caseActuelle[0]), caseSuivante[1] * Parametres.tailleTuile, caseSuivante[0] * Parametres.tailleTuile};
    }

    public int getRecompense() {
        return recompense;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getVitesse() {
        return vitesse;
    }

    public double getHp() {
        return hp.get();
    }

    public DoubleProperty hpProperty() {
        return hp;
    }

    public double getHpMax() {
        return hpMax;
    }

    public String getType() {
        return type;
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


    public void seDeplace() {
        int nouveauX, nouveauY;

        nouveauX = this.getX() + this.getVitesse() * infoDeplacement[0];
        nouveauY = this.getY() + this.getVitesse() * infoDeplacement[1];


        boolean arriveX = (infoDeplacement[2] <= nouveauX) && (nouveauX <= (infoDeplacement[2] + this.vitesse - 1));
        boolean arriveY = (infoDeplacement[3] <= nouveauY) && (nouveauY <= (infoDeplacement[3] + this.vitesse - 1));

        this.xProperty().set(nouveauX);
        this.yProperty().set(nouveauY);

        if (arriveX && arriveY) {
            this.xProperty().set(infoDeplacement[2]);
            this.yProperty().set(infoDeplacement[3]);
            setInfoDeplacement();
        }
    }

    public void diminueHP(int value) {
        hp.set(hp.get() - value);
    }

}
