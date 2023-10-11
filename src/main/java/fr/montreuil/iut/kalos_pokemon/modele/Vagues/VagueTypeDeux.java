package fr.montreuil.iut.kalos_pokemon.modele.Vagues;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Camerupt;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Terrain;

public class VagueTypeDeux extends Vague{
    public VagueTypeDeux(Game game, Terrain terrain) {
        super(game, terrain);
        this.duree = 900;
    }

    @Override
    public Ennemi donneMoiUnEnnemi() {
        int[] caseDepart = terrain.getCaseDepart();
        return new Camerupt(caseDepart[0]*32, caseDepart[1]*32, game);
    }

    @Override
    public Boolean peutTuMeDonnerUnEnnemi(int frameActuelle) {
        return frameActuelle%400==0;
    }
}
