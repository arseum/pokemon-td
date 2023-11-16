package fr.montreuil.iut.kalos_pokemon.modele.DPS_ModeAttaque;

import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAProjectile.ForgeAttaque;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAProjectile.ForgeProjectile;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Projectile;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

/**
 * Dans ce mode, la tour cible un ennemi
 * et attaque que lui jusqu'à les HP de l'ennemi soit à zero ou bien qu'il soit hors de portée
 */
public class ModeTirEnContinue extends ModeAttaque {
    private Ennemi ennemiCible;
    //private boolean
    public ModeTirEnContinue(Tour tourCible) {
        super(tourCible);
    }

    @Override
    public void attaque(int degats, ForgeEffectImpact forgeEffet, ForgeAttaque forgeAttaque) {
        //Si pas d'ennemi ou ennemi est mort, on cherche un
        if(ennemiCible == null || !Game.getGame().getListEnnemi().contains(ennemiCible)){
            this.ennemiCible = chercheCible();
        }
        //Si a trouve et a distance
        //if(ennemiCible!= null && !ennemiCible.estEffecteParEffet(this.effetAttaque)){
        if(ennemiCible!= null && tourCible.estADistance(ennemiCible))
            lanceProjectile(forgeAttaque,forgeEffet,degats,ennemiCible);
        else
            this.ennemiCible = null;

    }
}
