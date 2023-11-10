package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class Poison extends EffetImpact {

    private int degat;
    private Seconde duree;
    private Seconde tic;
    private Tour myTourPoison;

    public Poison(int degat, Seconde duree, Seconde tic, Tour tireur) {
        super(tireur);
        this.degat = degat;
        this.duree = duree;
        this.tic = tic;
        this.myTourPoison = tireur;
    }

    //public TourPoison getMyTourPoison() {return myTourPoison;}

    @Override
    public boolean peutEtreAppliquer(int nbFrameValue) {
        //return (frameDebutDeVie - victime.getGame().getNbFrameValue() ) % tic.getTempFrameDouble() == 0 ;
        return (frameDebutDeVie - Game.getGame().getNbFrameValue()) % tic.getTempFrameDouble() == 0;

    }

    @Override
    public void appliqueEffet() {
        victime.diminueHP(degat);
        tireur.ajouteDegats(degat);
    }

    @Override
    public boolean finDeVie() {
        //return victime != null && victime.getGame().getNbFrameValue() > frameDebutDeVie + duree.getTempFrameDouble();
        return victime != null && Game.getGame().getNbFrameValue() > frameDebutDeVie + duree.getTempFrameDouble();
    }

    //@Override
    //public void fin() {myTourPoison.supprimerEnnemiEmpoissoner(victime);}
}
