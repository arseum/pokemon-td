package fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile;

import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteAttaque.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteAttaque.ProjectileExplosif;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ForgeProjectileSimpleExplosif extends ForgeProjectileSimple {
    private int rayon;

    public ForgeProjectileSimpleExplosif(int rayon) {
        this.rayon = rayon;
    }

    @Override
    public Projectile genereAttaque(Tour t, ForgeEffetImpact forgeEffet, int degats, Ennemi e) {
        return new ProjectileExplosif(t,e,forgeEffet,degats,rayon);
    }
}