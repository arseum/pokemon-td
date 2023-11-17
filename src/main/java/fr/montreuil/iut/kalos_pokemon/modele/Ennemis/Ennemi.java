package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.Donne.Type;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets.TypeEffet;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Map.BFS;
import fr.montreuil.iut.kalos_pokemon.modele.Mobile;
import fr.montreuil.iut.kalos_pokemon.modele.Pokemon;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class Ennemi extends Pokemon implements Mobile {

    private static int compteurID = 1;
    private int vitesseMax;
    private final double hpMax;
    private final int recompense;
    private boolean estStun;
    /**
     * compteur de frame utile pour compter le nombre de frames que le pokemon ne peut pas bouger
     */
    private int compteurTour;

    private final Map<Integer, Integer> cheminVersArrive;
    private final DoubleProperty hp;
    //protected final Game game;
    private int vitesseActuel;
    private int[] infoDeplacement;
    private boolean estTerrestre;
    private int dureeStun;
    protected boolean estArrive;
    //HashMap pour empecher le stack d'effet du même type
    protected ObservableMap<TypeEffet, EffetImpact> listeObsDesDifferentsTypeEffets;

    public Ennemi(int vitesseMax, int hp, Type type, int x, int y, int recompense, String pokemon, boolean estTerrestre) {
        super(pokemon,type,x,y);
        this.id = "Ennemi_n°" + compteurID;
        compteurID++;

        this.hp = new SimpleDoubleProperty(hp);
        this.hpMax = hp;
        this.vitesseMax = vitesseMax;
        this.vitesseActuel = vitesseMax;
        this.recompense = recompense;
        this.estTerrestre = estTerrestre;
        //this.cheminVersArrive = this.game.getTerrain().algoBFS(estTerrestre);
        this.cheminVersArrive = BFS.getBFS(Game.getGame().getTerrain()).algoBFS(estTerrestre);
        this.estStun = false;
        this.estArrive = false;
        this.listeObsDesDifferentsTypeEffets = FXCollections.observableHashMap();
        //this.effetImpactObservableList = FXCollections.observableArrayList();
        setInfoDeplacement();
    }
    public ObservableMap<TypeEffet, EffetImpact> getListeObsDesDifferentsTypeEffets(){
        return this.listeObsDesDifferentsTypeEffets;
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

    public DoubleProperty hpProperty() {return hp;}

    public double getHpMax() {
        return hpMax;
    }


    public void removeEffet(EffetImpact e) {
        listeObsDesDifferentsTypeEffets.remove(e.getTypeEffet());
    }

    public EffetImpact getEffectSelonType(TypeEffet typeEffet){
        return listeObsDesDifferentsTypeEffets.get(typeEffet);
    }



    public void gereEffet() {
        EffetImpact effetImpact;

        Set<Map.Entry<TypeEffet, EffetImpact>> set = listeObsDesDifferentsTypeEffets.entrySet();
        Iterator<Map.Entry<TypeEffet, EffetImpact>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<TypeEffet, EffetImpact> entry = iterator.next();
            effetImpact = entry.getValue();
            if (effetImpact.peutEtreApplique(this)){
                effetImpact.appliqueEffet(this);
            }
            if (effetImpact.finEffet(this)) {
                iterator.remove();
            }
        }

    }

    public void ajouteEffet(EffetImpact effetImpact) {
        TypeEffet typeEffet = effetImpact.getTypeEffet();
        listeObsDesDifferentsTypeEffets.put(typeEffet,effetImpact);
    }


    public void setVitesseActuel(int v){
        this.vitesseActuel = v;
    }

    public boolean estAffecterParEffet(TypeEffet typeEffet){
        return listeObsDesDifferentsTypeEffets.containsKey(typeEffet);
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
        //int idCaseSuivante = cheminVersArrive.get(this.game.getTerrain().coordonneesXYenCase(caseActuelle[0], caseActuelle[1]));
        int idCaseSuivante = cheminVersArrive.get(Game.getGame().getTerrain().coordonneesXYenCase(caseActuelle[0], caseActuelle[1]));
        //int[] caseSuivante = this.game.getTerrain().coordonneesCaseEnXY(idCaseSuivante);
        int[] caseSuivante = Game.getGame().getTerrain().coordonneesCaseEnXY(idCaseSuivante);
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

        //if (infoDeplacement[2]/32 == game.getTerrain().getCaseArrivee()[0]/32 && infoDeplacement[3]/32 == game.getTerrain().getCaseArrivee()[1]/32){
        if (infoDeplacement[2]/32 == Game.getGame().getTerrain().getCaseArrivee()[0]/32 && infoDeplacement[3]/32 == Game.getGame().getTerrain().getCaseArrivee()[1]/32){
            //game.remove(this);
            //game.perdVie(1);
            Game.getGame().remove(this);
            Game.getGame().perdVie(1);
            estArrive = true;
        }
    }

    public void diminueHP(double value) {
        hp.set(hp.get() - value);
        if (hp.get() <= 0) {
            meurt();
            //this.game.ajoutePokedollar(recompense);
            Game.getGame().ajoutePokedollar(recompense);
        }
    }

    protected void meurt() {
        Game.getGame().remove(this);
    }

    public void stun(int dureeStun) {
        estStun = true;
        compteurTour = 0;
        this.dureeStun = dureeStun;
    }

    public int getVitesseMax() {
        return vitesseMax;
    }

    public int getVitesseActuel() {
        return vitesseActuel;
    }
}
