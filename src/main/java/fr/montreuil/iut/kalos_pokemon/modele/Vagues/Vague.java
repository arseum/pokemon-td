package fr.montreuil.iut.kalos_pokemon.modele.Vagues;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;
import fr.montreuil.iut.kalos_pokemon.modele.Terrain;

public abstract class Vague {
    protected Game game;
    protected Terrain terrain;
    protected int duree;
    protected int frequence;
    protected FabriquePokemon fab;

    public Vague(Game game, Terrain terrain,int duree) {
        this.game=game;
        this.terrain=terrain;
        this.duree =duree;
    }

    public abstract Ennemi[] donneMoiUnEnnemi();

    public abstract Boolean peutTuMeDonnerUnEnnemi(int frameActuelle);

    public int getDuree() {
        return duree;
    }
}
