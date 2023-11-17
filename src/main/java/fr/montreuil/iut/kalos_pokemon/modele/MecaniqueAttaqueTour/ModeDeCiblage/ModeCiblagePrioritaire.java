package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.ModeDeCiblage;

import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAProjectile.ForgeEntiteDommage;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

/**
 * Dans ce mode, la tour cible un ennemi
 * et attaque que lui jusqu'à les HP de l'ennemi soit à zero ou bien qu'il soit hors de portée
 */
public class ModeCiblagePrioritaire extends ModeDeCiblage {
    private Ennemi ennemiCible;
    //private boolean
    public ModeCiblagePrioritaire(Tour tourCible) {
        super(tourCible);
    }

    @Override
    public void attaque(int degats, ForgeEffetImpact forgeEffet, ForgeEntiteDommage forgeEntiteDommage) {
        //Si pas d'ennemi ou ennemi est mort, on cherche un
        if(ennemiCible == null || !Game.getGame().getListEnnemi().contains(ennemiCible)){
            this.ennemiCible = chercheCible();
        }
        //Si a trouve et a distance
        if(ennemiCible!= null && tourCible.estADistance(ennemiCible))
            lanceEntiteAttaque(forgeEntiteDommage,forgeEffet,degats,ennemiCible);
        else
            this.ennemiCible = null;

    }
}
