package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

/**
 * une attaque dite 'direct' est une attaque ciblé qui touche l'ennemie directement
 * il n'y pas un temp de mouvement contrairement au projectile
 */
public class AttaqueInstantanee extends EntiteAttaque {
    public AttaqueInstantanee(Tour tour, ForgeEffetImpact effetImpacts, double degats, Ennemi cible) {
        super(tour, effetImpacts, degats, cible);
    }

    @Override
    public void gestionEntiteAttaque() {
        appliqueAttaque(this.cible);
    }

}
