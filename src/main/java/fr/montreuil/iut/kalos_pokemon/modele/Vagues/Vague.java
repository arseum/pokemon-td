package fr.montreuil.iut.kalos_pokemon.modele.Vagues;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Terrain;

public abstract class Vague {
    protected int duree;

    //todo: Attributs temporaire
    protected Game game;
    protected Terrain terrain;

    public Vague(Game game, Terrain terrain) {
        this.game = game;
        this.terrain = terrain;
    }

    public abstract Ennemi donneMoiUnEnnemi();

    public abstract Boolean peutTuMeDonnerUnEnnemi(int frameActuelle);

    public int getDuree() {
        return duree;
    }
}
