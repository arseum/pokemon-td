package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

//todo soit c'est un effet null?
//Qui applique les degats? l'effet? le projectile?
public class Simple extends EffetImpact{
    private Seconde tic;
    public Simple(Tour t, Seconde tic) {
        super(t);
        this.tic = tic;
    }

    @Override
    public void initEffetType() {
        this.typeEffet = TypeEffet.Simple;
    }

    @Override
    public boolean peutEtreAppliquer(int nbFrameValue) {
        //return (frameDebutDeVie - Game.getGame().getNbFrameValue()) % tic.getTempFrameDouble() == 0;
        return true;
    }

    @Override
    public void appliqueEffet() {
        this.victime.diminueHP(50);
        this.tireur.ajouteDegats(10);
    }

    @Override
    public boolean finDeVie() {
        return true;
    }
}
