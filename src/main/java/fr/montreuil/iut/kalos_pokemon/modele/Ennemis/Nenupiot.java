package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.Donne.Type;
import fr.montreuil.iut.kalos_pokemon.modele.Game;

public class Nenupiot extends Ennemi {
    public Nenupiot(int x, int y) {
        super(3, 100, Type.plante, x, y, 5, "nenupiot", true);
    }
}
