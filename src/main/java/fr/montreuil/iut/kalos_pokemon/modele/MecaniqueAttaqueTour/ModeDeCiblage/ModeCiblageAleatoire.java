package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage;

import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEntiteAttaque.ForgeEntiteAttaque;
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
    public void attaque(int degats, ForgeEffetImpact forgeEffet, ForgeEntiteAttaque forgeEntiteAttaque) {
        List<Ennemi> listeEnnemis = chercheCibles();

        if (!listeEnnemis.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(chercheCibles().size());
            lanceEntiteAttaque(forgeEntiteAttaque, forgeEffet, degats, listeEnnemis.get(index));
        }
    }

}
