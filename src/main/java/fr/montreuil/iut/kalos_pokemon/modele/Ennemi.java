package fr.montreuil.iut.kalos_pokemon.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Ennemi {

    private int vitesse;
    private int hp;
    private String type;
    private IntegerProperty x;
    private IntegerProperty y;
    private int[] vecteurAcc;
    private int recompense;

    private Game game;

    public Ennemi(int vitesse, int hp, String type, int x, int y, int recompense) {
        this.vitesse = vitesse;
        this.hp = hp;
        this.type = type;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.vecteurAcc = new int[] {1,0};
        this.recompense = recompense;
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

    public void seDeplace(){

        int caseSuiv= game.getTerrain().getMap_test()[ getY()/32 + vecteurAcc[1] ][getX()/32+vecteurAcc[0]];

        if (caseSuiv == 0) {

            int[][] model = new int[][] { {1,0}, {0,1} , {0,-1} };
            int n = -1;
            while (caseSuiv != 51) {
                n++;
                vecteurAcc[0] = model[n][0];
                vecteurAcc[1] = model[n][1];
                caseSuiv= game.getTerrain().getMap_test()[getY()/32 + vecteurAcc[1] ][getX()/32+vecteurAcc[0]];
            }
        }
        this.xProperty().set(this.getX() + this.getVitesse()* vecteurAcc[0] );  // 2 lignes du déplacement
        this.yProperty().set(this.getY() + this.getVitesse()* vecteurAcc[1] );
    }

    public void diminueHP(int value){
        hp -= value;
        if (hp <= 0)
            this.meurt();
    }

    public void meurt(){
        game.getListEnnemi().remove(this);
        //donne de l'argent au joueur
        game.ajoutePokedollar(recompense);

        //supprime le sprite du pokemon
    }

}
