package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.Donne.Type;
import fr.montreuil.iut.kalos_pokemon.modele.Game;

public class Camerupt extends Ennemi {
    public Camerupt(int x, int y) {
        super(1, 1100, Type.feu, x, y, 20, "camerupt", true);
    }
}
