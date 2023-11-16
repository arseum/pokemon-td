package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

/**
 * une attaque dite 'direct' est une attaque ciblé qui touche l'ennemie directement
 * il n'y pas un temp de mouvement contrairement au projectile
 */
public class AttaqueDirect extends AttaqueCible{
    public AttaqueDirect(Tour tour, ForgeEffectImpact effetImpacts,
                         double degats, Ennemi cible) {
        super(tour, effetImpacts, degats, cible);
        toucheCible();
    }

}
