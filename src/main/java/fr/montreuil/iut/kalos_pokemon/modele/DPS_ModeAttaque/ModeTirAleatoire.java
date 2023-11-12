package fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.ArrayList;
import java.util.Random;

/**
 * Dans ce mode, le tir cible un ennemi à portée de manière aléatoire
 */
public class ModeTirAleatoire extends ModeAttaque{
    public ModeTirAleatoire(EffetImpact effetAttaque, Tour tourCible) {
        super(effetAttaque, tourCible);
    }
    @Override
    public void attaque() {
        ArrayList<Ennemi> listeEnnemis = chercheCibles();
        if(listeEnnemis.size() != 0){
            Random random = new Random();
            int index = random.nextInt(chercheCibles().size());
            Projectile projectile = new Projectile(this.tourCible, chercheCibles().get(index), this.effetAttaque);
            Game.getGame().ajouteProjectile(projectile);
        }
    }
}
