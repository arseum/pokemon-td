package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Boss;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ludicolo;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Map;


public abstract class Ennemi implements Objet,Mobile{

    private static int compteurID = 1;
    private int vitesseMax;
    private final double hpMax;
    private final String type;
    protected final IntegerProperty x;
    protected final IntegerProperty y;
    private final int recompense;
    private boolean estStun;
    /**
     * compteur de frame utile pour compter le nombre de frames que le pokemon ne peut pas bouger
     */
    private int compteurTour;
    private final String nom;
    private final String id;
    private final Map<Integer, Integer> cheminVersArrive;
    private final DoubleProperty hp;
    protected final Game game;
    private int vitesseActuel;
    private int[] infoDeplacement;
    private boolean estTerrestre;
    private int dureeStun;
    protected boolean estArrive;

    public Ennemi(int vitesseMax, int hp, String type, int x, int y, int recompense, String pokemon, Game game, boolean estTerrestre) {
        this.id = "Ennemi_n°" + compteurID;
        compteurID++;
        this.hp = new SimpleDoubleProperty(hp);
        this.hpMax = hp;
        this.vitesseMax = vitesseMax;
        this.vitesseActuel = vitesseMax;
        this.type = type;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.recompense = recompense;
        this.nom = pokemon;
        this.game = game;
        this.estTerrestre = estTerrestre;
        this.cheminVersArrive = this.game.getTerrain().algoBFS(estTerrestre);
        this.estStun = false;
        this.estArrive = false;
        setInfoDeplacement();
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public boolean isEstArrive() {
        return estArrive;
    }

    public void reduitVitese(int v) {
        vitesseActuel = vitesseMax - v;
        if (vitesseActuel <= 0)
            vitesseActuel = 1;
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
    public void reduitVitesseMax(int value){
        //il faut empecher l'accumulation de slow qui pourront mettre la vitesse a 0
        vitesseMax = vitesseMax - value > 0 ? vitesseMax - value : 1;
        vitesseActuel = vitesseMax;
    }

    public void resetVitesse() {
        vitesseActuel = vitesseMax;
    }

    private void setInfoDeplacement() {
        int[] caseActuelle = new int[]{this.y.get() / Parametres.tailleTuile, this.x.get() / Parametres.tailleTuile};
        int idCaseSuivante = cheminVersArrive.get(this.game.getTerrain().coordonneesXYenCase(caseActuelle[0], caseActuelle[1]));
        int[] caseSuivante = this.game.getTerrain().coordonneesCaseEnXY(idCaseSuivante);
        this.infoDeplacement = new int[]{(caseSuivante[1] - caseActuelle[1]), (caseSuivante[0] - caseActuelle[0]), caseSuivante[1] * Parametres.tailleTuile, caseSuivante[0] * Parametres.tailleTuile};
    }

    public void bouge() {

        if (estStun)
            attendre();
        else
            deplacement();

    }

    /**
     * methode a executer lorsque l'ennemi est immobile
     */
    private void attendre() {
        compteurTour++;
        if (compteurTour == dureeStun)
            estStun = false;
    }

    private void deplacement(){
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

        if (infoDeplacement[2]/32 == game.getTerrain().getCaseArrivee()[0]/32 && infoDeplacement[3]/32 == game.getTerrain().getCaseArrivee()[1]/32){
            game.remove(this);
            game.perdVie(1);
            estArrive = true;
        }
    }

    public void diminueHP(double value) {
        hp.set(hp.get() - value);
        if (hp.get() <= 0) {
            meurt();
            game.ajoutePokedollar(recompense);
        }
    }

    protected void meurt() {
        game.remove(this);
    }

    public void stun(int dureeStun) {
        estStun = true;
        compteurTour = 0;
        this.dureeStun = dureeStun;
    }
}
