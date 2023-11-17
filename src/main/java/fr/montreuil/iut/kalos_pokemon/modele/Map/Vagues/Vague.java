package fr.montreuil.iut.kalos_pokemon.modele.Map.Vagues;

import fr.montreuil.iut.kalos_pokemon.Donnees.Seconde;
import fr.montreuil.iut.kalos_pokemon.modele.Ennemis.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Map.Terrain;

public abstract class Vague {

    protected Terrain terrain;
    protected Seconde duree;

    public Vague(Terrain terrain, Seconde duree) {
        this.terrain=terrain;
        this.duree =duree;
    }

    public abstract Ennemi[] donneMoiUnEnnemi();

    public abstract Boolean peutTuMeDonnerUnEnnemi(int frameActuelle);

    public Seconde getDuree() {
        return duree;
    }
}
