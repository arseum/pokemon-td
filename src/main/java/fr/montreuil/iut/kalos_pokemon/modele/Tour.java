package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.List;

public abstract class Tour implements Objet{
    private static int compteurID = 1;
    protected IntegerProperty portee;
    protected int degats;
    protected final String type;
    private String nom;
    private final int prix;
    private final IntegerProperty x;
    private final IntegerProperty y;
    protected final IntegerProperty level;
    protected final DoubleProperty compteurDegats;
    private final String id;
    protected int attaqueSpeed;
    protected int tempProchaineAttaque;
    
    protected Game game;

    public Tour(int portee, int degats, String type, int prix, int x, int y, String pokemon, int attaqueSpeed) {
        this.id = "Tour_n°" + compteurID;
        compteurID++;
        this.portee = new SimpleIntegerProperty(portee);
        this.degats = degats;
        this.type = type;
        this.prix = prix;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.level = new SimpleIntegerProperty(1);
        this.nom = pokemon;
        this.attaqueSpeed = attaqueSpeed;
        this.compteurDegats = new SimpleDoubleProperty(0);
        tempProchaineAttaque = 0;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nouveauNom){this.nom = nouveauNom;}

    public String getId() {
        return id;
    }
    public IntegerProperty porteeProperty() {
        return portee;
    }

    public int getDegats() {
        return degats;
    }

    public int getX() {
        return x.get();
    }
    public int getPrix() {
        return this.prix;
    }

    public String getType(){return this.type;}

    public IntegerProperty xProperty() {
        return x;
    }

    public int getLevel() {
        return level.get();
    }
    public double getCompteurDegats() {
        return compteurDegats.get();
    }
    public DoubleProperty compteurDegatsProperty() {
        return compteurDegats;
    }
    public IntegerProperty levelProperty() {
        return level;
    }
    public int getY() {
        return y.get();
    }
    public IntegerProperty yProperty() {
        return y;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    protected void ajouteDegats(double value) { compteurDegats.set(compteurDegats.get() + value);}
    public void levelUp(){
        if (level.get() + 1 == Parametres.niveauEvolutionTour)
            evolution();
        this.level.set(level.get()+1);
        amelioreStats();
    };

    protected void evolution(){
        setNom(Parametres.nomGrandEvolution.get(nom));
    }

    protected abstract void amelioreStats();

    public void attaque() {

        Ennemi cible = chercheCible();

        if (cible != null) {
            lanceProjectile(cible);
            tempProchaineAttaque = game.getNbFrameValue() + attaqueSpeed;
        }

    }

    /**
     * @return un ennemi si il y en a un en portée sinon renvoie null
     */
    protected Ennemi chercheCible() {
        Ennemi cible = null;
        int index = 0;

        List<Ennemi> listEnnemi = game.getListEnnemi().stream().toList();

        //cherche une cible
        while (cible == null && index < listEnnemi.size()) {

            if (peutCibler(listEnnemi.get(index)))
                cible = listEnnemi.get(index);
            else
                index++;

        }
        return cible;
    }

    /**
     * methode qui est utile car il y a des tours qui ont des conditions supplementaires pour le ciblage
     */
    protected boolean peutCibler(Ennemi ennemi) {
        return estADistance(ennemi);
    }

    /**
     * @return true si l'ennemi est a une distance inferieur a la portée de la tour
     */
    protected boolean estADistance(Ennemi ennemi) {
        return Parametres.distance(this,ennemi) <= portee.get();
    }

    /**
     * utile car toutes les tours ne lancent pas les meme type projectiles
     */
    protected void lanceProjectile(Ennemi cible){
        game.ajouteProjectile(new Projectile(this, cible, game));
    }

    public int prixRevente(){
        int sommeCumulee = (this.level.get() - 1) * this.level.get() / 2;
        return (int)( (this.prix + this.prix * (this.level.get() - 1 + sommeCumulee/10.0)) * Parametres.pourcentageRevente);
    }

    /**
     *Chaque amélioration coute 10% plus cher
     */
    public int prixAmelioration(){
        return (int)(this.prix * (1 + 0.1 * this.level.get() ) );
    }
}
