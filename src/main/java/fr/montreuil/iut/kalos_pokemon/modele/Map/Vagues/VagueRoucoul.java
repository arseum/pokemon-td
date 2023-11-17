package fr.montreuil.iut.kalos_pokemon.modele.Map.Vagues;

import fr.montreuil.iut.kalos_pokemon.Donnees.Seconde;
import fr.montreuil.iut.kalos_pokemon.Parametres;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.*;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Terrain;

        import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;

public class VagueRoucoul extends VagueMono{


    public VagueRoucoul(Terrain terrain, Seconde duree, Seconde frequence) {
        super(terrain,duree,frequence);
    }

    private int caseDepartYVol(){
        int delta = 2;
        int min = delta;
        int max = (terrain.getHauteurTerrain()/ Parametres.tailleTuile - 1) - delta;
        return min + (int)(Math.random() * ((max - min) + 1));
        //return  1 + (int)(Math.random() * ((terrain.getHauteurTerrain()/Parametres.tailleTuile - 1 - 1) + 1));

        //return (int)(Math.random() * terrain.getHauteurTerrain()/ Parametres.tailleTuile);
    }

    @Override
    public Ennemi genereennemi(int[] caseDepart) {
        return new Roucool(caseDepart[0]*32, caseDepartYVol()*32);
    }
}

