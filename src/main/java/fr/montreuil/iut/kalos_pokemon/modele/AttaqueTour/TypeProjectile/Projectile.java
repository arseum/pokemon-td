package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.TypeProjectile;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.TypeProjectile.Attaque;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;

import java.util.ArrayList;

public class Projectile extends Attaque {

    private final Ennemi cible;
    protected double degatToucher;


    public Projectile(Tour tour, Ennemi ennemi, Game game, ArrayList<EffetImpact> effetImpacts ) {
        super(tour, game,effetImpacts);
        cible = ennemi;
        degatToucher = Parametres.calculDegats(tour.getType(),ennemi.getType(),tour.getDegats());
    }

    public Projectile(Tour tour, Ennemi ennemi, Game game) {
        this(tour, ennemi,game, (ArrayList<EffetImpact>) null); //c moche mais jsp pk je suis obliger de faire ca
        effetImpacts = new ArrayList<>();
    }

    public Projectile(Tour tour, Ennemi cible, Game game, EffetImpact effet) {
        this(tour, cible, game);
        ArrayList<EffetImpact> list = new ArrayList<>();
        list.add(effet);
        this.effetImpacts = list;
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
            cible.diminueHP(degatToucher);
            tireur.ajouteDegats(degatToucher);
            ajouteEffet();
        }
        game.remove(this);
    }

    private void ajouteEffet() {
        for (EffetImpact e : effetImpacts)
            cible.ajouteEffet(e);

    }

    /**
     * on estime que le projectile est arrivé lorsqu'il est a 15 pixel de la cible
     */
    private boolean doitBouger(){
        return Parametres.distance(this,cible) > 15;
    }

}
