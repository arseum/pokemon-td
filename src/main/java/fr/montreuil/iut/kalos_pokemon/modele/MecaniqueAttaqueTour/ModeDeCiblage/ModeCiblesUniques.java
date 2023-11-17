package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage;

import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile.ForgeEntiteDommage;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dans ce mode, la tour ne cible qu'une seule fois un ennemi
 */
public class ModeCiblesUniques extends ModeDeCiblage {
    private ArrayList<Ennemi> listeEnnemisTouches;
    public ModeCiblesUniques(Tour tourCible) {
        super(tourCible);
        this.listeEnnemisTouches = new ArrayList<>();
    }

    @Override
    public void attaque(int degats, ForgeEffetImpact forgeEffet, ForgeEntiteDommage forgeEntiteDommage) {
        List<Ennemi> list = chercheCibles();
        int index = 0;
        Ennemi cibleFinal = null;

        while (cibleFinal == null && index <= list.size() - 1){
            if (!listeEnnemisTouches.contains(list.get(index))){
                cibleFinal = list.get(index);
                lanceEntiteAttaque(forgeEntiteDommage,forgeEffet,degats,cibleFinal);
                listeEnnemisTouches.add(cibleFinal);
            }
            index++;
        }

    }
}
