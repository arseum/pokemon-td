package fr.montreuil.iut.kalos_pokemon.modele.Vagues;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Togepi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Terrain;
import fr.montreuil.iut.kalos_pokemon.modele.Tour;

public class VagueTypeUn extends Vague{


    public VagueTypeUn(Game game, Terrain terrain) {
        super(game, terrain);
        this.duree = 600;
    }

    @Override
    public Ennemi donneMoiUnEnnemi() {
        int[] caseDepart = terrain.getCaseDepart();
        return new Togepi(caseDepart[0]*32, caseDepart[1]*32, game);
    }

    @Override
    public Boolean peutTuMeDonnerUnEnnemi(int frameActuelle) {
        return frameActuelle%90==0;
    }
}
