package fr.montreuil.iut.kalos_pokemon.modele.Vagues;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Terrain;

import java.util.ArrayList;
import java.util.Arrays;

public class VagueComposee extends Vague{
    protected ArrayList<VagueMono> vagues;
    protected boolean[] peutCreer ;
    int compte;

    public VagueComposee(Game game, Terrain terrain,int duree,ArrayList<VagueMono> vagues) {
        super(game, terrain,duree);
        this.vagues=vagues;
        this.duree=600;
        peutCreer = new boolean[vagues.size()];
    }

    @Override
    public Ennemi[] donneMoiUnEnnemi() {

        Ennemi[] ennemisCreer = new Ennemi[compte];
        int j = 0;

        for (VagueMono v : vagues) {
            if (peutCreer[vagues.indexOf(v)]) {
                ennemisCreer[j] = v.donneMoiUnEnnemi()[0];
                j++;
            }
        }
        return ennemisCreer;
    }


    @Override
    public Boolean peutTuMeDonnerUnEnnemi(int frameActuelle) {
        Arrays.fill(peutCreer, false);

        boolean auMoinsUn = false;
        compte=0;

        for (VagueMono v:vagues) {
            if (v.peutTuMeDonnerUnEnnemi(frameActuelle)) {
                peutCreer[vagues.indexOf(v)] = true;
                compte++;
                auMoinsUn = true;
            }
        }
        return auMoinsUn;
    }
}
