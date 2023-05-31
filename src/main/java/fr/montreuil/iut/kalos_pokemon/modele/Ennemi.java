package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Map;


public abstract class Ennemi {

    private static int compteurID = 1;
    private int vitesse;
    private int hp;
    private String type;
    private IntegerProperty x;
    private IntegerProperty y;
    private int maxHeightHitbox;
    private int maxWidhtHitbox;
    private int recompense;
    private String nom;
    private Game game;
    private String id;
    private Map<Integer, Integer> cheminVersArrive;

    private int[] infoDeplacement;

    public Ennemi(int vitesse, int hp, String type, int x, int y, int recompense, String pokemon, Game game) {
        this.id = "Ennemi_n°" + compteurID;
        compteurID++;
        this.vitesse = vitesse;
        this.hp = hp;
        this.type = type;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.recompense = recompense;
        this.nom = pokemon;

        this.game = game;
        this.cheminVersArrive = this.game.getTerrain().algoBFS();
        setInfoDeplacement();
    }

    private void setInfoDeplacement(){
        int[] caseActuelle = new int[]{this.y.get() / Parametres.tailleTuile, this.x.get() / Parametres.tailleTuile};
        int idCaseSuivante = cheminVersArrive.get(this.game.getTerrain().coordonneesXYenCase(caseActuelle[0],caseActuelle[1]));
        int[] caseSuivante = this.game.getTerrain().coordonneesCaseEnXY(idCaseSuivante);
        this.infoDeplacement = new int[]{(caseSuivante[1] - caseActuelle[1]), (caseSuivante[0] - caseActuelle[0]), caseSuivante[1] * Parametres.tailleTuile, caseSuivante[0] * Parametres.tailleTuile};
    }

    public int getRecompense() {
        return recompense;
    }

    public int getMaxHeightHitbox() {
        return maxHeightHitbox;
    }

    public void setMaxHeightHitbox(int maxHeightHitbox) {
        this.maxHeightHitbox = maxHeightHitbox;
    }

    public int getMaxWidhtHitbox() {
        return maxWidhtHitbox;
    }

    public void setMaxWidhtHitbox(int maxWidhtHitbox) {
        this.maxWidhtHitbox = maxWidhtHitbox;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getVitesse() {
        return vitesse;
    }

    public int getHp() {
        return hp;
    }

    public String getType() {
        return type;
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

    public void setGame(Game game) {
        this.game = game;
    }

    /*Deprecie*/
    public void seDeplaceBFS(){
        int[] caseActuelle, caseSuivante, vecteurVitesse; //(ligne, colonne)
        int idCaseActuelle, idCaseSuivante;

        caseActuelle = new int[]{this.y.get() / Parametres.tailleTuile, this.x.get() / Parametres.tailleTuile};
        idCaseActuelle = this.game.getTerrain().coordonneesXYenCase(caseActuelle[0],caseActuelle[1]);

        //System.out.println("***SE DEPLACE BFS");
        //System.out.println(cheminVersArrive);
        //System.out.println(idCaseActuelle);

        idCaseSuivante = cheminVersArrive.get(idCaseActuelle);
        caseSuivante = this.game.getTerrain().coordonneesCaseEnXY(idCaseSuivante);
        //System.out.println("SE DEPLACE BFS***");

        vecteurVitesse = new int[] {(caseSuivante[1] - caseActuelle[1]), (caseSuivante[0] - caseActuelle[0])};

        this.xProperty().set(this.getX() + this.getVitesse() * vecteurVitesse[0]);  // 2 lignes du déplacement
        this.yProperty().set(this.getY() + this.getVitesse() * vecteurVitesse[1]);


        System.out.println(caseActuelle[0] + ", "+ caseActuelle[1] + " id: " + idCaseActuelle);
        System.out.println(this.x.get() + ", " + this.y.get());
        System.out.println(" vitesse: " + vecteurVitesse[0] + ", "+ vecteurVitesse[1] );
        System.out.println("Suivant : " + cheminVersArrive.get(idCaseActuelle));
        System.out.println("***");

    }
    public void seDeplace(){
        int nouveauX, nouveauY;

        nouveauX = this.getX() + this.getVitesse() * infoDeplacement[0];
        nouveauY = this.getY() + this.getVitesse() * infoDeplacement[1];


        boolean arriveX = (infoDeplacement[2] <= nouveauX) && (nouveauX <= (infoDeplacement[2] + this.vitesse - 1));
        boolean arriveY = (infoDeplacement[3] <= nouveauY) && (nouveauY <= (infoDeplacement[3] + this.vitesse - 1));

        this.xProperty().set(nouveauX);
        this.yProperty().set(nouveauY);

        if( arriveX && arriveY){
            this.xProperty().set(infoDeplacement[2]);
            this.yProperty().set(infoDeplacement[3]);
            setInfoDeplacement();
        }
    }

    public void diminueHP(int value) {
        hp -= value;
    }

    //Copie

    /*
    public void seDeplace(){
        int nouveauX, nouveauY;

        //System.out.println("x: " + this.getX()+ ", y :" + this.getY());

        nouveauX = this.getX() + this.getVitesse() * infoDeplacement[0];
        nouveauY = this.getY() + this.getVitesse() * infoDeplacement[1];

        //System.out.println("x: " + nouveauX + ", y :" + nouveauY);

        //boolean arriveX = (infoDeplacement[2] <= nouveauX) && (nouveauX <= (infoDeplacement[2] + Parametres.vitesseMaximale));
        //boolean arriveY = (infoDeplacement[3] <= nouveauY) && (nouveauY <= (infoDeplacement[3] + Parametres.vitesseMaximale));

        boolean arriveX = (infoDeplacement[2] <= nouveauX) && (nouveauX <= (infoDeplacement[2] + this.vitesse - 1));
        boolean arriveY = (infoDeplacement[3] <= nouveauY) && (nouveauY <= (infoDeplacement[3] + this.vitesse - 1));

        this.xProperty().set(nouveauX);
        this.yProperty().set(nouveauY);

        System.out.println(infoDeplacement[2] + ", " + infoDeplacement[3]);
        if( arriveX && arriveY){
            System.out.println("NOUVELLE CASE");
            setInfoDeplacement();
            //this.xProperty().set(infoDeplacement[2]);
            //this.yProperty().set(infoDeplacement[3]);

        }

        /*
        boolean estDansX = (nouveauX / 32) == (this.getX() / 32);
        boolean estDansY = (nouveauY / 32) == (this.getY() / 32);

        if (estDansX && estDansY){
            this.xProperty().set(nouveauX);
            this.yProperty().set(nouveauY);
        }
        else {
            setInfoDeplacement();
            this.xProperty().set(infoDeplacement[2]);
            this.yProperty().set(infoDeplacement[3]);
        }


}
     */


}
