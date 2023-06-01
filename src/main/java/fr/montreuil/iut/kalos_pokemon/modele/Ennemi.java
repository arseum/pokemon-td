package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Map;


public abstract class Ennemi {

    private static int compteurID = 1;
    private final int vitesseMax;
    private int vitesseActuel;
    private int hp;
    private final String type;
    private final IntegerProperty x;
    private final IntegerProperty y;
    private int maxHeightHitbox;
    private int maxWidhtHitbox;
    private final int recompense;
    private final String nom;
    private Game game;
    private final String id;
    private final Map<Integer, Integer> cheminVersArrive;

    private int[] infoDeplacement;

    public Ennemi(int vitesseMax, int hp, String type, int x, int y, int recompense, String pokemon, Game game) {
        this.id = "Ennemi_n°" + compteurID;
        compteurID++;
        this.vitesseMax = vitesseMax;
        this.vitesseActuel = vitesseMax;
        this.hp = hp;
        this.type = type;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.recompense = recompense;
        this.nom = pokemon;
        this.game = game;
        this.cheminVersArrive = this.game.getTerrain().algoBFS();
        setInfoDeplacement();
    }

    public int getRecompense() {
        return recompense;
    }

    public int getMaxHeightHitbox() {
        return maxHeightHitbox;
    }

    public void setMaxHeightHitbox(int maxHeightHitbox) {
        this.maxHeightHitbox = maxHeightHitbox;
    }

    public int getMaxWidhtHitbox() {
        return maxWidhtHitbox;
    }

    public void setMaxWidhtHitbox(int maxWidhtHitbox) {
        this.maxWidhtHitbox = maxWidhtHitbox;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void reduitVitese(int v) {
        vitesseActuel -= v;
    }

    public int getHp() {
        return hp;
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

    public void resetVitesse(){ vitesseActuel = vitesseMax;}

    private void setInfoDeplacement() {
        int[] caseActuelle = new int[]{this.y.get() / Parametres.tailleTuile, this.x.get() / Parametres.tailleTuile};
        int idCaseSuivante = cheminVersArrive.get(this.game.getTerrain().coordonneesXYenCase(caseActuelle[0], caseActuelle[1]));
        int[] caseSuivante = this.game.getTerrain().coordonneesCaseEnXY(idCaseSuivante);
        this.infoDeplacement = new int[]{(caseSuivante[1] - caseActuelle[1]), (caseSuivante[0] - caseActuelle[0]), caseSuivante[1] * Parametres.tailleTuile, caseSuivante[0] * Parametres.tailleTuile};
    }

    public void seDeplace() {
        int nouveauX, nouveauY;

        nouveauX = this.getX() + vitesseActuel * infoDeplacement[0];
        nouveauY = this.getY() + vitesseActuel * infoDeplacement[1];


        boolean arriveX = (infoDeplacement[2] <= nouveauX) && (nouveauX <= (infoDeplacement[2] + this.vitesseMax - 1));
        boolean arriveY = (infoDeplacement[3] <= nouveauY) && (nouveauY <= (infoDeplacement[3] + this.vitesseMax - 1));

        this.xProperty().set(nouveauX);
        this.yProperty().set(nouveauY);

        if (arriveX && arriveY) {
            this.xProperty().set(infoDeplacement[2]);
            this.yProperty().set(infoDeplacement[3]);
            setInfoDeplacement();
        }
    }

    public void diminueHP(int value) {
        hp -= value;
    }

}
