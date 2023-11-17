package fr.montreuil.iut.kalos_pokemon.modele.Map.Vagues;

import fr.montreuil.iut.kalos_pokemon.Donnees.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ludicolo;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Terrain;

public class VaguesLudicolo extends VagueMono{
    public VaguesLudicolo(Terrain terrain, Seconde duree, Seconde frequence) {
        super(terrain, duree, frequence);
    }


    @Override
    public Ennemi genereennemi(int[] caseDepart) {return new Ludicolo(caseDepart[0]*32, caseDepart[1]*32);
    }
}