package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets;

import fr.montreuil.iut.kalos_pokemon.Donnees.Seconde;
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
        return (frameDebutDeVie - Game.getGame().getNbFrameValue()) % frequencePoison.getTempFrameDouble() == 0;

    }

    @Override
    public void appliqueEffet(Ennemi ennemi) {
        ennemi.diminueHP(degatPoison);
        tireur.ajouteDegats(degatPoison);
    }

    @Override
    public boolean finEffet(Ennemi ennemi) {
        return ennemi != null && Game.getGame().getNbFrameValue() > frameDebutDeVie + dureePoison.getTempFrameDouble();
    }

    @Override
    public int getPuissanceEffet() {
        return this.degatPoison;
    }

    //SETTERS
    public void amelioreEffet(int boostDegats, double boostDuree) {
        this.degatPoison = this.degatPoison + boostDegats;
        this.dureePoison = new Seconde(this.dureePoison.getTemp() + boostDuree);
    }
}
