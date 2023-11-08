package fr.montreuil.iut.kalos_pokemon.modele.Vagues;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
        import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Terrain;

public abstract class VagueMono extends Vague {
    protected int duree;
    protected int frequence;


    public VagueMono(Game game, Terrain terrain,int duree, int frequence) {
        super(game, terrain,duree);
        this.frequence=frequence;

    }

    public  Ennemi[] donneMoiUnEnnemi(){
        System.out.println("donne moi un ennemi");
        Ennemi[] ennemis = new Ennemi[1];
        ennemis[0] = genereennemi(terrain.getCaseDepart());
        return ennemis;
    }

    public abstract Boolean peutTuMeDonnerUnEnnemi(int frameActuelle);

    public abstract Ennemi genereennemi(int [] caseDepart);

    public int getDuree() {
        return duree;
    }
}