package fr.montreuil.iut.kalos_pokemon.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public abstract class Ennemi {

    private static int compteurID = 1;
    private int vitesse;
    private int hp;
    private String type;
    private IntegerProperty x;
    private IntegerProperty y;
    private int maxHeightHitbox;
    private int maxWidhtHitbox;
    private int[] vecteurAcc;
    private int recompense;
    private String nom;
    private Game game;
    private String id;

    public Ennemi(int vitesse, int hp, String type, int x, int y, int recompense, String pokemon) {
        this.id = "Ennemi_n°" + compteurID;
        compteurID++;
        this.vitesse = vitesse;
        this.hp = hp;
        this.type = type;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.vecteurAcc = new int[]{1, 0};
        this.recompense = recompense;
        this.nom = pokemon;
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

    public int getVitesse() {
        return vitesse;
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

    public void seDeplace() {

        //int caseSuiv = game.getTerrain().getMap_test()[getY() / 32 + vecteurAcc[1]][getX() / 32 + vecteurAcc[0]];
        boolean caseSuiv = game.getTerrain().estChemin(getY() / 32 + vecteurAcc[1], getX() / 32 + vecteurAcc[0]);

        //if (caseSuiv == 0) {
        if (!caseSuiv) {

            int[][] model = new int[][]{{1, 0}, {0, 1}, {0, -1}};
            int n = -1;
            //while (caseSuiv != 51) {
            while (!caseSuiv) {
                n++;
                vecteurAcc[0] = model[n][0];
                vecteurAcc[1] = model[n][1];
                //caseSuiv = game.getTerrain().getMap_test()[getY() / 32 + vecteurAcc[1]][getX() / 32 + vecteurAcc[0]];
                caseSuiv = game.getTerrain().estChemin(getY() / 32 + vecteurAcc[1], getX() / 32 + vecteurAcc[0]);
            }
        }
        this.xProperty().set(this.getX() + this.getVitesse() * vecteurAcc[0]);  // 2 lignes du déplacement
        this.yProperty().set(this.getY() + this.getVitesse() * vecteurAcc[1]);
    }

    public void diminueHP(int value) {
        hp -= value;
    }


}
