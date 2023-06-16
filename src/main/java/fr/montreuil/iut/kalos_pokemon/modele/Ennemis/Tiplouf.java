package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;

public class Tiplouf extends Ennemi {
    public Tiplouf(int x, int y, Game game) {
        super(4, 155, "eau", x, y, 10, "tiplouf", game, true);
    }
}
