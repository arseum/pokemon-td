package fr.montreuil.iut.kalos_pokemon.modele.Ennemis;

import fr.montreuil.iut.kalos_pokemon.modele.Ennemi;
import fr.montreuil.iut.kalos_pokemon.modele.Game;

public class Ludicolo extends Ennemi {
    public Ludicolo(int x, int y, Game game) {
        super(2, 110, "eau", x, y, 45, "ludicolo", game);
    }

    public void chevalDeTroie() {
    } //TODO
}
