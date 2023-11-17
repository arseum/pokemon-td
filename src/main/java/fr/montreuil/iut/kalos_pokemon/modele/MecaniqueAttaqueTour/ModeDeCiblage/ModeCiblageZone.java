package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage;

import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeEntiteAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
public class ModeCiblageZone extends ModeDeCiblage {
    public ModeCiblageZone(Tour tourCible) {
        super(tourCible);
    }

    @Override
    public void attaque(int degats, ForgeEffetImpact forgeEffet, ForgeEntiteAttaque forgeEntiteAttaque) {
        chercheCibles().forEach(ennemie -> {
            if (tour.estADistance(ennemie))
                lanceEntiteAttaque(forgeEntiteAttaque,forgeEffet,degats,ennemie);
        });
    }
}
