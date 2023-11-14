package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

/**
 * Un ennemi empoisonné perde des points de vie à une certaine fréquence
 * pendant une certaine durée.
 */
public class EffetPoison extends EffetImpact {
    private int degatPoison;
    private Seconde dureePoison;
    private Seconde frequencePoison;

    public EffetPoison(int degatPoison, Seconde dureePoison, Seconde frequencePoison, Tour tireur) {
        super(tireur);
        this.degatPoison = degatPoison;
        this.dureePoison = dureePoison;
        this.frequencePoison = frequencePoison;
    }

    @Override
    public void initEffetType() {
        this.typeEffet = TypeEffet.Poison;
    }

    @Override
    public boolean peutEtreApplique(Ennemi ennemi) {
        //return (frameDebutDeVie - victime.getGame().getNbFrameValue() ) % tic.getTempFrameDouble() == 0 ;
        return (frameDebutDeVie - Game.getGame().getNbFrameValue()) % frequencePoison.getTempFrameDouble() == 0;

    }

    @Override
    public void appliqueEffet(Ennemi ennemi) {
        ennemi.diminueHP(degatPoison);
        tireur.ajouteDegats(degatPoison);
    }

    @Override
    public boolean finDeVie(Ennemi ennemi) {
        //return victime != null && victime.getGame().getNbFrameValue() > frameDebutDeVie + duree.getTempFrameDouble();
        return ennemi != null && Game.getGame().getNbFrameValue() > frameDebutDeVie + dureePoison.getTempFrameDouble();
    }

    //SETTERS
    public void amelioreEffet(int boostDegats, double boostDuree) {
        this.degatPoison = this.degatPoison + boostDegats;
        this.dureePoison = new Seconde(this.dureePoison.getTemp() + boostDuree);
    }
}
