package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.Donne.PokemonEnum;
import fr.montreuil.iut.kalos_pokemon.Donne.Type;
import fr.montreuil.iut.kalos_pokemon.modele.Game;

public class Boss extends Ennemi {
    public Boss( int x, int y) {
        super(2, 14545, Type.neutre, x, y, 0, "rayquaza", true);
    }

    @Override
    protected void meurt() {
        super.meurt();
        Game.getGame().setBossVaincu(true);
    }
}
