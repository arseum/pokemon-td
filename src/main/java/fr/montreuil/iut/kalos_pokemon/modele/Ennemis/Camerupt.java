package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.modele.Game;

public class Camerupt extends Ennemi {
    public Camerupt(int x, int y, Game game) {
        super(1, 850, "feu", x, y, 15, "camerupt", game, true);
    }
}
