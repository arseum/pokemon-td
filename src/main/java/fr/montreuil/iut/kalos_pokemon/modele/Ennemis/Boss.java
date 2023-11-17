package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.Donnees.Type;
import fr.montreuil.iut.kalos_pokemon.modele.Game;

//TODO : Enum Pour pokémon ennemi
public class Boss extends Ennemi {
    public Boss(int x, int y) {
        super(2, 14545, Type.neutre, x, y, 0, "rayquaza", true);
    }

    @Override
    protected void meurt() {
        super.meurt();
        Game.getGame().setBossVaincu(true);
    }
}
