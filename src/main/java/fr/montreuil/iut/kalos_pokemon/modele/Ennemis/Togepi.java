package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;

public class Togepi extends Ennemi {
    public Togepi(int x, int y, Game game) {
        super(3, 150, "normal", x, y, 50, "togepi", game);
    }

}
