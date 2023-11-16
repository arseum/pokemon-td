package fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.SpecialClassePourTour.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Dans ce mode, le tir cible un ennemi à portée de manière aléatoire
 */
public class ModeTirAleatoire extends ModeAttaque{
    public ModeTirAleatoire(Tour tourCible) {
        super(tourCible);
    }

    @Override
    public void attaque(int degats, ForgeEffectImpact forgeEffet) {
        List<Ennemi> listeEnnemis = chercheCibles();

        if(!listeEnnemis.isEmpty()){
            Random random = new Random();
            int index = random.nextInt(chercheCibles().size());
            Projectile projectile = new Projectile(this.tourCible, chercheCibles().get(index), this.tourCible.getMyForgeEffectImpact().genereEffect());
            Game.getGame().ajouteProjectile(projectile);
        }
    }

    @Override
    public void attaque() {


    }
}
