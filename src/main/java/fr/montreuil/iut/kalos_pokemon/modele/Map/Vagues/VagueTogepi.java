package fr.montreuil.iut.kalos_pokemon.modele.Map.Vagues;

import fr.montreuil.iut.kalos_pokemon.Donnees.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Togepi;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Terrain;

public class VagueTogepi extends VagueMono {
    public VagueTogepi(Terrain terrain, Seconde duree, Seconde frequence) {
        super(terrain, duree, frequence);
    }

    @Override
    public Ennemi genereEnnemi(int[] caseDepart) {
        return new Togepi(caseDepart[0] * 32, caseDepart[1] * 32);
    }
}
