package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public abstract class EffetImpact {
    protected int frameDebutDeVie;
    protected Ennemi victime;
    //Doit avoir cet attribut à cause du compteur de dégats
    protected Tour tireur;
    protected TypeEffet typeEffet;

    public EffetImpact(Tour t) {
        this.tireur = t;
        initEffetType();
    }

    public abstract void initEffetType();

    public TypeEffet getTypeEffet(){return typeEffet;}
    public Ennemi getVictime() {
        return victime;
    }

    public void setFrameDebutDeVie(int frameDebutDeVie) {
        this.frameDebutDeVie = frameDebutDeVie;
    }

    public void setVictime(Ennemi victime) {
        this.victime = victime;
    }

    public void debutVie(Ennemi victime, int frameDebutDeVie) {
        setFrameDebutDeVie(frameDebutDeVie);
        setVictime(victime);
    }

    //public abstract boolean peutEtreAjoute();

    //Dans le cas du poison, c'est pas à toutes les frames
    public abstract boolean peutEtreAppliquer(int nbFrameValue);

    public abstract void appliqueEffet();

    public abstract boolean finDeVie();

    public void fin() {

    }
}
