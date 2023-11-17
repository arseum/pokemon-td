package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage;

import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile.ForgeEntiteDommage;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets.TypeEffet;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ModeCiblageEnnemisEmpoisonnes extends ModeDeCiblage{
    public ModeCiblageEnnemisEmpoisonnes(Tour tourCible) {
        super(tourCible);
    }
    @Override
    public void attaque(int degats, ForgeEffetImpact forgeEffet, ForgeEntiteDommage forgeEntiteDommage) {
        Game.getGame().getListEnnemi().forEach(e -> {
            if (e.getListeObsDesDifferentsTypeEffets().containsKey(TypeEffet.Poison))
                lanceEntiteAttaque(forgeEntiteDommage,forgeEffet,degats,e);
        });
    }
}
