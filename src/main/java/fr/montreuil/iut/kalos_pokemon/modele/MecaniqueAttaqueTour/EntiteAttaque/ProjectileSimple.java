package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ProjectileSimple extends Projectile {
    public ProjectileSimple(Tour tour, Ennemi ennemi, ForgeEffetImpact forgeEffetImpact, double degats) {
        super(tour, ennemi, forgeEffetImpact, degats);
    }

    @Override
    public void gestionTir() {
        appliqueAttaque(this.cible);
        finTir();
    }
}
