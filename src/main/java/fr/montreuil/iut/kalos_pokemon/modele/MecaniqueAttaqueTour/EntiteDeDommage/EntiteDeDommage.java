package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteDeDommage;

import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;


/**
 * super-classe qui regroupe les differents type d'attaque des tours
 * est necessaire pour pouvoir donner un effet speciales a des attaques
 */
public abstract class EntiteDeDommage {
    protected Tour tireur;
    protected ForgeEffetImpact forgeEffetImpact;
    protected double degats;
    protected final Ennemi cible;

    public EntiteDeDommage(Tour tour, ForgeEffetImpact effetImpacts, double degats, Ennemi ennemi) {
        this.tireur = tour;
        this.forgeEffetImpact = effetImpacts;
        this.degats = degats;
        this.cible = ennemi;
    }

    //todo Z : nom var
    public abstract void traitementEntiteDommage();
    public abstract void finEntiteDommage();

    protected double getDegatsFinaux(Ennemi cible) {
        return tireur.getType().calculDegats(cible.getType(), degats);
    }

    protected void toucheCible() {
        double degatsReel = getDegatsFinaux(cible);
        explotionTir(degatsReel);
        //todo z a sup (pas bien)
        finEntiteDommage();
    }

    protected void explotionTir(double degatsReel) {
        affecteSiPossible(cible, degatsReel);
    }

    protected boolean estToucherParExplotion(Ennemi e) {
        return e.getHp() > 0;
    }
    protected void affecteSiPossible(Ennemi e, double degatsReel) {
        if (estToucherParExplotion(e)) {
            e.diminueHP(degatsReel);
            tireur.ajouteDegats(degatsReel);
            e.ajouteEffet(this.forgeEffetImpact.genereEffect());
        }
    }
    public Tour getTireur() {
        return tireur;
    }

}
