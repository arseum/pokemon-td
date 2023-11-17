package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage;

import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile.ForgeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
public class ModeCiblageZone extends ModeDeCiblage {
    public ModeCiblageZone(Tour tourCible) {
        super(tourCible);
    }

    @Override
    public void attaque(int degats, ForgeEffectImpact forgeEffet, ForgeAttaque forgeAttaque) {
        chercheCibles().forEach(ennemie -> {
            if (tourCible.estADistance(ennemie))
                lanceProjectile(forgeAttaque,forgeEffet,degats,ennemie);
        });
    }
}
