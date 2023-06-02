package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;

public class Roucool extends Ennemi {
    public Roucool(int x, int y, Game game) {
        super(2, 175, "neutre", x, y, 50, "roucool", game);
    }
}
