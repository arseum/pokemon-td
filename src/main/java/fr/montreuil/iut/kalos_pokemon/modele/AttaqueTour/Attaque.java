package fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour;

import fr.montreuil.iut.kalos_pokemon.Donne.Type;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.AttaqueTour.Effets.EffetImpact;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Mobile;
import fr.montreuil.iut.kalos_pokemon.modele.Tours.Tour;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;


/**
 * super-classe qui regroupe les differents type d'attaque des tours
 * est necessaire pour pouvoir donner un effet speciales a des attaques
 */
public abstract class Attaque implements Mobile {
    private static int compteur = 1;
    private final String id;
    protected Tour tireur;
    protected IntegerProperty x;
    protected IntegerProperty y;
    /**
     * boolean qui permet de signaler a la vue que l'attaque se deplace
     * il aurait pu etre remplacer par un listener qui ecoute le y et x de l'attaque mais
     * cela est plus 'simple' de rajouter un boleanProperty
     */
    protected BooleanProperty bouge;
    //protected ArrayList<EffetImpact> effetImpacts;
    //FIXME Pour simplifier je dis qu'un projectile ne peut amener qu'un seul effet
    protected EffetImpact effetImpact;

    protected double degats;

    public Attaque(Tour tour, EffetImpact effetImpacts, double degats) {
    //public Attaque(Tour tour, Game game, ArrayList<EffetImpact> effetImpacts) {
        this.tireur = tour;
        this.id = "Tir_n°" + compteur;
        compteur++;
        this.bouge = new SimpleBooleanProperty(false);

        x = new SimpleIntegerProperty(tour.getX() + 22 - Parametres.offsetXTour);
        y = new SimpleIntegerProperty(tour.getY() + 22 - Parametres.offsetYTour);
        this.effetImpact = effetImpacts;
    }

    /*
    public Attaque(Tour tour) {
        //this(tour, game,null);
        this(tour,null);
        this.effetImpacts = new ArrayList<>();
    }

     */
    public BooleanProperty bougeProperty() {
        return bouge;
    }

    public Tour getTireur() {
        return tireur;
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

    public void bouge(){
        //permet de faire bouger la vue
        bouge.set(true);
        bouge.set(false);
    }

    protected double getDegatsFinale(Ennemi cible){
        return Type.calculDegats()
    }

}
