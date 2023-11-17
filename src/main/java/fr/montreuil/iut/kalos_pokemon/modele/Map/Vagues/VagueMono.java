package fr.montreuil.iut.kalos_pokemon.modele.Map.Vagues;

import fr.montreuil.iut.kalos_pokemon.Donne.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Terrain;

public abstract class VagueMono extends Vague {
    protected int duree;
    protected Seconde frequence;

    public VagueMono(Terrain terrain, Seconde duree, Seconde frequence) {
        super(terrain,duree);
        this.frequence=frequence;
    }

    public Ennemi[] donneMoiUnEnnemi(){
        Ennemi[] ennemis = new Ennemi[1];
        ennemis[0] = genereennemi(terrain.getCaseDepart());
        return ennemis;
    }

    public Boolean peutTuMeDonnerUnEnnemi(int frameActuelle){
        return frameActuelle % frequence.getTempFrameDouble() == 0;
    }

    public abstract Ennemi genereennemi(int [] caseDepart);

}