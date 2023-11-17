package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage;

import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeEntiteAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.Effets.TypeEffet;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class ModeCiblageEnnemisEmpoisonnes extends ModeDeCiblage{
    public ModeCiblageEnnemisEmpoisonnes(Tour tourCible) {
        super(tourCible);
    }
    @Override
    public void attaque(int degats, ForgeEffetImpact forgeEffet, ForgeEntiteAttaque forgeEntiteAttaque) {
        Game.getGame().getListEnnemi().forEach(e -> {
            if (e.getListeObsDesDifferentsTypeEffets().containsKey(TypeEffet.Poison) && e.getListeObsDesDifferentsTypeEffets().get(TypeEffet.Poison).getTireur() == this.tour)
                lanceEntiteAttaque(forgeEntiteAttaque,forgeEffet,degats,e);
        });
    }
}
