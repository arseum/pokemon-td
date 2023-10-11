package fr.montreuil.iut.kalos_pokemon.modele.Tours;


import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Objet;
import fr.montreuil.iut.kalos_pokemon.modele.Projectile;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;

import java.util.List;


public abstract class Tour implements Objet {

     protected IntegerProperty portee;
    protected int degats;
    protected String type;
    protected String nom;
    protected int prix;
    protected IntegerProperty x;
    protected IntegerProperty y;
    protected int attaqueSpeed;
    protected static int compteurID = 1;
    protected IntegerProperty level;
    protected DoubleProperty compteurDegats;
    protected String id;
    protected int tempProchaineAttaque;
    protected Game game;

     public void setType(String type) {
          this.type = type;
     }

     public void setPrix(int prix) {
          this.prix = prix;
     }

     public void setId(String id) {
          this.id = id;
     }

     public void setPortee(int portee) {
          this.portee.set(portee);
     }

     public void setPorteeProperty(IntegerProperty p) {this.portee = p;}

     public void setDegats(int degats) {
          this.degats = degats;
     }

     public void setNom(String nom) {
          this.nom = nom;
     }

     public void setX(int x) {
          this.x.set(x);
     }

     public void setY(int y) {
          this.y.set(y);
     }

     public void setAttaqueSpeed(int attaqueSpeed) {
          this.attaqueSpeed = attaqueSpeed;
     }

     public void setLevel(int level) {
          this.level.set(level);
     }

     public void setCompteurDegats(double compteurDegats) {
          this.compteurDegats.set(compteurDegats);
     }

     public void setTempProchaineAttaque(int tempProchaineAttaque) {
          this.tempProchaineAttaque = tempProchaineAttaque;
     }

     public void setGame(Game game) {
          this.game = game;
     }

     public int getPortee() {
          return portee.get();
     }

     public IntegerProperty porteeProperty() {
          return portee;
     }

     public int getDegats() {
          return degats;
     }

     public String getType() {
          return type;
     }

     public String getNom() {
          return nom;
     }

     public int getPrix() {
          return prix;
     }

     @Override
     public int getX() {
          return x.get();
     }

     public IntegerProperty xProperty() {
          return x;
     }

     @Override
     public int getY() {
          return y.get();
     }

     public IntegerProperty yProperty() {
          return y;
     }

     public int getAttaqueSpeed() {
          return attaqueSpeed;
     }

     public static int getCompteurID() {
          return compteurID;
     }

     public int getLevel() {
          return level.get();
     }

     public IntegerProperty levelProperty() {
          return level;
     }

     public double getCompteurDegats() {
          return compteurDegats.get();
     }

     public DoubleProperty compteurDegatsProperty() {
          return compteurDegats;
     }

     public String getId() {
          return id;
     }

     public int getTempProchaineAttaque() {
          return tempProchaineAttaque;
     }

     public Game getGame() {
          return game;
     }

     //fin des getter + setter

     public abstract void evolution();

     public abstract void amelioreStats();

     public void ajouteDegats(double value) { compteurDegats.set(getCompteurDegats() + value);}

     public void levelUp(){
        setLevel(getLevel()+1);
        amelioreStats();
        if (getLevel() == Parametres.niveauEvolutionTour)
            evolution();
     };

     public void attaque() {

        Ennemi cible = chercheCible();

        if (cible != null) {
            lanceProjectile(cible);
            tempProchaineAttaque = getGame().getNbFrameValue() + getAttaqueSpeed();
        }

    }

     /**
     * @return un ennemi si il y en a un en portée sinon renvoie null
     */
    protected Ennemi chercheCible() {
        Ennemi cible = null;
        int index = 0;

        List<Ennemi> listEnnemi = getGame().getListEnnemi().stream().toList();

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
    public boolean estADistance(Ennemi ennemi) {
        return Parametres.distance(this,ennemi) <= getPortee();
    }

    /**
     * utile car toutes les tours ne lancent pas les meme type projectiles
     */
    protected void lanceProjectile(Ennemi cible){
        getGame().ajouteProjectile(new Projectile(this, cible, game));
    }

    public int prixRevente(){
        int sommeCumulee = (getLevel() - 1) * getLevel() / 2;
        return (int)( (getPrix() + getPrix() * (getLevel() - 1 + sommeCumulee/10.0)) * Parametres.pourcentageRevente);
    }

    /**
     *Chaque amélioration coute 10% plus cher
     */
    public int prixAmelioration(){
         return (int)(getPrix()* (1 + 0.1 * getLevel() ) );
    }

}
