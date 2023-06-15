package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ludicolo;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Map;


public abstract class Ennemi implements Objet{

    private static int compteurID = 1;
    private final int vitesseMax;
    private final double hpMax;
    private final String type;
    protected final IntegerProperty x;
    protected final IntegerProperty y;
    private final int recompense;
    private boolean estStun;
    private int compteurTour;
    private final String nom;
    private final String id;
    private final Map<Integer, Integer> cheminVersArrive;
    private final DoubleProperty hp;
    protected final Game game;
    private boolean estEmpoisonner;
    private int vitesseActuel;
    private int[] infoDeplacement;

    private boolean estTerrestre;

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
        this.estEmpoisonner = false;
        setInfoDeplacement();
    }

    public int getRecompense() {
        return recompense;
    }

    public void setEstEmpoisonner(boolean estEmpoisonner) {
        this.estEmpoisonner = estEmpoisonner;
    }

    public boolean isEstEmpoisonner() {
        return estEmpoisonner;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
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


    public void resetVitesse() {
        vitesseActuel = vitesseMax;
    }

    private void setInfoDeplacement() {
        int[] caseActuelle = new int[]{this.y.get() / Parametres.tailleTuile, this.x.get() / Parametres.tailleTuile};
        int idCaseSuivante = cheminVersArrive.get(this.game.getTerrain().coordonneesXYenCase(caseActuelle[0], caseActuelle[1]));
        int[] caseSuivante = this.game.getTerrain().coordonneesCaseEnXY(idCaseSuivante);
        this.infoDeplacement = new int[]{(caseSuivante[1] - caseActuelle[1]), (caseSuivante[0] - caseActuelle[0]), caseSuivante[1] * Parametres.tailleTuile, caseSuivante[0] * Parametres.tailleTuile};
    }

    public void seDeplace() {

        if (estStun) {
            compteurTour++;
            if (compteurTour == 120)
                estStun = false;
        }
        else
            deplacement();

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

        if (infoDeplacement[2] == game.getTerrain().getCaseArrivee()[0] && infoDeplacement[3] == game.getTerrain().getCaseArrivee()[1] && arriveX && arriveY) {
            meurt();
            game.perdVie(1);
        }
    }

    public void diminueHP(double value) {
        hp.set(hp.get() - value);
        if (hp.get() <= 0) {
            if (this instanceof Ludicolo)
                ((Ludicolo) this).chevalDeTroie();
            game.remove(this);
            game.ajoutePokedollar(recompense);
        }
    }

    private void meurt() {
        hp.set(0);
        game.remove(this);
    }

    public void stun() {
        estStun = true;
        compteurTour = 0;
    }
}
