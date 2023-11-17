package fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile;

import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteAttaque.EntiteAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteAttaque.AttaqueInstantane;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ForgeEntiteDommageInstantane implements ForgeEntiteDommage {

    @Override
    public EntiteAttaque genereAttaque(Tour t, ForgeEffetImpact forgeEffet,
                                       int degats, Ennemi e) {
        return new AttaqueInstantane(t,forgeEffet,degats,e);
    }
}
