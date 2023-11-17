package fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile;

import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteDeDommage.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteDeDommage.ProjectileExplosif;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ForgeBouleDeFeu extends ForgeProjectile{
    private int rayon;

    public ForgeBouleDeFeu(int rayon) {
        this.rayon = rayon;
    }

    @Override
    public Projectile genereAttaque(Tour t, ForgeEffectImpact forgeEffet, int degats, Ennemi e) {
        return new ProjectileExplosif(t,e,forgeEffet,degats,rayon);
    }
}
