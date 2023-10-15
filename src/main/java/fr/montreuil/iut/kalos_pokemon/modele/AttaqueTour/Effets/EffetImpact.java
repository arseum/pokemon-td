package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public abstract class EffetImpact {

    protected int frameDebutDeVie;
    protected Ennemi victime;
    protected Tour tireur;

    public EffetImpact(Tour t) {
        this.tireur = t;
    }

    public Ennemi getVictime() {
        return victime;
    }

    public Tour getTireur() {
        return tireur;
    }

    public void setFrameDebutDeVie(int frameDebutDeVie) {
        this.frameDebutDeVie = frameDebutDeVie;
    }
    public void setVictime(Ennemi victime) {
        this.victime = victime;
    }

    public void debutVie(Ennemi victime, int frameDebutDeVie){
        setFrameDebutDeVie(frameDebutDeVie);
        setVictime(victime);
    }

    public abstract boolean peutEtreAppliquer(int nbFrameValue);

    public void appliqueEffet() {

    }

    public abstract boolean finDeVie() ;

    public void fin() {

    }
}
