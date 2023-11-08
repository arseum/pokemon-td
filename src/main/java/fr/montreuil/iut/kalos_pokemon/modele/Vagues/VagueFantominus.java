package fr.montreuil.iut.kalos_pokemon.modele.Vagues;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Fantominus;

import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Terrain;

public class VagueFantominus extends VagueMono{

    public VagueFantominus(Game game, Terrain terrain, int duree, int frequence) {
        super(game, terrain, duree, frequence);
    }

    @Override
public Boolean peutTuMeDonnerUnEnnemi(int frameActuelle) {return frameActuelle%frequence==0;}

    @Override
    public Ennemi genereennemi(int[] caseDepart) {
        return new Fantominus(caseDepart[0]*32, caseDepart[1]*32);
    }
}