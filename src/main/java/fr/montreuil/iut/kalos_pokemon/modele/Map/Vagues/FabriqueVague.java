package fr.montreuil.iut.kalos_pokemon.modele.Map.Vagues;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Terrain;

public  class FabriqueVague {

    private static FabriqueVague uniqueInstance =null;


    public static FabriqueVague getInstance(){
        if (uniqueInstance==null) {
            uniqueInstance = new FabriqueVague();}
        return uniqueInstance;
    }

    private FabriqueVague( ){ } //constructeur vide


    public VagueMono creeVagueTogepi(Terrain terrain, Seconde duree, Seconde freq){
        return new VagueTogepi(terrain,duree,freq);
    }
    public VagueMono creeVagueTiplouf(Terrain terrain, Seconde duree, Seconde freq){
        return new VagueTiplouf(terrain,duree,freq);
    }
    public VagueMono creeVagueFantominus(Terrain terrain, Seconde duree, Seconde freq){
        return new VagueFantominus(terrain,duree,freq);
    }
    public VagueMono creeVagueCamerupt(Terrain terrain, Seconde duree, Seconde freq){
        return new VagueCamerupt(terrain,duree,freq);
    }
    public VagueMono creeVagueLudicolo(Terrain terrain, Seconde duree, Seconde freq){
        return new VaguesLudicolo(terrain,duree,freq);
    }

    public VagueMono creeVagueRoucoul(Terrain terrain, Seconde duree, Seconde freq){
        return new VagueRoucoul(terrain,duree,freq);
    }
}
