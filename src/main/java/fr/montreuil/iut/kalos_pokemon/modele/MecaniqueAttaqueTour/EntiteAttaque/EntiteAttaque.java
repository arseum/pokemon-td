package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;


/**
 * super-classe qui regroupe les differents type d'attaque des tours
 * est necessaire pour pouvoir donner un effet speciales a des attaques
 */
public abstract class EntiteAttaque {
    protected Tour tireur;
    protected ForgeEffetImpact forgeEffetImpact;
    protected double degats;
    protected final Ennemi cible;

    public EntiteAttaque(Tour tour, ForgeEffetImpact effetImpact, double degatsDeBase, Ennemi ennemi) {
        this.tireur = tour;
        this.forgeEffetImpact = effetImpact;
        this.degats = degatsDeBase;
        this.cible = ennemi;
    }
    public abstract void gestionEntiteAttaque();
    public Tour getTireur() {
        return tireur;
    }
    protected double getDegatsFinaux(Ennemi cible) {
        return tireur.getType().calculDegats(cible.getType(), degats);
    }

    protected void appliqueAttaque(Ennemi ennemi){
        if(ennemi.getHp() > 0){
            double degatsReels = getDegatsFinaux(ennemi);
            ennemi.diminueHP(degatsReels);
            tireur.ajouteDegats(degatsReels);
            ennemi.ajouteEffet(this.forgeEffetImpact.genereEffect());
        }
    }

}