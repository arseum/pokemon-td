package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.ForgeAEffet.ForgeEffectImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

public class Projectile extends AttaqueCible {

    public Projectile(Tour tour, Ennemi ennemi,
                      ForgeEffectImpact forgeEffectImpact, double degats) {
        super(tour, forgeEffectImpact,degats,ennemi);

    }

    /**
     * methode qui permet de simuler un deplacement simple des projectiles
     * ils suivent la cible jusqu'a etre assez proche pour exploser
     */
    @Override
    public void bouge() {

        if (doitBouger()) {
            y.set(getY() < cible.getY() ? getY() + 4 : getY() - 4);
            x.set(getX() < cible.getX() ? getX() + 4 : getX() - 4);
            super.bouge();
        } else
            toucheCible();

    }



    /**
     * methode a executer a la fin de vie du projectiles
     * permet de faire subir des degats a la cible puis disparait
     */


//    private void ajouteEffet() {
//        if(!this.cible.estEffecteParEffet(this.effetImpact))
//            cible.ajouteEffet(this.effetImpact);
//    }

    /**
     * on estime que le projectile est arrivé lorsqu'il est a 15 pixel de la cible
     */
    private boolean doitBouger(){
        return Parametres.distance(this,cible) > 15;
    }

}
