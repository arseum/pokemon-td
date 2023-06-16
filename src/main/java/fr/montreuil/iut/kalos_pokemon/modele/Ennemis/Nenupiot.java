package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;

public class Nenupiot extends Ennemi {
    public Nenupiot(int x, int y, Game game) {
        super(3, 100, "plante", x, y, 5, "nenupiot", game, true);
    }
}
