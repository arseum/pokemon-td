package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.Donne.Type;
import fr.montreuil.iut.kalos_pokemon.modele.Game;

public class Fantominus extends Ennemi {
    public Fantominus(int x, int y) {
        super(3, 300, Type.feu, x, y, 15, "fantominus", true);
    }
}
