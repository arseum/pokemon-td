package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public abstract class AttaqueCible extends Attaque {

    protected final Ennemi cible;
    public AttaqueCible(Tour tour, ForgeEffectImpact effetImpacts,
                        double degats, Ennemi cible) {
        super(tour, effetImpacts, degats);
        this.cible = cible;
    }

    protected void toucheCible(){
        double degatsReel = getDegatsFinale(cible);
        explotionTir(degatsReel);
        Game.getGame().remove(this);
    }
    protected void explotionTir(double degatsReel){
        affecteSiPossible(cible,degatsReel);
    }

    protected boolean estToucherParExplotion(Ennemi e){
        return e.getHp() > 0;
    }

    protected void affecteSiPossible(Ennemi e, double degatsReel){
        if (estToucherParExplotion(e)) {
            e.diminueHP(degatsReel);
            tireur.ajouteDegats(degatsReel);
            e.ajouteEffet(this.forgeEffectImpact.genereEffect());
        }
    }
}
