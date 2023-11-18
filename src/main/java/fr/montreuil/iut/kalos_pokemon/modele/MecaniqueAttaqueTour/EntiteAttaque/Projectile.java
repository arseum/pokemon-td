package fr.montreuil.iut.kalos_pokemon.modele.MecaniqueAttaqueTour.EntiteAttaque;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Forges.ForgeEffet.ForgeEffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Mobile;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import javafx.beans.property.*;

public abstract class Projectile extends EntiteAttaque implements Mobile {

    private static int compteur = 1;
    private final String id;

    protected DoubleProperty x;
    protected DoubleProperty y;
    /**
     * boolean qui permet de signaler a la vue que l'attaque se deplace
     * il aurait pu etre remplacer par un listener qui ecoute le y et x de l'attaque mais
     * cela est plus 'simple' de rajouter un boleanProperty
     */
    protected BooleanProperty bouge;

    public Projectile(Tour tour, Ennemi ennemi,
                      ForgeEffetImpact forgeEffetImpact,
                      double degats) {
        super(tour, forgeEffetImpact, degats, ennemi);
        this.id = "Tir_n°" + compteur;
        compteur++;
        this.bouge = new SimpleBooleanProperty(false);

        x = new SimpleDoubleProperty(tour.getX() + 22 - Parametres.offsetXTour);
        y = new SimpleDoubleProperty(tour.getY() + 22 - Parametres.offsetYTour);

    }

    public abstract void gestionTir();

    public void bouge() {
        if (doitBouger()) {
            y.set(getY() < cible.getY() ? getY() + 4 : getY() - 4);
            x.set(getX() < cible.getX() ? getX() + 4 : getX() - 4);
            //Pour éviter de gros changements sur la vue
            bouge.set(true);
            bouge.set(false);
        } else
            gestionTir();
    }

    @Override
    public void gestionEntiteAttaque() {
        Game.getGame().ajouteProjectile(this);
    }

    protected void finTir() {
        Game.getGame().removeProjectile(this);
    }

    /**
     * on estime que le projectile est arrivé lorsqu'il est a 15 pixel de la cible
     */
    private boolean doitBouger() {
        return Parametres.distance(this.getX(), this.getY(), this.cible.getX(), this.cible.getY()) > 15;
    }

    public double getY() {
        return y.get();
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x.get();
    }

    public BooleanProperty bougeProperty() {
        return bouge;
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public DoubleProperty yProperty() {
        return y;
    }
}
