package fr.montreuil.iut.kalos_pokemon.modele;

import java.util.ArrayList;

public class Game {

    private Terrain terrain;
    private ArrayList<Ennemi> listEnnemi;

    public Game(){
        terrain = new Terrain();
        listEnnemi = new ArrayList<>();
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void ajouteEnnemi(Ennemi e){

        this.listEnnemi.add(e);
        e.setGame(this);
    }

    public void uneFrame(){

        for (Ennemi e : listEnnemi){
            e.seDeplace();
        }

    }

}
