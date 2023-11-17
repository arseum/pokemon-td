package fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteAttaque.EntiteAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteAttaque.ProjectileSimple;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ForgeProjectileSimple implements ForgeEntiteAttaque {

    public EntiteAttaque genereAttaque(Tour t, ForgeEffetImpact forgeEffet, int degats, Ennemi e) {
        return new ProjectileSimple(t, e, forgeEffet, degats);
    }
}
