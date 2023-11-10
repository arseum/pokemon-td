package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.Donne.Type;
import fr.montreuil.iut.kalos_pokemon.modele.Game;

public class Tiplouf extends Ennemi {
    public Tiplouf(int x, int y) {
        super(4, 150, Type.eau, x, y, 10, "tiplouf", true);
    }
}
