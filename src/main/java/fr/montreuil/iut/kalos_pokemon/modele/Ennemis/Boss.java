package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;

public class Boss extends Ennemi {
    public Boss( int x, int y, Game game) {
        super(1, 10000, "neutre", x, y, 0, "rayquaza", game);
    }
}
