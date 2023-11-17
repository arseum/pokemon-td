package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.Donne.Type;
import fr.montreuil.iut.kalos_pokemon.modele.Game;

public class Roucool extends Ennemi {
    public Roucool(int x, int y) {
        super(2, 250, Type.neutre, x, y, 7, "roucool",false);
    }
}
