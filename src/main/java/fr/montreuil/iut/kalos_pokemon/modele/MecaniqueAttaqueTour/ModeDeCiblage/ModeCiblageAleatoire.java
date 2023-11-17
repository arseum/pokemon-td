package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage;

import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile.ForgeEntiteDommage;
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
    public void attaque(int degats, ForgeEffetImpact forgeEffet, ForgeEntiteDommage forgeEntiteDommage) {
        List<Ennemi> listeEnnemis = chercheCibles();

        if(!listeEnnemis.isEmpty()){
            Random random = new Random();
            int index = random.nextInt(chercheCibles().size());
            lanceProjectile(forgeEntiteDommage,forgeEffet,degats,listeEnnemis.get(index));
        }
    }

}
