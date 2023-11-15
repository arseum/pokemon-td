package fr.montreuil.iut.kalos_pokemon.modele.Map.Vagues;

import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Terrain;

public  class FabriqueVague {

    private static FabriqueVague uniqueInstance =null;


    public static FabriqueVague getInstance(){
        if (uniqueInstance==null) {
            uniqueInstance = new FabriqueVague();}
        return uniqueInstance;
    }

    private FabriqueVague( ){ } //constructeur vide


    public VagueMono créeVagueTogepi(Terrain terrain, int duree, int freq){
        return new VagueTogepi(terrain,duree,freq);
    }
    public VagueMono créeVagueTiplouf(Terrain terrain, int duree, int freq){
        return new VagueTiplouf(terrain,duree,freq);
    }
    public VagueMono créeVagueFantominus(Terrain terrain, int duree, int freq){
        return new VagueFantominus(terrain,duree,freq);
    }
    public VagueMono créeVagueCamerupt(Terrain terrain, int duree, int freq){
        return new VagueCamerupt(terrain,duree,freq);
    }
    public VagueMono créeVagueLudicolo(Terrain terrain, int duree, int freq){
        return new VaguesLudicolo(terrain,duree,freq);
    }

    public VagueMono créeVagueRoucoul(Terrain terrain, int duree, int freq){
        return new VagueRoucoul(terrain,duree,freq);
    }
}
