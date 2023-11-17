package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteAttaque;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeAEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Projectile extends EntiteAttaque {

    private static int compteur = 1;
    private final String id;

    protected IntegerProperty x;
    protected IntegerProperty y;
    /**
     * boolean qui permet de signaler a la vue que l'attaque se deplace
     * il aurait pu etre remplacer par un listener qui ecoute le y et x de l'attaque mais
     * cela est plus 'simple' de rajouter un boleanProperty
     */
    protected BooleanProperty bouge;

    public Projectile(Tour tour, Ennemi ennemi, ForgeEffetImpact forgeEffetImpact, double degats) {
        super(tour, forgeEffetImpact,degats,ennemi);
        this.id = "Tir_n°" + compteur;
        compteur++;
        this.bouge = new SimpleBooleanProperty(false);

        x = new SimpleIntegerProperty(tour.getX() + 22 - Parametres.offsetXTour);
        y = new SimpleIntegerProperty(tour.getY() + 22 - Parametres.offsetYTour);

    }

    /**
     * methode qui permet de simuler un deplacement simple des projectiles
     * ils suivent la cible jusqu'a etre assez proche pour exploser
     */
    public void bouge() {

        if (doitBouger()) {
            y.set(getY() < cible.getY() ? getY() + 4 : getY() - 4);
            x.set(getX() < cible.getX() ? getX() + 4 : getX() - 4);
            //Pour éviter de gros changements sur la vue
            bouge.set(true);
            bouge.set(false);
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
        //return Parametres.distance((Objet) this,cible) > 15;
        return distanceIci() > 15;
    }

    public BooleanProperty bougeProperty() {
        return bouge;
    }

    public String getId() {
        return id;
    }

    public int getX() {
        return x.get();
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public int getY() {
        return y.get();
    }

    public IntegerProperty yProperty() {
        return y;
    }

    @Override
    public void traitementEntiteDommage() {
        //Game.getGame().ajouteProjectile(this);
        Game.getGame().ajouteVraiProjectile(this);
        //permet de faire bouger la vue
    }

    @Override
    public void finEntiteDommage() {
        Game.getGame().removeProjectile(this);
    }

    //Todo z a changer
    public int distanceIci(){
        int super_x;
        int super_y;

        super_x = Math.abs(this.getX() - this.cible.getX());
        super_y = Math.abs(this.getY() - this.cible.getY());

        return (int) Math.sqrt((super_x * super_x) + (super_y * super_y));
    }

    /*
    public void bouge(){
        //permet de faire bouger la vue
        bouge.set(true);
        bouge.set(false);
    }

     */

}
