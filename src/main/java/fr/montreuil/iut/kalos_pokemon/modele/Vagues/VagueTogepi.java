package fr.montreuil.iut.kalos_pokemon.modele.Vagues;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Togepi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Terrain;

public class VagueTogepi extends VagueMono{
    public VagueTogepi(Game game, Terrain terrain,int duree,int frequence) {
        super(game, terrain,duree,frequence);
    }

    @Override
    public Boolean peutTuMeDonnerUnEnnemi(int frameActuelle) {
        System.out.println("peux tu");return frameActuelle%frequence==0;}

    @Override
    public Ennemi genereennemi(int[] caseDepart) {
        System.out.println("genere ennemi");return new Togepi(caseDepart[0]*32, caseDepart[1]*32);
    }
}
