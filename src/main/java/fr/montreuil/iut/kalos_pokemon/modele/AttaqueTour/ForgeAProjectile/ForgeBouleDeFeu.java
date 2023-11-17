package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAProjectile;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Attaque;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.bouleDeFeu;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ForgeBouleDeFeu extends ForgeProjectile{
    private int rayon;

    public ForgeBouleDeFeu(int rayon) {
        this.rayon = rayon;
    }

    @Override
    public Projectile genereAttaque(Tour t, ForgeEffectImpact forgeEffet, int degats, Ennemi e) {
        return new bouleDeFeu(t,e,forgeEffet,degats,rayon);
    }
}
