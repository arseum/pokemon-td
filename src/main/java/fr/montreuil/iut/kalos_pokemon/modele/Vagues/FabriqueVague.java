package fr.montreuil.iut.kalos_pokemon.modele.Vagues;

import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Terrain;

public  class FabriqueVague {

    private static FabriqueVague uniqueInstance =null;


    public static FabriqueVague getInstance(){
        if (uniqueInstance==null) {
            uniqueInstance = new FabriqueVague();}
        return uniqueInstance;
    }
    private FabriqueVague( ){
    }


    public VagueMono créeVagueTogepi(Game game, Terrain terrain, int duree, int freq){
        return new VagueTogepi(game,terrain,duree,freq);
    }
    public VagueMono créeVagueTiplouf(Game game, Terrain terrain, int duree, int freq){
        return new VagueTiplouf(game,terrain,duree,freq);
    }
    public VagueMono créeVagueFantominus(Game game, Terrain terrain, int duree, int freq){
        return new VagueFantominus(game,terrain,duree,freq);
    }
    public VagueMono créeVagueCamerupt(Game game, Terrain terrain, int duree, int freq){
        return new VagueCamerupt(game,terrain,duree,freq);
    }
    public VagueMono créeVagueLudicolo(Game game, Terrain terrain, int duree, int freq){
        return new VaguesLudicolo(game,terrain,duree,freq);
    }

}
