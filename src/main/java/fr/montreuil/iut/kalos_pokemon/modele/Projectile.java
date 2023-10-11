package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Nidoran;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.TourConcrete;

public class Projectile extends Attaque {

    private final Ennemi cible;
    protected double degatFinal;

    public Projectile(Tour tour, Ennemi ennemi, Game game) {
        super(tour, game);
        cible = ennemi;
        degatFinal = Parametres.calculDegats(tour.getType(),ennemi.getType(),tour.getDegats());
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
            explotionTir();

    }

    /**
     * methode a executer a la fin de vie du projectiles
     * permet de faire subir des degats a la cible puis disparait
     */
    protected void explotionTir(){
        if (cible.getHp() > 0) {
            cible.diminueHP(degatFinal);
            tireur.ajouteDegats(degatFinal);
            if (tireur instanceof Nidoran nidoran)
                nidoran.ajouteEnnemiEmpoissoner(cible);
        }
        game.remove(this);
    }

    /**
     * on estime que le projectile est arrivé lorsqu'il est a 15 pixel de la cible
     */
    private boolean doitBouger(){
        return Parametres.distance(this,cible) > 15;
    }

}
