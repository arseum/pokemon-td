package fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.ArrayList;

public class ModeTirUnique extends ModeTireur {
    private ArrayList<Ennemi> listeEnnemisTouches;
    public ModeTirUnique(EffetImpact effetAttaque, Tour tourCible) {
        super(effetAttaque, tourCible);
        this.listeEnnemisTouches = new ArrayList<>();
    }

    @Override
    public void attaque() {
        Ennemi ennemi = chercheCible();
        if(ennemi != null && !listeEnnemisTouches.contains(ennemi)){
            //ennemi.ajouteEffet(this.effetAttaque);
            Projectile projectile = new Projectile(this.tourCible, ennemi, this.effetAttaque);
            Game.getGame().ajouteProjectile(projectile);
            //Ici, c'est juste pour voir le "projectile" (Peut être en faire un generique ou bien reprendre deja existant)
            //Game.getGame().ajouteProjectile(new Projectile(this.tourCible, e));
            listeEnnemisTouches.add(ennemi);
        }
    }
}
