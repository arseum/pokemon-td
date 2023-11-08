package fr.montreuil.iut.kalos_pokemon.modele.Vagues;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Camerupt;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Terrain;

public class VagueCamerupt extends VagueMono{
    public VagueCamerupt(Game game, Terrain terrain, int duree, int frequence) {
        super(game, terrain, duree, frequence);
    }

    @Override
    public Boolean peutTuMeDonnerUnEnnemi(int frameActuelle) {return frameActuelle%frequence==0;}

    @Override
    public Ennemi genereennemi(int[] caseDepart) {
        return new Camerupt(caseDepart[0]*32, caseDepart[1]*32, game);
    }
}
