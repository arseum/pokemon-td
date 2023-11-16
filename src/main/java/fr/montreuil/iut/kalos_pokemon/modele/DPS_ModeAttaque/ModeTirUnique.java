package fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.ArrayList;

/**
 * Dans ce mode, la tour ne cible qu'une seule fois un ennemi
 */
public class ModeTirUnique extends ModeAttaque {
    private ArrayList<Ennemi> listeEnnemisTouches;
    public ModeTirUnique(Tour tourCible) {
        super(tourCible);
        this.listeEnnemisTouches = new ArrayList<>();
    }

    @Override
    public void attaque() {
        Ennemi ennemi = chercheCible();
        if(ennemi != null && !listeEnnemisTouches.contains(ennemi)){
            Projectile projectile = new Projectile(
                    this.tourCible,
                    ennemi,
                    this.tourCible.getMyForgeEffectImpact().genereEffect());
            Game.getGame().ajouteProjectile(projectile);
            listeEnnemisTouches.add(ennemi);
        }
    }
}
