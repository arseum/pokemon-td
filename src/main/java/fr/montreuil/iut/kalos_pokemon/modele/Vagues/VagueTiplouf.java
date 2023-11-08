package fr.montreuil.iut.kalos_pokemon.modele.Vagues;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Tiplouf;

import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Terrain;

public class VagueTiplouf extends VagueMono{


    public VagueTiplouf(Game game, Terrain terrain,int duree, int frequence) {
        super(game, terrain,duree,frequence);
    }



    @Override
    public Boolean peutTuMeDonnerUnEnnemi(int frameActuelle) {return frameActuelle%frequence==0; }

    @Override
    public Ennemi genereennemi(int[] caseDepart) {
        return new Tiplouf(caseDepart[0]*32, caseDepart[1]*32);
    }
}
