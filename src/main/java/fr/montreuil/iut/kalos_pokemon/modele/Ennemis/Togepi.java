package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.Donne.Type;
import fr.montreuil.iut.kalos_pokemon.modele.Game;

public class Togepi extends Ennemi {
    public Togepi(int x, int y) {
        super(3, 125, Type.neutre, x, y, 5, "togepi",true);
    }

}
