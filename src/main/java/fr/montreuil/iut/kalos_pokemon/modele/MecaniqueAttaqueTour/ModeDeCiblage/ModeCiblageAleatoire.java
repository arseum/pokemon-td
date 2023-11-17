package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage;

import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile.ForgeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.List;
import java.util.Random;

/**
 * Dans ce mode, le tir cible un ennemi à portée de manière aléatoire
 */
public class ModeCiblageAleatoire extends ModeDeCiblage {
    public ModeCiblageAleatoire(Tour tourCible) {
        super(tourCible);
    }

    @Override
    public void attaque(int degats, ForgeEffectImpact forgeEffet, ForgeAttaque forgeAttaque) {
        List<Ennemi> listeEnnemis = chercheCibles();

        if(!listeEnnemis.isEmpty()){
            Random random = new Random();
            int index = random.nextInt(chercheCibles().size());
            lanceProjectile(forgeAttaque,forgeEffet,degats,listeEnnemis.get(index));
        }
    }

}
