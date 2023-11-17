package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.Donnees.Type;

public class Roucool extends Ennemi {
    public Roucool(int x, int y) {
        super(2, 300, Type.neutre, x, y, 7, "roucool", false);
    }
}
