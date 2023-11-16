package fr.montreuil.iut.kalos_pokemon.modele.Map.Vagues;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Terrain;

public abstract class Vague {
    protected Terrain terrain;
    protected int duree;
    protected int frequence;


    public Vague(Terrain terrain,int duree) {
        this.terrain=terrain;
        this.duree =duree;
    }

    public abstract Ennemi[] donneMoiUnEnnemi();

    public abstract Boolean peutTuMeDonnerUnEnnemi(int frameActuelle);

    public int getDuree() {
        return duree;
    }
}
