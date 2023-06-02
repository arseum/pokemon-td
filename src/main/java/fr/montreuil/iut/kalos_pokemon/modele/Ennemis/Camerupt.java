package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;

public class Camerupt extends Ennemi {
    public Camerupt(int x, int y, Game game) {
        super(2, 650, "feu", x, y, 75, "camerupt", game);
    }
}
