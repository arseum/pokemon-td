package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.TypeTour.TourPoison;

public class Poison extends EffetImpact {

    private int degat;
    private Seconde duree;
    private Seconde tic;
    private TourPoison myTourPoison;

    public Poison(int degat, Seconde duree, Seconde tic, TourPoison tireur) {
        super(tireur);
        this.degat = degat;
        this.duree = duree;
        this.tic = tic;
        this.myTourPoison = tireur;
        //todo il faut que le poison ne sois pas stackable
        //il faut gere le fait que lorsque le poison arrive sur un ennemi empoisoner il reset seulement le poison deja existant dessus
    }

    @Override
    public boolean peutEtreAppliquer(int nbFrameValue) {

        return (frameDebutDeVie - victime.getGame().getNbFrameValue() ) % tic.getTempFrameDouble() == 0 ;

    }

    @Override
    public void appliqueEffet() {

        victime.diminueHP(degat);
        tireur.ajouteDegats(degat);

    }

    @Override
    public boolean finDeVie() {
        return victime != null && victime.getGame().getNbFrameValue() > frameDebutDeVie + duree.getTempFrameDouble();
    }

    @Override
    public void fin() {
        myTourPoison.supprimerEnnemiEmpoissoner(victime);
    }
}