package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.Donnees.Type;

public class Nenupiot extends Ennemi {
    public Nenupiot(int x, int y) {
        super(5, 200, Type.plante, x, y, 5, "nenupiot", true);
    }
}
