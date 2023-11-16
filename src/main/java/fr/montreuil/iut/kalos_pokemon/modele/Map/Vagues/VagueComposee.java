package fr.montreuil.iut.kalos_pokemon.modele.Map.Vagues;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Terrain;

import java.util.ArrayList;
import java.util.Arrays;

public class VagueComposee extends Vague{
    protected ArrayList<VagueMono> vagues;
    protected boolean[] peutCreer ;
    int compte;

    public VagueComposee(Terrain terrain,int duree,ArrayList<VagueMono> vagues) {
        super(terrain,duree);
        this.vagues=vagues;
        this.duree=600;
        peutCreer = new boolean[vagues.size()];
    }

    @Override
    public Ennemi[] donneMoiUnEnnemi() {
        int j = 0;
        Ennemi[] ennemisCreer = new Ennemi[compte];


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
