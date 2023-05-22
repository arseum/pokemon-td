package fr.montreuil.iut.kalos_pokemon.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Game {

    private Terrain terrain;
    private ArrayList<Ennemi> listEnnemi;
    private IntegerProperty pokedollar;

    public Game(){
        terrain = new Terrain();
        listEnnemi = new ArrayList<>();
        pokedollar = new SimpleIntegerProperty(300);
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public IntegerProperty PokedollarProperty() {
        return pokedollar;
    }

    public int getPokedollar() {
        return pokedollar.get();
    }

    public void ajoutePokedollar(int value){
        pokedollar.setValue(pokedollar.get() + value);
    }

    public void ajouteEnnemi(Ennemi e){
        this.listEnnemi.add(e);
        e.setGame(this);
    }

    public ArrayList<Ennemi> getListEnnemi() {
        return listEnnemi;
    }

    public void uneFrame(){

        for (Ennemi e : listEnnemi){
            e.seDeplace();
        }

    }

}
