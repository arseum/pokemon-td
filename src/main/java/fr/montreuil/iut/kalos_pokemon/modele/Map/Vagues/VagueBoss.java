package fr.montreuil.iut.kalos_pokemon.modele.Map.Vagues;

import fr.montreuil.iut.kalos_pokemon.Donnees.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Boss;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Terrain;

public class VagueBoss extends Vague {

    private boolean estApparu;

    public VagueBoss(Terrain terrain, Seconde duree) {
        super(terrain, duree);
        estApparu = false;
    }

    @Override
    public Ennemi[] donneMoiUnEnnemi() {
        return new Ennemi[]{new Boss(terrain.getCaseDepart()[0] * 32, terrain.getCaseDepart()[1] * 32)};
    }

    @Override
    public Boolean peutTuMeDonnerUnEnnemi(int frameActuelle) {
        if (!estApparu) {
            estApparu = true;
            return true;
        }
        return false;

    }
}
