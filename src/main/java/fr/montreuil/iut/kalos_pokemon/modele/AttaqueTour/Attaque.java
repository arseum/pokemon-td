package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Mobile;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * super-classe qui regroupe les differents type d'attaque des tours
 * est necessaire pour pouvoir donner un effet speciales a des attaques
 */
public abstract class Attaque {
    protected Tour tireur;
    protected ForgeEffectImpact forgeEffectImpact;
    protected double degats;
    protected final Ennemi cible;

    public Attaque(Tour tour, ForgeEffectImpact effetImpacts, double degats, Ennemi ennemi) {
        this.tireur = tour;
        this.forgeEffectImpact = effetImpacts;
        this.degats = degats;
        this.cible = ennemi;
    }

    protected double getDegatsFinale(Ennemi cible) {
        return tireur.getType().calculDegats(cible.getType(), degats);
    }

    //Attaque cible
    /*
    public AttaqueCible(Tour tour, ForgeEffectImpact effetImpacts,
                        double degats, Ennemi cible) {
        super(tour, effetImpacts, degats);
        this.cible = cible;
    }*/
    protected void toucheCible() {
        double degatsReel = getDegatsFinale(cible);
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
            e.ajouteEffet(this.forgeEffectImpact.genereEffect());
        }
    }

    public Tour getTireur() {
        return tireur;
    }

    //todo Z : nom var
    public abstract void traitementEntiteDommage();
    public abstract void finEntiteDommage();

}
