package fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.ArrayList;

public class TirUnique extends Tireur{
    private ArrayList<Ennemi> listeEnnemisTouches;
    public TirUnique(EffetImpact effetAttaque, Tour tourCible) {
        super(effetAttaque, tourCible);
        this.listeEnnemisTouches = new ArrayList<>();
    }

    @Override
    public void attaque() {
        Ennemi e = chercheCible();
        if(e != null && !listeEnnemisTouches.contains(e)){
            e.ajouteEffet(this.effetAttaque);
            //Ici, c'est juste pour voir le "projectile" (Peut être en faire un generique ou bien reprendre deja existant)
            //Game.getGame().ajouteProjectile(new Projectile(this.tourCible, e));
            listeEnnemisTouches.add(e);
        }
    }
}
