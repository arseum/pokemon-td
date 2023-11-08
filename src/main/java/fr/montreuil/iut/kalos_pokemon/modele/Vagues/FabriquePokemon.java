package fr.montreuil.iut.kalos_pokemon.modele.Vagues;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.*;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Terrain;

public class FabriquePokemon {
    private Game game;
    private Terrain terrain;

    private static FabriquePokemon uniqueInstance =null;


    public static FabriquePokemon getInstance(Game game, Terrain terrain){
        if (uniqueInstance==null) {
            uniqueInstance = new FabriquePokemon(game, terrain);}
        return uniqueInstance;
    }
    private FabriquePokemon(Game game, Terrain terrain) {
        this.game=game;
        this.terrain=terrain;
    }








}
