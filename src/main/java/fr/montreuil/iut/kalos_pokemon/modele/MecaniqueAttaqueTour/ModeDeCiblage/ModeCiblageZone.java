package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage;

import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile.ForgeEntiteDommage;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
public class ModeCiblageZone extends ModeDeCiblage {
    public ModeCiblageZone(Tour tourCible) {
        super(tourCible);
    }

    @Override
    public void attaque(int degats, ForgeEffetImpact forgeEffet, ForgeEntiteDommage forgeEntiteDommage) {
        chercheCibles().forEach(ennemie -> {
            if (tourCible.estADistance(ennemie))
                lanceProjectile(forgeEntiteDommage,forgeEffet,degats,ennemie);
        });
    }
}
