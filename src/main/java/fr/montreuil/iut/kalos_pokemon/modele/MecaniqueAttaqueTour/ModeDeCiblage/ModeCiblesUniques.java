package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage;

import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile.ForgeAttaque;
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
    public void attaque(int degats, ForgeEffectImpact forgeEffet, ForgeAttaque forgeAttaque) {
//        Ennemi ennemi = chercheCible();
//        if(ennemi != null && !listeEnnemisTouches.contains(ennemi)){
//            lanceProjectile(forgeAttaque,forgeEffet,degats,ennemi);
//            listeEnnemisTouches.add(ennemi);
//        }
//         probleme le nidoran attaque bizarement je fix ca
        List<Ennemi> list = chercheCibles();
        int index = 0;
        Ennemi cibleFinal = null;

        while (cibleFinal == null && index <= list.size() - 1){
            if (!listeEnnemisTouches.contains(list.get(index))){
                cibleFinal = list.get(index);
                lanceProjectile(forgeAttaque,forgeEffet,degats,cibleFinal);
                listeEnnemisTouches.add(cibleFinal);
            }
            index++;
        }

    }
}
