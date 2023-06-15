package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import javafx.beans.property.IntegerProperty;
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

    public String getType(){return this.type;}

    public IntegerProperty xProperty() {
        return x;
    }

    public int getLevel() {
        return level.get();
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

        Ennemi cible = null;
        int index = 0;

        List<Ennemi> listEnnemi = game.getListEnnemi().stream().toList();

        //cherche une cible
        while (cible == null && index < listEnnemi.size()) {

            if (Parametres.distance(this,listEnnemi.get(index)) <= portee.get())
                cible = listEnnemi.get(index);
            else
                index++;

        }

        //attaque la cible
        if (cible != null) {
            lanceProjectile(cible);
            tempProchaineAttaque = game.getNbFrameValue() + attaqueSpeed;
        }

    }

    protected void lanceProjectile(Ennemi cible){
        game.ajouteProjectile(new Projectile(this, cible, game));
    }

    public int getPrix() {
        return this.prix;
    }


    public int prixRevente(){
        int sommeCumulee = (this.level.get() - 1) * this.level.get() / 2;
        return (int)( (this.prix + this.prix * (this.level.get() - 1 + sommeCumulee/10.0)) * Parametres.pourcentageRevente);
    }

    /*
    Chaque amélioration coute 10% plus cher
     */
    public int prixAmelioration(){
        return (int)(this.prix * (1 + 0.1 * this.level.get() ) );
    }
}
