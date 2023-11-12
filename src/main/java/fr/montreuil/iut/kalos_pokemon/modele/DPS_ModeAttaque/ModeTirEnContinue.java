package fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

/**
 * Dans ce mode, la tour cible un ennemi
 * et attaque que lui jusqu'à les HP de l'ennemi soit à zero ou bien qu'il soit hors de portée
 */
public class ModeTirEnContinue extends ModeTireur {
    private Ennemi ennemiCible;
    //private boolean
    public ModeTirEnContinue(EffetImpact effetAttaque, Tour tourCible) {
        super(effetAttaque, tourCible);
    }
    @Override
    public void attaque() {
        //Si pas d'ennemi ou ennemi est mort, on cherche un
        if(ennemiCible == null || !Game.getGame().getListEnnemi().contains(ennemiCible)){
            this.ennemiCible = chercheCible();
        }
        //Si a trouve et a distance
        if(ennemiCible!= null && !ennemiCible.estEffecteParEffet(this.effetAttaque)){
            Projectile projectile = new Projectile(this.tourCible, ennemiCible, this.effetAttaque);
            Game.getGame().ajouteProjectile(projectile);
        }else {
            this.ennemiCible = null;
        }
    }
}
