package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;

public class Fantominus extends Ennemi {
    public Fantominus(int x, int y, Game game) {
        super(2, 300, "neutre", x, y, 50, "fantominus", game);
    }
}
