package fr.montreuil.iut.kalos_pokemon.modele;

import fr.montreuil.iut.kalos_pokemon.Parametres;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Map;


public abstract class Ennemi {

    private int vitesse;
    private int hp;
    private String type;
    private IntegerProperty x;
    private IntegerProperty y;
    //private int[] vecteurAcc;
    private int recompense;
    private String nom;
    private Game game;
    private static int compteurID = 1;
    private String id;

    //private Terrain t;
    private Map<Integer, Integer> cheminVersArrive;

    public Ennemi(int vitesse, int hp, String type, int x, int y, int recompense, String pokemon, Game game) {
        this.id = "n°" + compteurID;
        compteurID++;
        this.vitesse = vitesse;
        this.hp = hp;
        this.type = type;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        //this.vecteurAcc = new int[]{1, 0};
        this.recompense = recompense;
        this.nom = pokemon;

        //todo Terrain_BFS 3
        /*
        this.t = new Terrain();
        this.cheminVersArrive = t.algoBFS();
        System.out.println(cheminVersArrive);

         */
        this.game = game;
        this.cheminVersArrive = this.game.getTerrain().algoBFS();
    }

    public int getRecompense() {
        return recompense;
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

    /*
    //À garder car BFS est inspiré de celui-ci
    public void seDeplace() {

        //int caseSuiv = game.getTerrain().getMap_test()[getY() / 32 + vecteurAcc[1]][getX() / 32 + vecteurAcc[0]];
        boolean caseSuiv = game.getTerrain().estChemin(getY()/32 + vecteurAcc[1], getX()/32+vecteurAcc[0]);

        //if (caseSuiv == 0) {
        if (!caseSuiv) {

            int[][] model = new int[][]{{1, 0}, {0, 1}, {0, -1}};
            int n = -1;
            //while (caseSuiv != 51) {
            while (!caseSuiv) {
                n++;
                vecteurAcc[0] = model[n][0];
                vecteurAcc[1] = model[n][1];
                //caseSuiv = game.getTerrain().getMap_test()[getY() / 32 + vecteurAcc[1]][getX() / 32 + vecteurAcc[0]];
                caseSuiv = game.getTerrain().estChemin(getY()/32 + vecteurAcc[1], getX()/32+vecteurAcc[0]);
            }
        }
        this.xProperty().set(this.getX() + this.getVitesse() * vecteurAcc[0]);  // 2 lignes du déplacement
        this.yProperty().set(this.getY() + this.getVitesse() * vecteurAcc[1]);
    }

     */

    public void seDeplaceBFS(){
        int[] caseActuelle, caseSuivante, vecteurVitesse; //(ligne, colonne)
        int idCaseActuelle, idCaseSuivante;

        caseActuelle = new int[]{this.y.get() / Parametres.tailleTuile, this.x.get() / Parametres.tailleTuile};
        //idCaseActuelle = t.coordonneesXYenCase(caseActuelle[0],caseActuelle[1]);
        idCaseActuelle = this.game.getTerrain().coordonneesXYenCase(caseActuelle[0],caseActuelle[1]);
        System.out.println("***SE DEPLACE BFS");
        System.out.println(cheminVersArrive);
        System.out.println(idCaseActuelle);
        idCaseSuivante = cheminVersArrive.get(idCaseActuelle);
        //caseSuivante = t.coordonneesCaseEnXY(idCaseSuivante);
        caseSuivante = this.game.getTerrain().coordonneesCaseEnXY(idCaseSuivante);
        System.out.println("SE DEPLACE BFS***");

        vecteurVitesse = new int[] {(caseSuivante[1] - caseActuelle[1]), (caseSuivante[0] - caseActuelle[0])};

        this.xProperty().set(this.getX() + this.getVitesse() * vecteurVitesse[0]);  // 2 lignes du déplacement
        this.yProperty().set(this.getY() + this.getVitesse() * vecteurVitesse[1]);


        System.out.println(caseActuelle[0] + ", "+ caseActuelle[1] + " id: " + idCaseActuelle);
        System.out.println(this.x.get() + ", " + this.y.get());
        System.out.println(" vitesse: " + vecteurVitesse[0] + ", "+ vecteurVitesse[1] );
        System.out.println("Suivant : " + cheminVersArrive.get(idCaseActuelle));
        System.out.println("***");

        //this.xProperty().set(this.getX() + this.getVitesse() * vecteurAcc[0]);  // 2 lignes du déplacement
        //this.yProperty().set(this.getY() + this.getVitesse() * vecteurAcc[1]);

    }

    public void diminueHP(int value) {
        hp -= value;
    }


}
